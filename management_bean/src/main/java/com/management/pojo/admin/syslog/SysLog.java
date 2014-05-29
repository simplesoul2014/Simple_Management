package com.management.pojo.admin.syslog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 系统日志
 * @Author 伍锐凡
 * @Date 2012-4-9
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_SYSLOG")
public class SysLog implements Serializable
{
	private static final long serialVersionUID = -6928825850487661551L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_CMSYSLOGID")
	private Integer nCmSysLogId;
	@Column(name = "N_BGUSERID")
	private Integer nBgUserId;
	@Column(name = "S_NAME")
	private String sName;
	@Column(name = "N_MODULEID")
	private Integer nModuleId;
	@Column(name = "S_MODULENAME")
	private String sModuleName;

	@Column(name = "S_CONTENT")
	private String sContent;
	@Column(name = "D_CREATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dCreateDate = new Date();
	
	public Integer getCmSysLogId()
	{
		return nCmSysLogId;
	}
	
	public void setCmSysLogId(Integer nCmSysLogId)
	{
		this.nCmSysLogId = nCmSysLogId;
	}
	
	public Integer getBgUserId()
	{
		return nBgUserId;
	}
	
	public void setBgUserId(Integer nBgUserId)
	{
		this.nBgUserId = nBgUserId;
	}
	
	public String getName()
	{
		return sName;
	}
	
	public void setName(String sName)
	{
		this.sName = sName;
	}
	
	public Integer getModuleId()
	{
		return nModuleId;
	}
	
	public void setModuleId(Integer nModuleId)
	{
		this.nModuleId = nModuleId;
	}	
	
	public String getModuleName()
	{
		return sModuleName;
	}

	public void setModuleName(String sModuleName)
	{
		this.sModuleName = sModuleName;
	}	
	
	public String getContent()
	{
		return sContent;
	}
	
	public void setContent(String sContent)
	{
		this.sContent = sContent;
	}
	
	public Date getCreateDate()
	{
		return dCreateDate;
	}
	
	public void setCreateDate(Date dCreateDate)
	{
		this.dCreateDate = dCreateDate;
	}
}
