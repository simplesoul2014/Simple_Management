package com.management.pojo.admin.sysmgr;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GenericGenerator;


/**
 * 后台用户 的POJO
 * @Author 姚林勇
 * @Date 2012-12-25
 * @Version 1.0
 * @Remark
 */

@Entity
@Table(name = "BG_CPUSER")
public class BgCpUser implements Serializable {
	private static final long serialVersionUID = 3421717982944164843L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
//	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
//			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
//			valueColumnName = "N_SERIALID", allocationSize = 1)
	
	@Id
	@GenericGenerator(name = "SerialGenerator", strategy = "assigned") 
	@Column(name = "S_USER_ID")
	private String sUserId;
	@Column(name = "S_USER_NAME")
	private String sUserName;
	@Column(name = "S_PASSWORD")
	private String sPassword;
	@Column(name = "N_LOGIN_TYPE")
	private Integer nLoginType;
	@Column(name = "S_USER_CREATOR")
	private String sUserCreator;
	@Column(name = "D_CREAT_TIME")
	private Date dCreatTime;
	@Column(name = "D_LAST_TIME")
	private Date dLastTime;
	@Column(name = "D_PASSWD_EXPIRE")
	private Date dPasswdExpire;
	@Column(name = "D_USER_EXPIRE")
	private Date dUserExpire;
	@Column(name = "S_ROLE")
	private String sRole;
	@Column(name = "N_ROLEID")
	private Integer nRoleId;
	@Column(name = "S_STATUS")
	private Integer sStatus;
	
	public String getUserId() {
		return sUserId;
	}
	public void setUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	public String getUserName() {
		return sUserName;
	}
	public void setUserName(String sUserName) {
		this.sUserName = sUserName;
	}
	public String getPassword() {
		return sPassword;
	}
	public void setPassword(String sPassword) {
		this.sPassword = sPassword;
	}
	public Integer getLoginType() {
		return nLoginType;
	}
	public void setLoginType(Integer nLoginType) {
		this.nLoginType = nLoginType;
	}
	public String getUserCreator() {
		return sUserCreator;
	}
	public void setUserCreator(String sUserCreator) {
		this.sUserCreator = sUserCreator;
	}
	public Date getCreatTime() {
		return dCreatTime;
	}
	public void setCreatTime(Date dCreatTime) {
		this.dCreatTime = dCreatTime;
	}
	public Date getLastTime() {
		return dLastTime;
	}
	public void setLastTime(Date dLastTime) {
		this.dLastTime = dLastTime;
	}
	public Date getPasswdExpire() {
		return dPasswdExpire;
	}
	public void setPasswdExpire(Date dPasswdExpire) {
		this.dPasswdExpire = dPasswdExpire;
	}
	public Date getUserExpire() {
		return dUserExpire;
	}
	public void setUserExpire(Date dUserExpire) {
		this.dUserExpire = dUserExpire;
	}
	public String getRole() {
		return sRole;
	}
	public void setRole(String sRole) {
		this.sRole = sRole;
	}
	
	public Integer getRoleId() {
		return nRoleId;
	}
	public void setRoleId(Integer nRoleId) {
		this.nRoleId = nRoleId;
	}
	public Integer getStatus() {
		return sStatus;
	}
	public void setStatus(Integer sStatus) {
		this.sStatus = sStatus;
	}
	
}
