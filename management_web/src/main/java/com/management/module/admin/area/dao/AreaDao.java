package com.management.module.admin.area.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.area.Area;
/**
 * 地区管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Repository("AreaDao")
@Scope("prototype")
public class AreaDao extends HBaseDao<Area>
{
	/**
	 * 获取一个树结点
	 * 
	 * @param nAreaId
	 * @return Area
	 */
	public Area getArea(Integer nAreaId)
	{
		return getById(nAreaId);
	}
	
	/**
	 * 获取树结点列表
	 * 
	 * @param nAreaId
	 * @param sName
	 * @return List<Area>
	 */
	public List<Area> getAreaList(Integer nAreaId)
	{
		return select("FROM Area WHERE nPId=? ORDER BY nAreaId", new Field().addInt(nAreaId));
	}	
	public List<Area> getAreaList(Integer nAreaId, String sName, int nStart, int nLimit)
	{
		return select("FROM Area WHERE nPId=? AND sName LIKE ? ORDER BY nAreaId", new Field().addInt(nAreaId).addStr("%" + sName + "%"), nStart, nLimit);
	}
	
	/**
	 * 获取树结点列表记录总数
	 * 
	 * @param nAreaId
	 * @param sName
	 * @return Long
	 */
	public Long getAreaListTotal(Integer nAreaId, String sName)
	{
		return getTotal("FROM Area WHERE nAreaId=? AND sName LIKE ?",
				new Field().addInt(nAreaId).addStr("%" + sName + "%"));
	}
	
	/**
	 * 增加一个树结点
	 * 
	 * @param oArea
	 * @return int
	 */
	public int addArea(Area oArea)
	{
		int nPKId = 0;
		if (insert(oArea) > 0)
		{
			update("UPDATE Area A SET A.sPath=CONCAT((SELECT B.sPath FROM Area B WHERE B.nAreaId=A.nPId), A.nAreaId, '/') WHERE A.sPath IS NULL");
			nPKId = oArea.getAreaId();
		}
		return nPKId;
	}
	
	/**
	 * 修改一个树结点
	 * 
	 * @param oArea
	 * @return int
	 */
	public int editArea(Area oArea)
	{
		return update(oArea);
	}
	
	/**
	 * 删除一个树结点
	 * 
	 * @param nAreaId
	 * @return int
	 */
	public int delArea(Integer nAreaId)
	{
		int nTotal = -1;
		Area oArea = getArea(nAreaId);
		if (oArea != null)
		{
			nTotal = delete("DELETE FROM Area WHERE sPath LIKE ?",
					new Field().addStr(oArea.getPath() + "%"));
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
	public int editAreaPath(String sConsPath, String sOldPath, String sNewPath)
	{
		return update(
				"UPDATE Area SET sPath=REPLACE(sPath, ?, ?) WHERE sPath LIKE ?",
				new Field().addStr(sOldPath).addStr(sNewPath)
						.addStr(sConsPath + "%"));
	}
	
}
