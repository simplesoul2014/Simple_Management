package com.management.module.admin.sysmgr.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.management.module.admin.sysmgr.dao.BgRRTDao;
import com.management.module.admin.sysmgr.dao.BgRoleDao;
import com.management.module.admin.sysmgr.dao.BgURRDao;
import com.management.module.admin.sysmgr.dao.BgUserDao;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.sysmgr.BgCpRole;
import com.management.pojo.admin.sysmgr.BgCpUser;
import com.management.pojo.admin.sysmgr.BgRRT;
import com.management.pojo.admin.sysmgr.BgRole;
import com.management.pojo.admin.sysmgr.BgRoleLinkPower;
import com.management.pojo.admin.sysmgr.BgURR;
import com.management.pojo.admin.sysmgr.BgUser;
import com.management.pojo.admin.sysmgr.BgUserLinkRole;

/**
 * 后台角色管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Service("BgRoleService")
@Scope("prototype")
public class BgRoleService
{
	@Resource(name = "BgRoleDao")
	private BgRoleDao m_oBgRoleDao;
	
	@Resource(name = "BgRRTDao")
	private BgRRTDao m_oBgRRTDao;
	
	@Resource(name = "BgURRDao")
	private BgURRDao m_oBgURRDao;
	
	@Resource(name = "BgUserDao")
	private BgUserDao m_oBgUserDao;
	
	/**
	 * 获取一个后台角色资料
	 * @param nBgRoleId
	 * @return BgRole
	 */
	public BgCpRole getBgRole(int nBgRoleId)
	{
		return m_oBgRoleDao.getBgRole(nBgRoleId);
	}
	
	/**
	 * 获取角色与树结点关联数据
	 * @param nBgRoleId
	 * @return List<Integer>
	 */
	public List<Integer> getBgRRTList(int nBgRoleId)
	{
		List<Integer> aTreeId = new ArrayList<Integer>();
		List<BgRoleLinkPower> aBgRRT = m_oBgRRTDao.getBgRRTList(nBgRoleId);
		for(BgRoleLinkPower oBgRRT : aBgRRT)
		{
			aTreeId.add(oBgRRT.getPowerId());
		}
		return aTreeId;
	}
	
	/**
	 * 获取后台角色资料列表
	 * @param sName
	 * @return List<BgRole>
	 */
	public List<BgCpRole> getBgRoleList(String sName, int nStart, int nOffset)
	{
		return m_oBgRoleDao.getBgRoleList(sName, nStart, nOffset);
	}
	
	/**
	 * 获取后台角色列表数量
	 * @param sName
	 * @return Long
	 */
	public Long getBgRoleListTotal(String sName)
	{
		return m_oBgRoleDao.getBgRoleListTotal(sName);
	}
	
	/**
	 * 获取后台角色资料列表
	 * @return List<BgRole>
	 */
	public List<BgCpRole> getBgRoleList()
	{
		return m_oBgRoleDao.getBgRoleList();
	}
	
	/**
	 * 获取后台角色资料树
	 * @param nBgRoleId
	 * @return List<Tree>
	 */
	public List<Tree> getBgRoleTree(int nBgRoleId)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<BgCpRole> aBgRole = getBgRoleList();
		
		if(aBgRole != null && aBgRole.size() > 0)
		{
			for(BgCpRole oBgRole : aBgRole)
			{
				Tree oTree = new Tree();
				oTree.setText(oBgRole.getName());
				oTree.setId("TreeNav_" + String.valueOf(oBgRole.getRoleId()));
				aTree.add(oTree);
			}
		}
		return aTree;
	}	
	
	/**
	 * 增加一个后台角色资料
	 * @param sName
	 * @param sBgTreeIdList
	 * @param sRemark
	 * @return int
	 */
	public int addBgRole(
		 String sName
		,String sBgTreeIdList
		,String sRemark		
	)
	{
		BgCpRole oBgRole = new BgCpRole();
		oBgRole.setName(sName);
		//oBgRole.setRemark(sRemark);
		int nTotal = m_oBgRoleDao.addBgRole(oBgRole);
		int nBgRoleId = 0;
		if(nTotal > 0)
		{
			nBgRoleId = oBgRole.getRoleId();
			addBgRRT(nBgRoleId, sBgTreeIdList);
		}
		return nBgRoleId;
	}
	
	/**
	 * 修改一个后台角色资料
	 * @param nBgRoleId
	 * @param sName
	 * @param sBgTreeIdList
	 * @param sRemark
	 * @return int
	 */
	public int editBgRole(
		Integer nBgRoleId
		,String sName
		,String sBgTreeIdList
		,String sRemark
	)
	{
		BgCpRole oBgRole = getBgRole(nBgRoleId);
		oBgRole.setName(sName);
		//oBgRole.setRemark(sRemark);
		int nTotal = m_oBgRoleDao.editBgRole(oBgRole);
		if(nTotal > 0)
		{
			m_oBgRRTDao.delBgRRT(nBgRoleId);
			addBgRRT(nBgRoleId, sBgTreeIdList);
			
			//List<BgURR> aBgURR = m_oBgURRDao.getBgURRList(nBgRoleId);
			List<BgUserLinkRole> aBgURR= m_oBgURRDao.getBgURRList(nBgRoleId);
			Set<String> aBgUserId = new HashSet<String>();
			if(aBgURR != null && aBgURR.size() > 0)
			{
				for(BgUserLinkRole oBgURR : aBgURR)
				{
					aBgUserId.add(oBgURR.getUserId());
				}
			}
			for(String sUserId : aBgUserId)
			{
				editBgUserRole(sUserId);
			}
		}
		return nTotal;
	}
	
	/**
	 * 删除角色列表
	 * 
	 * @param nBgRoleId
	 * @return int
	 */
	public int delBgRole(int nBgRoleId)
	{
		int nTotal = m_oBgRoleDao.delBgRole(getBgRole(nBgRoleId));
		if(nTotal > 0)
		{
			m_oBgRRTDao.delBgRRT(nBgRoleId);
			List<BgUserLinkRole> aBgURR = m_oBgURRDao.getBgURRList(nBgRoleId);
			Set<String> aBgUserId = new HashSet<String>();
			if(aBgURR != null && aBgURR.size() > 0)
			{
				for(BgUserLinkRole oBgURR : aBgURR)
				{
					aBgUserId.add(oBgURR.getUserId());
				}
			}
			m_oBgURRDao.delBgURRByRoleId(nBgRoleId);
			for(String sUserId : aBgUserId)
			{
				editBgUserRole(sUserId);
			}
		}
		return nTotal;
	}
	
	/**
	 * 后台用户归角色
	 * @param sBgUserId
	 * @param nBgRoleId
	 * @return int
	 */
	public int addBgUserToRole(String sBgUserId, int nBgRoleId)
	{
		int nTotal = 0;
		m_oBgURRDao.delBgURRByUR(sBgUserId, nBgRoleId);
		String[] aBgUserId = sBgUserId.split(",");
		for(String sUserId : aBgUserId)
		{
			//Integer nBgUserId = Integer.valueOf(sTempBgUserId);
			BgCpUser oBgUser = m_oBgUserDao.getBgUser(sUserId);
			if(oBgUser == null) continue;
			BgUserLinkRole oBgURR = m_oBgURRDao.getBgURR(nBgRoleId, sUserId);
			if(oBgURR == null)
			{
				BgUserLinkRole oTempBgURR = new BgUserLinkRole();
				oTempBgURR.setUserId(sUserId);
				oTempBgURR.setRoleId(nBgRoleId);
				if(m_oBgURRDao.addBgURR(oTempBgURR) > 0) nTotal ++;
			}
			editBgUserRole(sUserId);
		}
		return nTotal == 0 ? -1 : nTotal;
	}
	
	/**
	 * 增加角色与树结点的关联记录
	 * @param nBgRoleId
	 * @param sBgTreeIdList
	 * @return int
	 */
	private int addBgRRT(int nBgRoleId, String sBgTreeIdList)
	{
		int nTotal = 0;
		
		String[] aBgTreeIdList = sBgTreeIdList.split(",");
		if(aBgTreeIdList != null && aBgTreeIdList.length > 0)
		{
			for(String sBgTreeId : aBgTreeIdList)
			{
				BgRoleLinkPower oBgRRT = new BgRoleLinkPower();
				oBgRRT.setRoleId(nBgRoleId);
				oBgRRT.setPowerId(Integer.valueOf(sBgTreeId));
				nTotal += m_oBgRRTDao.addBgRRT(oBgRRT);
			}
		}
		return nTotal == 0 ? -1 : nTotal;
	}
	
	/**
	 * 修改用户角色
	 * @param sUserId 
	 * return void
	 */
	private void editBgUserRole(String sUserId)
	{
		String sBgRoleId = "";
		String sBgRole = "";
		BgCpUser oBgUser = m_oBgUserDao.getBgUser(sUserId);
		if(oBgUser == null) return;
		List<BgCpRole> aBgRole = m_oBgRoleDao.getBgRoleList(sUserId);
		if(aBgRole != null && aBgRole.size() > 0)
		{
			for(int i = 0; i < aBgRole.size(); i ++)
			{
				if(i > 0)
				{
					sBgRoleId += ",";
					sBgRole += ",";
				}
				sBgRoleId += aBgRole.get(i).getRoleId();
				sBgRole += aBgRole.get(i).getName();
			}
		}
		//oBgUser.setRole(sBgRoleId);
		oBgUser.setRole(sBgRole);
		m_oBgUserDao.editBgUser(oBgUser);
	}
}
