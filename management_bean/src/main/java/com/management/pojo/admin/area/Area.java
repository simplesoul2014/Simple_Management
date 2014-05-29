package com.management.pojo.admin.area;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 地区POJO
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_AREA")
public class Area implements Serializable
{
	private static final long serialVersionUID = -5009397069207638823L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_AREAID")
	private Integer nAreaId;	
	@Column(name = "N_PID")
	private Integer nPId;	
	@Column(name = "S_PATH")
	private String sPath;
	@Column(name = "S_NAME")
	private String sName;
	@Column(name = "N_TYPE")
	private Integer nType = 1;
	
	public Integer getAreaId()
	{
		return nAreaId;
	}
	public void setAreaId(Integer nAreaId)
	{
		this.nAreaId = nAreaId;
	}
	public Integer getPId()
	{
		return nPId;
	}
	public void setPId(Integer nPId)
	{
		this.nPId = nPId;
	}
	public String getPath()
	{
		return sPath;
	}
	public void setPath(String sPath)
	{
		this.sPath = sPath;
	}
	public String getName()
	{
		return sName;
	}
	public void setName(String sName)
	{
		this.sName = sName;
	}
	public Integer getType()
	{
		return nType;
	}
	public void setType(Integer nType)
	{
		this.nType = nType;
	}

}
