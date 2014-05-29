package com.management.pojo.admin.enums;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 枚举POJO
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_ENUM")
public class Enums implements Serializable
{
	private static final long serialVersionUID = -5341948406083687531L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_ENUMID")
	private Integer nEnumId;	
	@Column(name = "S_TABLE")
	private String sTable;
	@Column(name = "S_FIELD")
	private String sField;
	@Column(name = "S_NAME")
	private String sName;	
	@Column(name = "N_VALUE")
	private Integer nValue = 1;	
	@Column(name = "S_REMARK")
	private String sRemark;
	
	public Enums()
	{
		
	}
	
	public Enums(String sTable, String sField)
	{
		this.sTable = sTable;
		this.sField = sField;
	}
	
	public Integer getEnumId()
	{
		return nEnumId;
	}
	
	public void setEnumId(Integer nEnumId)
	{
		this.nEnumId = nEnumId;
	}
	
	public String getTable()
	{
		return sTable;
	}
	
	public void setTable(String sTable)
	{
		this.sTable = sTable;
	}
	
	public String getField()
	{
		return sField;
	}
	
	public void setField(String sField)
	{
		this.sField = sField;
	}
	
	public String getName()
	{
		return sName;
	}
	
	public void setName(String sName)
	{
		this.sName = sName;
	}
	
	public Integer getValue()
	{
		return nValue;
	}
	
	public void setValue(Integer nValue)
	{
		this.nValue = nValue;
	}
	
	public String getRemark()
	{
		return sRemark;
	}
	
	public void setRemark(String sRemark)
	{
		this.sRemark = sRemark;
	}	
}
