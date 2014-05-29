package com.management.module.admin.enums.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.enums.Enums;

/**
 * 枚举管理持久层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Repository("EnumDao")
@Scope("prototype")
public class EnumDao extends HBaseDao<Enums>
{
	/**
	 * 获取枚举
	 * @param nEnumId
	 * @return Enums
	 */
	public Enums getEnum(Integer nEnumId)
	{
		return getById(nEnumId);
	}
	
	/**
	 * 获取枚举表
	 * @return List<Enums>
	 */
	public List<Enums> getTableList()
	{
		return select("SELECT new Enums(sTable, '') FROM Enums GROUP BY sTable ORDER BY sTable");
	}
	
	/**
	 * 获取表字段
	 * @param sTable
	 * @return List<Enums>
	 */
	public List<Enums> getFieldList(String sTable)
	{
		return select("SELECT new Enums('', sField) FROM Enums WHERE sTable=? GROUP BY sField ORDER BY sField", new Field().addStr(sTable));
	}
	
	/**
	 * 获取表字段的枚举
	 * @param sTable
	 * @param sField
	 * @return List<Enums>
	 */
	public List<Enums> getEnumList(String sTable, String sField)
	{
		return select("FROM Enums WHERE sTable=? AND sField=? ORDER BY nValue", new Field().addStr(sTable).addStr(sField));
	}
	
	/**
	 * 增加枚举值
	 * @param oEnums
	 * @return int
	 */
	public int addEnum(Enums oEnums)
	{
		return insert(oEnums);
	}

	/**
	 * 修改枚举值
	 * @param oEnums
	 * @return int
	 */
	public int editEnum(Enums oEnums)
	{
		return update(oEnums);
	}

	/**
	 * 删除枚举
	 * @param sTable
	 * @return int
	 */
	public int delEnum(String sTable)
	{
		return delete("DELETE FROM Enums WHERE sTable=?",
					new Field().addStr(sTable));
	}
	public int delEnum(String sTable, String sField)
	{
		return delete("DELETE FROM Enums WHERE sTable=? AND sField=?",
					new Field().addStr(sTable).addStr(sField));
	}
	public int delEnum(Integer nEnumId)
	{
		return delete("DELETE FROM Enums WHERE nEnumId=?",
					new Field().addInt(nEnumId));
	}
	
}
