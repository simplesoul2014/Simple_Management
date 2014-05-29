package com.management.module.admin.sysmgr.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.sysmgr.BgDept;

/**
 * 后台部门管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Repository("BgDeptDao")
@Scope("prototype")
public class BgDeptDao extends HBaseDao<BgDept>
{
	/**
	 * 获取一个部门
	 * 
	 * @param nBgDeptId
	 * @return BgDept
	 */
	public BgDept getBgDept(Integer nBgDeptId)
	{
		return getById(nBgDeptId);
	}
	
	/**
	 * 获取部门列表
	 * 
	 * @param nBgDeptId
	 * @return List<BgDept>
	 */
	public List<BgDept> getBgDeptList(Integer nBgDeptId)
	{
		return select("FROM BgDept WHERE nPId=?", new Field().addInt(nBgDeptId));
	}
	
	/**
	 * 获取部门列表记录总数
	 * 
	 * @param nBgDeptId
	 * @return Long
	 */
	public Long getBgDeptListTotal(Integer nBgDeptId)
	{
		return getTotal("FROM BgDept WHERE nBgDeptId=?",
				new Field().addInt(nBgDeptId));
	}
	
	/**
	 * 增加一个部门
	 * 
	 * @param oBgDept
	 * @return int
	 */
	public int addBgDept(BgDept oBgDept)
	{
		int nPKId = 0;
		if (insert(oBgDept) > 0)
		{
			update("UPDATE BgDept A SET A.sPath=CONCAT((SELECT B.sPath FROM BgDept B WHERE B.nBgDeptId=A.nPId), A.nBgDeptId, '/') WHERE A.sPath IS NULL");
			nPKId = oBgDept.getBgDeptId();
		}
		return nPKId;
	}
	
	/**
	 * 修改一个部门
	 * 
	 * @param oBgDept
	 * @return int
	 */
	public int editBgDept(BgDept oBgDept)
	{
		return update(oBgDept);
	}
	
	/**
	 * 删除一个部门
	 * 
	 * @param nBgDeptId
	 * @return int
	 */
	public int delBgDept(Integer nBgDeptId)
	{
		int nTotal = -1;
		BgDept oBgDept = getBgDept(nBgDeptId);
		if (oBgDept != null)
		{
			nTotal = delete("DELETE FROM BgDept WHERE sPath LIKE ?",
					new Field().addStr(oBgDept.getPath() + "%"));
		}
		return nTotal;
	}
	
	/**
	 * 修改部门路径
	 * @param sConPath
	 * @param sOldPath
	 * @param sNewPath
	 * @return int
	 */
	public int editBgDeptPath(String sConPath, String sOldPath, String sNewPath)
	{
		return update(
				"UPDATE BgDept SET sPath=REPLACE(sPath, ?, ?) WHERE sPath LIKE ?",
				new Field().addStr(sOldPath).addStr(sNewPath)
						.addStr(sConPath + "%"));
	}
}
