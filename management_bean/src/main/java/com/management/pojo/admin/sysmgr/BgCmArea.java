package com.management.pojo.admin.sysmgr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * 后台	地区	的POJO
 * @Author 廖志勇
 * @Date 2012-4-18
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_AREA")
public class BgCmArea implements Serializable
{
	@Transient
	public static final Integer BgCmArea_SJ = 2; //省的父ID
	
	private static final long serialVersionUID = 8586026786869153731L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_AREAID")
	private Integer areaId;       // 地区标识
	@Column(name = "N_PID")
	private Integer pid;          // 父地区标识
	@Column(name = "S_PATH")
	private String path;          // 地区路径
	@Column(name = "S_NAME")
	private String name;          // 地区名称
	@Column(name = "N_TYPE")
	private Integer type;         // 地区类型
	
	
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}

