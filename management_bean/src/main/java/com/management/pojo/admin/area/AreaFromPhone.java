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
 * 地区号段POJO
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_PHONE_AREA")
public class AreaFromPhone implements Serializable
{
	private static final long serialVersionUID = -5009397069207638823L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_ID")
	private Integer nAreaFromPhoneId;	
	@Column(name = "S_PHONE")
	private String sPhone;
	@Column(name = "S_AREA_NAME")
	private String sAreaName;
	@Column(name = "N_AREAID")
	private Integer nAreaId;
	@Column(name = "N_PRIVINCE_CODE")
	private Integer nProvId;
	@Column(name = "S_TYPE_DESCRIBE")
	private String sDesc;
	@Column(name = "N_TYPE_STATUS")
	private Integer nStatus = 1;
	
	public Integer getAreaFromPhoneId()
	{
		return nAreaFromPhoneId;
	}
	public void setAreaFromPhoneId(Integer nAreaFromPhoneId)
	{
		this.nAreaFromPhoneId = nAreaFromPhoneId;
	}
	public String getPhone()
	{
		return sPhone;
	}
	public void setPhone(String sPhone)
	{
		this.sPhone = sPhone;
	}
	public String getAreaName()
	{
		return sAreaName;
	}
	public void setAreaName(String sAreaName)
	{
		this.sAreaName = sAreaName;
	}
	public Integer getAreaId()
	{
		return nAreaId;
	}
	public void setAreaId(Integer nAreaId)
	{
		this.nAreaId = nAreaId;
	}
	public Integer getProvId()
	{
		return nProvId;
	}
	public void setProvId(Integer nProvId)
	{
		this.nProvId = nProvId;
	}
	public String getDesc()
	{
		return sDesc;
	}
	public void setDesc(String sDesc)
	{
		this.sDesc = sDesc;
	}
	public Integer getStatus()
	{
		return nStatus;
	}
	public void setStatus(Integer nStatus)
	{
		this.nStatus = nStatus;
	}

}
