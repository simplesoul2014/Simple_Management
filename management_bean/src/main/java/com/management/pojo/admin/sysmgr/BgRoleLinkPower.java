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
@Table(name = "BG_ROLELINKPOWER")
public class BgRoleLinkPower implements Serializable {
	private static final long serialVersionUID = 3421717982944164843L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_RPID")
	private Integer nRpId;
	@Column(name = "N_ROLEID")
	private Integer nRoleId;
	@Column(name = "N_POWERID")
	private Integer nPowerId;
	public Integer getRpId() {
		return nRpId;
	}
	public void setRpId(Integer nRpId) {
		this.nRpId = nRpId;
	}
	public Integer getRoleId() {
		return nRoleId;
	}
	public void setRoleId(Integer nRoleId) {
		this.nRoleId = nRoleId;
	}
	public Integer getPowerId() {
		return nPowerId;
	}
	public void setPowerId(Integer nPowerId) {
		this.nPowerId = nPowerId;
	}
	
}
