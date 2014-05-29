package com.management.module.admin.syslog.dao;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.core.db.Field;
import com.management.module.common.db.HBaseDao;
import com.management.pojo.admin.syslog.SysLog;

/**
 * 系统日志操作持久层
 * @Author 伍锐凡
 * @Date 2012-4-9
 * @Version 1.0
 * @Remark
 */
@Repository("SysLogDao")
@Scope("prototype")
public class SysLogDao extends HBaseDao<SysLog>
{
	/**
	 * 获取系统日志
	 * @param nSysLogId
	 * @return SysLog
	 */
	public SysLog getSysLog(int nSysLogId)
	{
		return getById(new Integer(nSysLogId));
	}
	
	/**
	 * 获取系统日志列表
	 * @param sName
	 * @param sContent
	 * @param nStart
	 * @param nOffset
	 * @return List<SysLog>
	 */
	public List<SysLog> getSysLogList(String sName, String sContent,
			int nStart, int nOffset)
	{
		return select(
				"FROM SysLog WHERE sName LIKE ? AND sContent LIKE ? ORDER BY nCmSysLogId DESC",
				new Field().addStr("%" + sName + "%").addStr(
						"%" + sContent + "%"), nStart, nOffset);
	}
	
	/**
	 * 获取系统日志总记录数
	 * @param sName
	 * @param sContent
	 * @return Long
	 */
	public Long getSysLogListTotal(String sName, String sContent)
	{
		return getTotal(
				"FROM SysLog WHERE sName LIKE ? AND sContent LIKE ?",
				new Field().addStr("%" + sName + "%").addStr(
						"%" + sContent + "%"));
	}
	
	/**
	 * 增加一个系统日志
	 * @param oSysLog
	 * @return int
	 */
	public int addBgPost(SysLog oSysLog)
	{
		return insert(oSysLog);
	}
}
