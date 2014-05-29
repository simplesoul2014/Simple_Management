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
 * 后台角色与树结点关联 的POJO
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "BG_RRT")
public class BgRRT implements Serializable
{
	private static final long serialVersionUID = 3584735225353922922L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_BGRRTID")
	private Integer nBgRRTId;
	@Column(name = "N_BGROLEID")
	private Integer nBgRoleId;
	@Column(name = "N_BGTREEID")
	private Integer nBgTreeId;
	
	public Integer getBgRRTId()
	{
		return nBgRRTId;
	}
	
	public void setBgRRTId(Integer nBgRRTId)
	{
		this.nBgRRTId = nBgRRTId;
	}
	
	public Integer getBgRoleId()
	{
		return nBgRoleId;
	}
	
	public void setBgRoleId(Integer nBgRoleId)
	{
		this.nBgRoleId = nBgRoleId;
	}
	
	public Integer getBgTreeId()
	{
		return nBgTreeId;
	}
	
	public void setBgTreeId(Integer nBgTreeId)
	{
		this.nBgTreeId = nBgTreeId;
	}
}
