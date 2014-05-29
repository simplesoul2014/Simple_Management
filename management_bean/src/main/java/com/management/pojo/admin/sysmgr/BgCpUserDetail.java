package com.management.pojo.admin.sysmgr;

import java.io.Serializable;

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
@Table(name = "BG_CPUSER_DETAIL")
public class BgCpUserDetail implements Serializable {
	private static final long serialVersionUID = 3421717982944164843L;
	/*@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)*/
	@Id
	@GenericGenerator(name = "SerialGenerator", strategy = "assigned") 
	@Column(name = "S_USER_ID")
	private String sUserId;
	@Column(name = "N_USERSEX")
	private Integer nUserSex;
	@Column(name = "N_USER_TYPE")
	private Integer nUserType;
	@Column(name = "S_USERPOST")
	private String sUserPost;
	@Column(name = "S_USERQQ")
	private String sUserQq;
	@Column(name = "S_CP_ID")
	private String sCpId;
	@Column(name = "S_USEREMAIL")
	private String sUserEmail;
	@Column(name = "S_USERADRR")
	private String sUserAdrr;
	@Column(name = "S_USERDESC")
	private String sUserDesc;
	@Column(name = "S_USERTEL")
	private String sUserTel;
	@Column(name = "S_USERFAX")
	private String sUserFax;
	public String getUserId() {
		return sUserId;
	}
	public void setUserId(String sUserId) {
		this.sUserId = sUserId;
	}
	public Integer getUserSex() {
		return nUserSex;
	}
	public void setUserSex(Integer nUserSex) {
		this.nUserSex = nUserSex;
	}
	
	public Integer getUserType() {
		return nUserType;
	}
	public void setUserType(Integer nUserType) {
		this.nUserType = nUserType;
	}
	public String getUserPost() {
		return sUserPost;
	}
	public void setUserPost(String sUserPost) {
		this.sUserPost = sUserPost;
	}
	public String getUserQq() {
		return sUserQq;
	}
	public void setUserQq(String sUserQq) {
		this.sUserQq = sUserQq;
	}
	public String getCpId() {
		return sCpId;
	}
	public void setCpId(String sCpId) {
		this.sCpId = sCpId;
	}
	public String getUserEmail() {
		return sUserEmail;
	}
	public void setUserEmail(String sUserEmail) {
		this.sUserEmail = sUserEmail;
	}
	public String getUserAdrr() {
		return sUserAdrr;
	}
	public void setUserAdrr(String sUserAdrr) {
		this.sUserAdrr = sUserAdrr;
	}
	public String getUserDesc() {
		return sUserDesc;
	}
	public void setUserDesc(String sUserDesc) {
		this.sUserDesc = sUserDesc;
	}
	public String getUserTel() {
		return sUserTel;
	}
	public void setUserTel(String sUserTel) {
		this.sUserTel = sUserTel;
	}
	public String getUserFax() {
		return sUserFax;
	}
	public void setUserFax(String sUserFax) {
		this.sUserFax = sUserFax;
	}
	
}
