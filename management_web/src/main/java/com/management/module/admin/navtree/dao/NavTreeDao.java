package com.management.module.admin.navtree.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgPower;

/**
 * 导航树管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Repository("NavTreeDao")
@Scope("prototype")
public class NavTreeDao extends HBaseDao<BgPower>
{
	/**
	 * 获取一个树结点
	 * 
	 * @param nBgTreeId
	 * @return BgTree
	 */
	public BgPower getTreeNode(Integer nBgTreeId)
	{
		return getById(nBgTreeId);
	}
	
	/**
	 * 获取树结点列表
	 * 
	 * @param nBgTreeId
	 * @return List<BgTree>
	 */
	public List<BgPower> getTreeNodeList(Integer nBgTreeId)
	{
		return select("FROM BgPower WHERE nParentId=?", new Field().addInt(nBgTreeId));
	}
	
	/**
	 * 获取与用户相关的树结点列表
	 * @param nBgUserId
	 * @param nBgTreeId
	 * @return List<BgTree>
	 */
	public List<BgPower> getMyTreeNodeList(String sUserId, Integer nBgTreeId)
	{
		//System.out.println("nBgUserId="+nBgUserId+"|nBgTreeId="+nBgTreeId);
		Field oField = new Field();
		oField.addInt(nBgTreeId);
		if(!"1".equals(sUserId)) oField.addStr(sUserId);
		
		String sHQL = "1".equals(sUserId) ? "FROM BgPower WHERE nParentId=?" :
				"  SELECT DISTINCT A "
			  + "  FROM "
			  + "  	BgPower AS A "
			  + "  	, BgRoleLinkPower AS B "
			  + "  	, BgUserLinkRole AS C "
			  + "  WHERE 1=1 "
			  + "  	AND A.nPowerId=B.nPowerId  "
			  + "  	AND B.nRoleId=C.nRoleId  "
			  + "  	AND A.nParentId=?  "
			  + "  	AND C.sUserId=? ";
		return select(sHQL, oField);
	}
	
	/**
	 * 获取树结点列表记录总数
	 * 
	 * @param nBgTreeId
	 * @return Long
	 */
	/*public Long getTreeNodeListTotal(Integer nBgTreeId)
	{
		return getTotal("FROM BgTree WHERE nBgTreeId=?",
				new Field().addInt(nBgTreeId));
	}*/
	
	/**
	 * 增加一个树结点
	 * 
	 * @param oBgTree
	 * @return int
	 */
	public int addTreeNode(BgPower oBgTree)
	{
		int nPKId = 0;
		if (insert(oBgTree) > 0)
		{
			List list=null;
			String sHQL="SELECT b.nPowerId , a.sPath FROM BgPower a, BgPower b  WHERE b.nParentId=a.nPowerId AND b.sPath IS NULL";
			list=select(sHQL);
			
			int nPowerId=Integer.parseInt(((Object[])list.get(0))[0].toString());
			String sPath=((Object[])list.get(0))[1].toString();
			update("UPDATE BgPower SET sPath='"+sPath+nPowerId+"' WHERE nPowerId="+nPowerId);
			//update("UPDATE BgPower A SET A.sPath=CONCAT((SELECT B.sPath FROM BgPower B WHERE B.nPowerId=A.nParentId), A.nPowerId, '/') WHERE A.sPath IS NULL");
			nPKId = oBgTree.getPowerId();
		}
		return nPKId;
	}
	
	/**
	 * 修改一个树结点
	 * 
	 * @param oBgTree
	 * @return int
	 */
	public int editTreeNode(BgPower oBgTree)
	{
		return update(oBgTree);
	}
	
	/**
	 * 删除一个树结点
	 * 
	 * @param nBgTreeId
	 * @return int
	 */
	public int delTreeNode(Integer nBgTreeId)
	{
		int nTotal = -1;
		BgPower oBgTree = getTreeNode(nBgTreeId);
		if (oBgTree != null)
		{
			nTotal = delete("DELETE FROM BgPower WHERE sPath LIKE ?",
					new Field().addStr(oBgTree.getPath() + "%"));
		}
		return nTotal;
	}
	
	/**
	 * 修改树结点路径
	 * @param sConsPath
	 * @param sOldPath
	 * @param sNewPath
	 * @return int
	 */
	public int editTreeNodePath(String sConsPath, String sOldPath, String sNewPath)
	{
		return update(
				"UPDATE BgTree SET sPath=REPLACE(sPath, ?, ?) WHERE sPath LIKE ?",
				new Field().addStr(sOldPath).addStr(sNewPath)
						.addStr(sConsPath + "%"));
	}
	
}
