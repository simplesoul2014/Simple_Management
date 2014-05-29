package com.management.module.admin.attachments.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.attachments.CMFile;

/**
 * 附件管理持久层
 * @Author 伍锐凡
 * @Date 2012-4-24
 * @Version 1.0
 * @Remark
 */
@Repository("CMFileDao")
@Scope("prototype")
public class CMFileDao extends HBaseDao<CMFile>
{
	/**
	 * 获取附件列表
	 * @param sFileId
	 * @return List<CMFile>
	 */
	public List<CMFile> getCMFile(String sFileId)
	{
		return select("FROM CMFile WHERE nFileId IN ("+sFileId+")");
	}
	
	/**
	 * 获取某一附件信息
	 * @param nCMFileId
	 * @return CMFile
	 */
	public CMFile getCMFile(Integer nCMFileId)
	{
		return getById(nCMFileId);
	}
	
	/**
	 * 增加附件
	 * @param oCMFile
	 * @return int
	 */
	public int addCMFile(CMFile oCMFile)
	{
		return insert(oCMFile);
	}
	
	/**
	 * 修改附件
	 * @param oCMFile
	 * @return int
	 */
	public int editCMFile(CMFile oCMFile)
	{
		return update(oCMFile);
	}
	
	/**
	 * 删除附件
	 * @param nCMFileId
	 * @return int
	 */
	public int delCMFile(Integer nCMFileId)
	{
		CMFile oCMFile = getCMFile(nCMFileId);
		return oCMFile == null ? -1 : delete(oCMFile);
	}
	public int delCMFile(CMFile oCMFile)
	{
		return delete(oCMFile);
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
		return select("SELECT A FROM CMFile AS A, "+sTable+" AS B WHERE A.nFileId=B.nFileId AND B."+sField+"=?", new Field().addInt(nObjectId));
	}
}
