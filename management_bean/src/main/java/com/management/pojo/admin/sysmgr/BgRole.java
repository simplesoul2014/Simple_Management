package com.management.pojo.admin.sysmgr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * 后台角色 的POJO
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "BG_ROLE")
public class BgRole implements Serializable
{
	private static final long serialVersionUID = 4678495852636674297L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_BGROLEID")
	private Integer nBgRoleId;
	@Column(name = "S_NAME")
	private String sName;
	@Column(name = "S_REMARK")
	private String sRemark;
	
	public Integer getBgRoleId()
	{
		return nBgRoleId;
	}
	
	public void setBgRoleId(Integer nBgRoleId)
	{
		this.nBgRoleId = nBgRoleId;
	}
	
	public String getName()
	{
		return sName;
	}
	
	public void setName(String sName)
	{
		this.sName = sName;
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
