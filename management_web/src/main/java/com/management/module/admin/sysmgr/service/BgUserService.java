package com.management.module.admin.sysmgr.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.core.util.AlgorithmUitl;
import com.management.module.admin.sysmgr.dao.BgCpUserDetailDao;
import com.management.module.admin.sysmgr.dao.BgURPDao;
import com.management.module.admin.sysmgr.dao.BgURRDao;
import com.management.module.admin.sysmgr.dao.BgUserDao;
import com.management.pojo.admin.sysmgr.BgCpUser;
import com.management.pojo.admin.sysmgr.BgCpUserDetail;
import com.management.pojo.admin.sysmgr.BgURP;
import com.management.pojo.admin.sysmgr.BgURR;
import com.management.pojo.admin.sysmgr.BgUser;
import com.management.pojo.admin.sysmgr.BgUserLinkRole;

/**
 * 后台用户管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Service("BgUserService")
@Scope("prototype")
public class BgUserService
{
	@Resource(name = "BgUserDao")
	private BgUserDao m_oBgUserDao;
	@Resource(name = "BgCpUserDetailDao")
	private BgCpUserDetailDao m_oBgCpUserDetailDao;
	
	@Resource(name = "BgURRDao")
	private BgURRDao m_oBgURRDao;
	
	@Resource(name = "BgURPDao")
	private BgURPDao m_oBgURPDao;
	
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public BgCpUser getBgUser(int nBgUserId)
	{
		BgCpUser oBgUser = m_oBgUserDao.getBgUser(nBgUserId);
		/*if(nBgUserId == 1)
		{
			oBgUser.setBgRoleId("0");
			oBgUser.setBgRole("全部");
		}*/
		return oBgUser;
	}
	
	/**
	 * 获取一个后台用户扩展资料
	 * 
	 * @param nBgUserId
	 * @return BgUser
	 */
	public BgCpUserDetail getCpUserDetail(String sUserId)
	{
		BgCpUserDetail oBgUser = m_oBgCpUserDetailDao.getBgCpUserDetail(sUserId);
		return oBgUser;
	}
	/**
	 * 获取一个后台用户资料
	 * 
	 * @param nBgUserId
	 * @return List oBguser
	 */
	public List getCpUser(String sUserId)
	{
		List oBgUser = m_oBgUserDao.getCpUser(sUserId);
		/*if(nBgUserId == 1)
		{
			oBgUser.setBgRoleId("0");
			oBgUser.setBgRole("全部");
		}*/
		return oBgUser;
	}
	
	/**
	 * 通过帐号和密码获取后台用户资料
	 * 
	 * @param sAccount
	 * @param sPassword
	 * @return BgCpUser
	 */
	public BgCpUser getBgCpUser(String sAccount, String sPassword)
	{
		return m_oBgUserDao.getBgCpUser(sAccount, AlgorithmUitl.md5(sPassword));
	}
	
	/**
	 * 通过帐号获取用户资料
	 * @param sAccount
	 * @return BgUser
	 */
	public BgCpUser getBgUser(String sAccount)
	{
		return m_oBgUserDao.getBgUser(sAccount);
	}
	
	/**
	 * 获取后台用户资料列表
	 * @param nBgRoleId
	 * @param sName
	 * @param sAccount
	 * @param nStart
	 * @param nOffset
	 * @return List
	 */
	public List<BgCpUser> getBgUserList(Integer nBgRoleId, String sName, String sAccount,
			int nStart, int nOffset)
	{
		return m_oBgUserDao.getBgUserList(nBgRoleId, sName, sAccount, nStart, nOffset);
	}
	
	/**
	 * 获取后台用户列表总数
	 * @param nBgRoleId
	 * @param sName
	 * @param sAccount
	 * @return Long
	 */
	public Long getBgUserListTotal(Integer nBgRoleId, String sName, String sAccount)
	{
		return m_oBgUserDao.getBgUserListTotal(nBgRoleId, sName, sAccount);
	}
	
	/**
	 * 增加一个后台用户资料
	 * @param sUserId
	 * @param sPassword
	 * @param sName
	 * @param nBgDeptId
	 * @param sDeptName
	 * @param sBgRoleId
	 * @param sBgRole
	 * @param sBgPostId
	 * @param sBgPost
	 * @param nSex
	 * @param sQQ
	 * @param sEmail
	 * @param sMobile
	 * @param sTel
	 * @param sFax
	 * @param sZip
	 * @param sAddress
	 * @param sBrief
	 * @return int
	 */
	public int addBgUser(
		BgCpUser loginuser	
		 ,String sUserId
		,String sPassword
		,String sName
		,Integer nBgDeptId
		,String sDeptName
		,String sBgRoleId
		,String sBgRole
		,String sBgPostId
		,String sBgPost
		,Integer nSex
		,String sQQ
		,String sEmail
		,Integer nUserType
		,String sTel
		,String sFax
		,String sZip
		,String sAddress
		,String sBrief			
	)
	{
		BgCpUser oBgUser = new BgCpUser();
		BgCpUserDetail oCpUserDetail= new  BgCpUserDetail();
		oBgUser.setUserId(sUserId);
		oBgUser.setPassword(AlgorithmUitl.md5(sPassword));
		oBgUser.setUserName(sName);
		oBgUser.setCreatTime(new Date());
		oBgUser.setUserCreator(loginuser.getUserName());
		oBgUser.setStatus(0);
		//oBgUser.setBgDeptId(nBgDeptId);
		//oBgUser.setDeptName(sDeptName);
		int nRoleId=Integer.parseInt(sBgRoleId);
		oBgUser.setRoleId(nRoleId);
		oBgUser.setRole(sBgRole);
		//oBgUser.setBgPostId(sBgPostId);
		//oBgUser.setBgPost(sBgPost);
		
		oCpUserDetail.setUserId(sUserId);
		oCpUserDetail.setUserSex(nSex);
		oCpUserDetail.setUserQq(sQQ);
		oCpUserDetail.setUserEmail(sEmail);
		//oBgUser.setMobile(sMobile);
		oCpUserDetail.setUserType(nUserType);
		oCpUserDetail.setUserTel(sTel);
		oCpUserDetail.setUserFax(sFax);
		oCpUserDetail.setUserPost(sZip);
		oCpUserDetail.setUserAdrr(sAddress);
		oCpUserDetail.setUserDesc(sBrief);
		int nTotal = m_oBgUserDao.addBgUser(oBgUser);
		if(nTotal > 0)
		{
			nTotal=m_oBgCpUserDetailDao.addBgUser(oCpUserDetail);
			addRole(sBgRoleId, oBgUser.getUserId());
			/*addPost(sBgPostId, oBgUser.getBgUserId());
			addRole(sBgRoleId, oBgUser.getBgUserId());*/
		}
		return nTotal;
	}
	
	/**
	 * 修改一个后台用户资料
	 * @param sUserId
	 * @param sPassword
	 * @param sName
	 * @param nBgDeptId
	 * @param sDeptName
	 * @param sBgRoleId
	 * @param sBgRole
	 * @param sBgPostId
	 * @param sBgPost
	 * @param nSex
	 * @param sQQ
	 * @param sEmail
	 * @param sMobile
	 * @param sTel
	 * @param sFax
	 * @param sZip
	 * @param sAddress
	 * @param sBrief
	 * @return int
	 */
	public int editBgUser(
		String sUserId
		,String sPassword
		,String sName
		,Integer nBgDeptId
		,String sDeptName
		,String sBgRoleId
		,String sBgRole
		,String sBgPostId
		,String sBgPost		
		,Integer nSex
		,String sQQ
		,String sEmail
		,Integer nUserType
		,String sTel
		,String sFax
		,String sZip
		,String sAddress
		,String sBrief
	)
	{
		Boolean bIsRoleEdit = Boolean.FALSE;
		Boolean bIsPostEdit = Boolean.FALSE;
		BgCpUserDetail oBgCpUserDetail = getCpUserDetail(sUserId);
		BgCpUser oBgUser=getBgUser(sUserId);
		//BgCpUserDetail oBgCpUserDetail = new BgCpUserDetail();
		//BgCpUser oBgUser = new BgCpUser();
		/*if(!sPassword.equals("**********"))
		{
			oBgUser.setPassword(AlgorithmUitl.md5(sPassword));
		}*/
		oBgUser.setUserName(sName);
		
		if(!sBgRole.isEmpty() && !sBgRole.equals(oBgUser.getRole()) && !"1".equals(sUserId))
		{
			bIsRoleEdit = Boolean.TRUE;
			int nRoleId=Integer.parseInt(sBgRoleId);
			oBgUser.setRoleId(nRoleId);
			oBgUser.setRole(sBgRole);
		}
		/*if(nBgDeptId != 0 && nBgDeptId != oBgUser.getBgDeptId())
		{
			oBgUser.setBgDeptId(nBgDeptId);
			oBgUser.setDeptName(sDeptName);
		}
		if(!sBgRoleId.isEmpty() && !sBgRoleId.equals(oBgUser.getBgRoleId()) && nBgUserId != 1)
		{
			bIsRoleEdit = Boolean.TRUE;
			oBgUser.setBgRoleId(sBgRoleId);
			oBgUser.setBgRole(sBgRole);
		}
		if(!sBgPostId.isEmpty() && !sBgPostId.equals(oBgUser.getBgPostId()))
		{
			bIsPostEdit = Boolean.TRUE;
			oBgUser.setBgPostId(sBgPostId);
			oBgUser.setBgPost(sBgPost);
		}*/
		oBgCpUserDetail.setUserSex(nSex);
		oBgCpUserDetail.setUserQq(sQQ);
		oBgCpUserDetail.setUserEmail(sEmail);
		oBgCpUserDetail.setUserType(nUserType);
		oBgCpUserDetail.setUserTel(sTel);
		oBgCpUserDetail.setUserFax(sFax);
		oBgCpUserDetail.setUserPost(sZip);
		oBgCpUserDetail.setUserAdrr(sAddress);
		oBgCpUserDetail.setUserDesc(sBrief);
		int nTotal = m_oBgUserDao.editBgUser(oBgUser);
		if(nTotal > 0){
		nTotal = m_oBgCpUserDetailDao.editBgUser(oBgCpUserDetail);
		}
		if(nTotal > 0)
		{
			if(bIsRoleEdit)
			{
				addRole(sBgRoleId, oBgUser.getUserId());
			}
			/*if(bIsPostEdit)
			{
				addPost(sBgPostId, oBgUser.getBgUserId());
			}*/
		}
		return nTotal;
	}
	
	/**
	 * 修改登录用户密码
	 * @param sUserId
	 * @param sPassword
	 * @return int
	 */
	public int editMyBgUserPW(String sUserId, String sPassword)
	{
		BgCpUser oBgUser = getBgUser(sUserId);
		oBgUser.setPassword(AlgorithmUitl.md5(sPassword));
		return m_oBgUserDao.editBgUser(oBgUser);
	}
	
	/**
	 * 删除用户列表
	 * 
	 * @param sBgUserIdList，ID之间用逗号隔开
	 * @return int
	 */
	public int delBgUserList(String sBgUserIdList)
	{
		int nTotal = m_oBgUserDao.delBgUserList(sBgUserIdList);
		/*if(nTotal > 0)
		{
			m_oBgURRDao.delBgURRByUser(sBgUserIdList);
		}*/
		return nTotal;
	}
	
	/**
	 * 增加角色
	 * @param sBgRoleId
	 * @param nBgUserId 
	 * return void
	 */
	private void addRole(String sBgRole, String sUserId)
	{
		if(!sBgRole.isEmpty() && !"1".equals(sUserId))
		{
			m_oBgURRDao.delBgURRByUser(sUserId);
			String[] aBgRoleId = sBgRole.split(",");
			for(String sTempBgRoleId : aBgRoleId)
			{
				Integer nBgRoleId = Integer.valueOf(sTempBgRoleId);
				BgUserLinkRole oBgURR = new BgUserLinkRole();
				oBgURR.setRoleId(nBgRoleId);
				oBgURR.setUserId(sUserId);
				m_oBgURRDao.addBgURR(oBgURR);
			}
		}
	}
	
	/**
	 * 增加岗位
	 * @param sBgPostId
	 * @param nBgUserId 
	 * return void
	 */
	/*private void addPost(String sBgPostId, Integer nBgUserId)
	{
		if(!sBgPostId.isEmpty())
		{
			String[] aBgPostId = sBgPostId.split(",");
			m_oBgURPDao.delBgURPByUserId(String.valueOf(nBgUserId));
			for(String sTempBgPostId : aBgPostId)
			{
				Integer nBgPostId = Integer.valueOf(sTempBgPostId);
				BgURP oBgURP = new BgURP();
				oBgURP.setBgPostId(nBgPostId);
				oBgURP.setBgUserId(nBgUserId);
				m_oBgURPDao.addBgURP(oBgURP);
			}
		}			
	}*/
}
