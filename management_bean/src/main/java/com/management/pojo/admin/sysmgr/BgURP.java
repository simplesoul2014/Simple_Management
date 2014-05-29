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
 * 后台岗位与用户关联 的POJO
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "BG_URP")
public class BgURP implements Serializable
{
	private static final long serialVersionUID = 178516938004182105L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_BGURPID")
	private Integer nBgURPId;
	@Column(name = "N_BGUSERID")
	private Integer nBgUserId;
	@Column(name = "N_BGPOSTID")
	private Integer nBgPostId;
	
	public Integer getBgURPId()
	{
		return nBgURPId;
	}
	public void setBgURPId(Integer nBgURPId)
	{
		this.nBgURPId = nBgURPId;
	}
	public Integer getBgUserId()
	{
		return nBgUserId;
	}
	public void setBgUserId(Integer nBgUserId)
	{
		this.nBgUserId = nBgUserId;
	}
	public Integer getBgPostId()
	{
		return nBgPostId;
	}
	public void setBgPostId(Integer nBgPostId)
	{
		this.nBgPostId = nBgPostId;
	}
}
