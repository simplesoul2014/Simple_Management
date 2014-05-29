package com.management.pojo.admin.attachments;

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
 * 附件POJO
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Entity
@Table(name = "CM_FILE")
public class CMFile implements Serializable
{
	private static final long serialVersionUID = -2921164524693730625L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SerialGenerator")
	@TableGenerator(name = "SerialGenerator", table = "CM_SERIAL", 
			pkColumnName = "S_SERIALNAME", pkColumnValue = "COMMON", 
			valueColumnName = "N_SERIALID", allocationSize = 1)
	@Column(name = "N_FILEID")
	private Integer nFileId;	
	@Column(name = "N_SIZE")
	private Long nSize = 0L;
	@Column(name = "N_STATUS")
	private Integer nStatus = 1;
	@Column(name = "S_SORT")
	private String sSort;
	@Column(name = "S_PATH")
	private String sPath;	
	@Column(name = "S_SNAME")
	private String sSName;	
	@Column(name = "S_ANAME")
	private String sAName;
	@Column(name = "D_CREATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dCreateTime = new Date();
	
	public Integer getFileId()
	{
		return nFileId;
	}
	public void setFileId(Integer nFileId)
	{
		this.nFileId = nFileId;
	}
	public Long getSize()
	{
		return nSize;
	}
	public void setSize(Long nSize)
	{
		this.nSize = nSize;
	}
	public Integer getStatus()
	{
		return nStatus;
	}
	public void setStatus(Integer nStatus)
	{
		this.nStatus = nStatus;
	}
	public String getSort()
	{
		return sSort;
	}
	public void setSort(String sSort)
	{
		this.sSort = sSort;
	}
	public String getPath()
	{
		return sPath;
	}
	public void setPath(String sPath)
	{
		this.sPath = sPath;
	}
	public String getSName()
	{
		return sSName;
	}
	public void setSName(String sSName)
	{
		this.sSName = sSName;
	}
	public String getAName()
	{
		return sAName;
	}
	public void setAName(String sAName)
	{
		this.sAName = sAName;
	}
	public Date getCreateTime()
	{
		return dCreateTime;
	}
	public void setCreateTime(Date dCreateTime)
	{
		this.dCreateTime = dCreateTime;
	}

	public Integer getPercent()
	{
		return 100;
	}

	public Integer getFileStatus()
	{
		return -4;
	}
	
	public Integer getId()
	{
		return 0;
	}
}
