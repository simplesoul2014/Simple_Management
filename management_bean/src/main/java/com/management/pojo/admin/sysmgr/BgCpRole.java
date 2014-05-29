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

/**
 * 后台用户 的POJO
 * @Author 姚林勇
 * @Date 2012-12-25
 * @Version 1.0
 * @Remark
 */

@Entity
@Table(name = "BG_CPROLE")
public class BgCpRole implements Serializable {
	private static final long serialVersionUID = 3421717982944164843L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_ROLEID")
	private Integer nRoleId;
	@Column(name = "S_NAME")
	private String sName;
	@Column(name = "D_BEGDATE")
	private Date dBegdate;
	@Column(name = "D_ENDDATE")
	private Date dEnddate;
	@Column(name = "S_MODIFIER")
	private String sModifier;
	@Column(name = "D_MODIFYDATE")
	private Date dModifydate;
	@Column(name = "N_FLAG")
	private Integer nFlag;
	public Integer getRoleId() {
		return nRoleId;
	}
	public void setRoleId(Integer nRoleId) {
		this.nRoleId = nRoleId;
	}
	public String getName() {
		return sName;
	}
	public void setName(String sName) {
		this.sName = sName;
	}
	public Date getBegdate() {
		return dBegdate;
	}
	public void setBegdate(Date dBegdate) {
		this.dBegdate = dBegdate;
	}
	public Date getEnddate() {
		return dEnddate;
	}
	public void setEnddate(Date dEnddate) {
		this.dEnddate = dEnddate;
	}
	public String getModifier() {
		return sModifier;
	}
	public void setModifier(String sModifier) {
		this.sModifier = sModifier;
	}
	public Date getModifydate() {
		return dModifydate;
	}
	public void setModifydate(Date dModifydate) {
		this.dModifydate = dModifydate;
	}
	public Integer getFlag() {
		return nFlag;
	}
	public void setFlag(Integer nFlag) {
		this.nFlag = nFlag;
	}
	
}
