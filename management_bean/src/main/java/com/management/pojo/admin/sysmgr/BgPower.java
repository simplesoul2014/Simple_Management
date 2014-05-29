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
@Table(name = "BG_POWER")
public class BgPower implements Serializable{
	private static final long serialVersionUID = 3421717982944164843L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_POWERID")
	private Integer nPowerId;
	@Column(name = "S_POWERNAME")
	private String sPowerName;
	@Column(name = "N_PARENTID")
	private Integer nParentId;
	@Column(name = "S_URL")
	private String sUrl;
	@Column(name = "S_REMARK")
	private String sRemark;
	@Column(name= "S_PATH")
	private String sPath;
	public Integer getPowerId() {
		return nPowerId;
	}
	public void setPowerId(Integer nPowerId) {
		this.nPowerId = nPowerId;
	}
	public String getPowerName() {
		return sPowerName;
	}
	public void setPowerName(String sPowerName) {
		this.sPowerName = sPowerName;
	}
	public Integer getParentId() {
		return nParentId;
	}
	public void setParentId(Integer nParentId) {
		this.nParentId = nParentId;
	}
	public String getUrl() {
		return sUrl;
	}
	public void setUrl(String sUrl) {
		this.sUrl = sUrl;
	}
	public String getRemark() {
		return sRemark;
	}
	public void setRemark(String sRemark) {
		this.sRemark = sRemark;
	}
	public String getPath() {
		return sPath;
	}
	public void setPath(String sPath) {
		this.sPath = sPath;
	}
	
}
