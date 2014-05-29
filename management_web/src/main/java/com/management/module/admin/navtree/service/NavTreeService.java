package com.management.module.admin.navtree.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.management.module.admin.navtree.dao.NavTreeDao;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.sysmgr.BgPower;

/**
 * 导航树管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Service("NavTreeService")
@Scope("prototype")
public class NavTreeService
{
	@Resource(name = "NavTreeDao")
	private NavTreeDao m_oNavTreeDao;
	
	/**
	 * 获取后台导航树
	 * @param nBgTreeId
	 * @return List<Tree>
	 */
	public List<Tree> getBgNavTree(Integer nBgTreeId, Boolean bIsCheckBoxTree)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<BgPower> aBgTree = getTreeNodeList(nBgTreeId);
		
		if(aBgTree != null && aBgTree.size() > 0)
		{
			for(BgPower oBgTree : aBgTree)
			{
				Tree oTree = new Tree();
				oTree.setText(oBgTree.getPowerName());
				oTree.setId("TreeNav_" + String.valueOf(oBgTree.getPowerId()));
				oTree.setUrl(oBgTree.getUrl());
				if(bIsCheckBoxTree) oTree.setChecked(Boolean.FALSE);
				List<BgPower> aBgTreeChild = getTreeNodeList(oBgTree.getPowerId());
				if(aBgTreeChild != null && aBgTreeChild.size() > 0)
				{
					oTree.setLeaf(Boolean.FALSE);
					oTree.setCls("folder");
					if(nBgTreeId == 1) 
					{
						oTree.setExpanded(Boolean.TRUE);
						oTree.setChildren(getBgNavTree(oBgTree.getPowerId(), bIsCheckBoxTree));
					}
				}
				
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	/**
	 * 用户登录后的导航树
	 * @param nBgUserId
	 * @param nBgTreeId
	 * @return List<Tree>
	 */
	public List<Tree> getMyBgNavTree(String sUserId, Integer nBgTreeId)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<BgPower> aBgTree = m_oNavTreeDao.getMyTreeNodeList(sUserId, nBgTreeId);
		if(aBgTree != null && aBgTree.size() > 0)
		{
			for(BgPower oBgTree : aBgTree)
			{
				Tree oTree = new Tree();
				oTree.setText(oBgTree.getPowerName());
				oTree.setId("TreeNav_" + String.valueOf(oBgTree.getPowerId()));
				oTree.setUrl(oBgTree.getUrl());
				
				List<BgPower> aBgTreeChild = m_oNavTreeDao.getMyTreeNodeList(sUserId, oBgTree.getPowerId());
				if(aBgTreeChild != null && aBgTreeChild.size() > 0)
				{
					oTree.setLeaf(Boolean.FALSE);
					oTree.setCls("folder");
					if(nBgTreeId == 1) 
					{
						oTree.setExpanded(Boolean.TRUE);
					}
				}
				
				aTree.add(oTree);
			}
		}		
		return aTree;
	}
	
	/**
	 * 获取一个树结点
	 * @param nBgTreeId
	 * @return BgTree
	 */
	public BgPower getTreeNode(Integer nBgTreeId)
	{
		return m_oNavTreeDao.getTreeNode(nBgTreeId);
	}
	
	/**
	 * 获取树结点列表
	 * @param nBgTreeId
	 * @return List<BgTree>
	 */
	public List<BgPower> getTreeNodeList(Integer nBgTreeId)
	{
		return m_oNavTreeDao.getTreeNodeList(nBgTreeId);
	}
	
	/**
	 * 增加一个树结点
	 * @param nPId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return int
	 */
	public int addBgNavTreeNode(int nPId, String sName, String sUrl, String sRemark)
	{
		BgPower oBgTree = new BgPower();
		oBgTree.setParentId(nPId);
		oBgTree.setPowerName(sName);
		oBgTree.setUrl(sUrl);
		oBgTree.setRemark(sRemark);
		return m_oNavTreeDao.addTreeNode(oBgTree);
	}
	
	/**
	 * 修改一个树结点
	 * @param nBgTreeId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return int
	 */
	public int editBgNavTreeNode(int nBgTreeId, String sName, String sUrl, String sRemark)
	{
		BgPower oBgTree = getTreeNode(nBgTreeId);
		if(oBgTree == null) return -1;
		oBgTree.setPowerName(sName);
		oBgTree.setUrl(sUrl);
		oBgTree.setRemark(sRemark);
		return m_oNavTreeDao.editTreeNode(oBgTree);
	}
	
	/**
	 * 删除一个树结点
	 * @param nBgTreeId
	 * @return int
	 */
	public int delTreeNode(Integer nBgTreeId)
	{
		return m_oNavTreeDao.delTreeNode(nBgTreeId);
	}
	
	/**
	 * 移动树结点
	 * @param nDestBgTreeId
	 * @param nSrcBgTreeId
	 * @return int
	 */
	/*public int editMoveBgNavTree(int nDestBgTreeId, int nSrcBgTreeId)
	{
		int nTotal = -1;
		BgTree oSrcBgTree = getTreeNode(nSrcBgTreeId);
		BgTree oDestBgTree = getTreeNode(nDestBgTreeId);
		if(oSrcBgTree != null && oDestBgTree != null)
		{
			BgTree oSrcParentBgTree = getTreeNode(oSrcBgTree.getPId());
			
			oSrcBgTree.setPId(oDestBgTree.getBgTreeId());
			nTotal = m_oNavTreeDao.editTreeNode(oSrcBgTree);
			if(oSrcParentBgTree != null)
			{
				m_oNavTreeDao.editTreeNodePath(oSrcBgTree.getPath(), oSrcParentBgTree.getPath(), oDestBgTree.getPath());
			}
		}
		return nTotal;
	}*/
	
}
