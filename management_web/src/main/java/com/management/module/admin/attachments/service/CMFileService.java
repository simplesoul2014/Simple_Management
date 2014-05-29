package com.management.module.admin.attachments.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.core.util.Tools;
import com.management.module.admin.attachments.dao.CMFileDao;
import com.management.pojo.admin.attachments.CMFile;


/**
 * 附件管理逻辑层
 * @Author 伍锐凡
 * @Date 2012-4-24
 * @Version 1.0
 * @Remark
 */
@Service("CMFileService")
@Scope("prototype")
public class CMFileService
{
	private static final Logger m_oLog = Logger.getLogger(CMFileService.class);
	
	@Resource(name = "CMFileDao")
	private CMFileDao m_oCMFileDao;
	
	/**
	 * 获取附件列表
	 * @param sFileId
	 * @return List<CMFile>
	 */
	public List<CMFile> getCMFile(String sFileId)
	{
		return m_oCMFileDao.getCMFile(sFileId);
	}
	
	/**
	 * 获取附件信息
	 * @param nCMFileId
	 * @return CMFile
	 */
	public CMFile getCMFile(Integer nCMFileId)
	{
		return m_oCMFileDao.getCMFile(nCMFileId);
	}
	
	/**
	 * 增加一个附件
	 * @param sSName
	 * @param sAName
	 * @param nSize
	 * @param sSort
	 * @param sPath
	 * @return int
	 */
	public int addCMFile(String sSName, String sAName, Long nSize, String sSort, String sPath)
	{
		CMFile oCMFile = new CMFile();
		oCMFile.setSName(sSName);
		oCMFile.setAName(sAName);
		oCMFile.setSize(nSize);
		oCMFile.setSort(sSort);
		oCMFile.setPath(sPath);
		
		return m_oCMFileDao.addCMFile(oCMFile);
	}
	public int addCMFile()
	{
		CMFile oCMFile = new CMFile();
		int nTotal = m_oCMFileDao.addCMFile(oCMFile);
		return nTotal > 0 ? oCMFile.getFileId() : 0;
	}
	
	/**
	 * 修改一个附件
	 * @param nCMFileId
	 * @param sSName
	 * @param sAName
	 * @param nSize
	 * @param sSort
	 * @param sPath
	 * @return int
	 */
	public int editCMFile(Integer nCMFileId, String sSName, String sAName, Long nSize, String sSort, String sPath)
	{
		CMFile oCMFile = m_oCMFileDao.getCMFile(nCMFileId);
		if(oCMFile == null) return -1;
		if(oCMFile.getPath() != null && oCMFile.getAName() != null)
		{
			delAttachment(oCMFile.getPath(), oCMFile.getAName());
		}
		
		oCMFile.setSName(sSName);
		oCMFile.setAName(sAName);
		oCMFile.setSize(nSize);
		oCMFile.setSort(sSort);
		oCMFile.setPath(sPath);
		oCMFile.setStatus(2);
		
		return m_oCMFileDao.editCMFile(oCMFile);
	}
	
	/**
	 * 删除一个附件
	 * @param nCMFileId
	 * @return int
	 */
	public int delCMFile(Integer nCMFileId)
	{
		CMFile oCMFile = m_oCMFileDao.getCMFile(nCMFileId);
		if(oCMFile == null) return -1;
		if(oCMFile.getPath() != null && oCMFile.getAName() != null)
		{
			delAttachment(oCMFile.getPath(), oCMFile.getAName());
		}
		return m_oCMFileDao.delCMFile(oCMFile);
	}
	
	/**
	 * 删除附件
	 * @param sPath
	 * @param sFileName
	 * @return boolean
	 */
	private boolean delAttachment(String sPath, String sFileName)
	{
		try
		{
			String sFile = Tools.getRealPath() + sPath + File.separator + sFileName;
			if(sPath.indexOf(File.separatorChar) == -1)
			{
				sFile = sFile.replaceAll(File.separatorChar == '/' ? "\\" : "/", File.separator);
				sFile = sFile.replaceAll(File.separator + File.separator, File.separator);
			}
			File oFile = new File(sFile);
			if(oFile.exists())
			{
				return oFile.delete();
			}
		}catch(Exception e)
		{
			m_oLog.error(e.getMessage(), e.getCause());
		}
		return false;
	}
	
	/**
	 * 获取附件表
	 * @param nObjectId
	 * @param sTable
	 * @param sField
	 * @return List<CMFile>
	 */
	public List<CMFile> getCMFileByObjectId(Integer nObjectId, String sTable, String sField)
	{
		return m_oCMFileDao.getCMFileByObjectId(nObjectId, sTable, sField);
	}
	
}
