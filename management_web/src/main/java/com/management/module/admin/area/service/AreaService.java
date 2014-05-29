package com.management.module.admin.area.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.management.module.admin.area.dao.AreaDao;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.area.Area;

/**
 * 地区管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Service("AreaService")
@Scope("prototype")
public class AreaService
{
	@Resource(name = "AreaDao")
	private AreaDao m_oAreaDao;
	
	/**
	 * 获取地区
	 * @param nAreaId
	 * @return List<Tree>
	 */
	public List<Tree> getAreaTree(Integer nAreaId, Boolean bIsCheckBoxTree)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<Area> aArea = m_oAreaDao.getAreaList(nAreaId);
		
		if(aArea != null && aArea.size() > 0)
		{
			for(Area oArea : aArea)
			{
				Tree oTree = new Tree();
				oTree.setText(oArea.getName());
				oTree.setId("AreaId_" + String.valueOf(oArea.getAreaId()));
				if(bIsCheckBoxTree) oTree.setChecked(Boolean.FALSE);
				List<Area> aAreaChild = m_oAreaDao.getAreaList(oArea.getAreaId());
				if(aAreaChild != null && aAreaChild.size() > 0)
				{
					oTree.setLeaf(Boolean.FALSE);
					oTree.setCls("folder");
					if(nAreaId == 1) 
					{
						oTree.setExpanded(Boolean.TRUE);
						oTree.setChildren(getAreaTree(oArea.getAreaId(), bIsCheckBoxTree));
					}
				}
				
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	public List<Tree> getAreaChildTree(Integer nAreaId)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<Area> aArea = m_oAreaDao.getAreaList(nAreaId);
		
		if(aArea != null && aArea.size() > 0)
		{
			for(Area oArea : aArea)
			{
				Tree oTree = new Tree();
				oTree.setText(oArea.getName());
				oTree.setId("AreaId_" + String.valueOf(oArea.getAreaId()));
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	/**
	 * 获取一个地区
	 * @param nAreaId
	 * @return Area
	 */
	public Area getArea(Integer nAreaId)
	{
		return m_oAreaDao.getArea(nAreaId);
	}
	
	/**
	 * 获取地区列表
	 * @param nAreaId
	 * @param sName
	 * @return List<Area>
	 */
	public List<Area> getAreaList(Integer nAreaId, String sName, int nStart, int nLimit)
	{
		return m_oAreaDao.getAreaList(nAreaId, sName, nStart, nLimit);
	}
	public Long getAreaListTotal(Integer nAreaId, String sName)
	{
		return m_oAreaDao.getAreaListTotal(nAreaId, sName);
	}
	
	/**
	 * 增加一个地区
	 * @param nPId
	 * @param sName
	 * @param nType
	 * @return int
	 */
	public int addArea(int nPId, String sName, int nType)
	{
		Area oArea = new Area();
		oArea.setPId(nPId);
		oArea.setName(sName);
		oArea.setType(nType);
		return m_oAreaDao.addArea(oArea);
	}
	
	/**
	 * 修改一个地区
	 * @param nAreaId
	 * @param sName
	 * @param nType
	 * @return int
	 */
	public int editArea(int nAreaId, String sName, int nType)
	{
		Area oArea = getArea(nAreaId);
		if(oArea == null) return -1;
		oArea.setName(sName);
		oArea.setType(nType);
		return m_oAreaDao.editArea(oArea);
	}
	
	/**
	 * 删除一个地区
	 * @param nAreaId
	 * @return int
	 */
	public int delArea(Integer nAreaId)
	{
		return m_oAreaDao.delArea(nAreaId);
	}
	
	/**
	 * 移动地区
	 * @param nDestAreaId
	 * @param nSrcAreaId
	 * @return int
	 */
	public int editMoveArea(int nDestAreaId, int nSrcAreaId)
	{
		int nTotal = -1;
		Area oSrcArea = getArea(nSrcAreaId);
		Area oDestArea = getArea(nDestAreaId);
		if(oSrcArea != null && oDestArea != null)
		{
			Area oSrcParentArea = getArea(oSrcArea.getPId());
			
			oSrcArea.setPId(oDestArea.getAreaId());
			nTotal = m_oAreaDao.editArea(oSrcArea);
			if(oSrcParentArea != null)
			{
				m_oAreaDao.editAreaPath(oSrcArea.getPath(), oSrcParentArea.getPath(), oDestArea.getPath());
			}
		}
		return nTotal;
	}
	
}
