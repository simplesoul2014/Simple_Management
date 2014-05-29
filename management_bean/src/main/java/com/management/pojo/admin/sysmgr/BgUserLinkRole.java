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
 * 后台用户 的POJO
 * @Author 姚林勇
 * @Date 2012-12-25
 * @Version 1.0
 * @Remark
 */

@Entity
@Table(name = "BG_USERLINKROLE")
public class BgUserLinkRole implements Serializable {
	private static final long serialVersionUID = 3421717982944164843L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_URID")
	private Integer nUrId;
	@Column(name = "S_USER_ID")
	private String sUserId;
	@Column(name = "N_ROLEID")
	private Integer nRoleId;
	@Column(name = "N_FLAG")
	private Integer nFlag;
	public Integer getUrId() {
		return nUrId;
	}
	public void setUrId(Integer nUrId) {
		this.nUrId = nUrId;
	}
	
	
	public String getUserId() {
		return sUserId;
	}
	public void setUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	public Integer getRoleId() {
		return nRoleId;
	}
	public void setRoleId(Integer nRoleId) {
		this.nRoleId = nRoleId;
	}
	public Integer getFlag() {
		return nFlag;
	}
	public void setFlag(Integer nFlag) {
		this.nFlag = nFlag;
	}
	
}
