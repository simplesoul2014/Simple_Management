package com.management.module.admin.enums.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.management.module.admin.enums.dao.EnumDao;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.enums.Enums;

/**
 * 枚举管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Service("EnumService")
@Scope("prototype")
public class EnumService
{
	@Resource(name = "EnumDao")
	private EnumDao m_oEnumDao;
	
	/**
	 * 获取一个枚举
	 * @param nEnumId
	 * @return Enums
	 */
	public Enums getEnum(Integer nEnumId)
	{
		return m_oEnumDao.getEnum(nEnumId);
	}
	
	/**
	 * 获取枚举表
	 * @return List<Tree>
	 */
	public List<Tree> getTableList()
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<Enums> aEnums = m_oEnumDao.getTableList();
		if(aEnums != null && aEnums.size() > 0)
		{
			for(Enums oEnums : aEnums)
			{
				Tree oTree = new Tree();
				oTree.setText(oEnums.getTable());
				oTree.setId("B-" + oEnums.getTable());
				List<Enums> aEnumChild = m_oEnumDao.getFieldList(oEnums.getTable());
				if(aEnumChild != null && aEnumChild.size() > 0)
				{
					oTree.setLeaf(Boolean.FALSE);
					oTree.setCls("folder");
				}
				
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	/**
	 * 获取表字段
	 * @param sTable
	 * @return List<Tree>
	 */
	public List<Tree> getFieldList(String sTable)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<Enums> aEnums = m_oEnumDao.getFieldList(sTable);
		if(aEnums != null && aEnums.size() > 0)
		{
			for(Enums oEnums : aEnums)
			{
				Tree oTree = new Tree();
				oTree.setText(oEnums.getField());
				oTree.setId("C-" + sTable + "-" + oEnums.getField());
				List<Enums> aEnumChild = m_oEnumDao.getEnumList(sTable, oEnums.getField());
				if(aEnumChild != null && aEnumChild.size() > 0)
				{
					oTree.setLeaf(Boolean.FALSE);
					oTree.setCls("folder");
				}
				
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	/**
	 * 获取表字段的枚举
	 * @param sTable
	 * @param sField
	 * @return List<Tree>
	 */
	public List<Tree> getEnumList(String sTable, String sField)
	{
		List<Tree> aTree = new ArrayList<Tree>();
		List<Enums> aEnums = m_oEnumDao.getEnumList(sTable, sField);
		if(aEnums != null && aEnums.size() > 0)
		{
			for(Enums oEnums : aEnums)
			{
				Tree oTree = new Tree();
				oTree.setId("D-" + oEnums.getEnumId());
				oTree.setText(oEnums.getName());
				aTree.add(oTree);
			}
		}
		return aTree;
	}
	
	/**
	 * 增加枚举值
	 * @param sTable
	 * @param sField
	 * @param sName
	 * @param nValue
	 * @param sRemark
	 * @return int
	 */
	public int addEnum(String sTable, String sField, String sName, Integer nValue, String sRemark)
	{
		Enums oEnums = new Enums();
		oEnums.setTable(sTable);
		oEnums.setField(sField);
		oEnums.setName(sName);
		oEnums.setValue(nValue);
		oEnums.setRemark(sRemark);
		return m_oEnumDao.addEnum(oEnums) > 0 ? oEnums.getEnumId() : 0;
	}

	/**
	 * 修改枚举值
	 * @param nEnumId
	 * @param sName
	 * @param nValue
	 * @param sRemark
	 * @return int
	 */
	public int editEnum(Integer nEnumId, String sName, Integer nValue, String sRemark)
	{
		Enums oEnums = m_oEnumDao.getEnum(nEnumId);
		oEnums.setName(sName);
		oEnums.setValue(nValue);
		oEnums.setRemark(sRemark);
		return m_oEnumDao.editEnum(oEnums);
	}

	/**
	 * 删除枚举
	 * @param sTable
	 * @return int
	 */
	public int delEnum(String sTable)
	{
		return m_oEnumDao.delEnum(sTable);
	}
	public int delEnum(String sTable, String sField)
	{
		return m_oEnumDao.delEnum(sTable, sField);
	}
	public int delEnum(Integer nEnumId)
	{
		return m_oEnumDao.delEnum(nEnumId);
	}
	
}
