package com.management.module.admin.syslog.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.management.module.admin.navtree.dao.NavTreeDao;
import com.management.module.admin.syslog.dao.SysLogDao;
import com.management.module.admin.sysmgr.dao.BgUserDao;
import com.management.pojo.admin.syslog.SysLog;
import com.management.pojo.admin.sysmgr.BgCpUser;
import com.management.pojo.admin.sysmgr.BgPower;

/**
 * 系统日志操作逻辑层
 * @Author 伍锐凡
 * @Date 2012-4-9
 * @Version 1.0
 * @Remark
 */
@Service("SysLogService")
@Scope("prototype")
public class SysLogService
{
	@Resource(name = "SysLogDao")
	private SysLogDao m_oSysLogDao;
	
	@Resource(name = "BgUserDao")
	private BgUserDao m_oBgUserDao;
	
	@Resource(name = "NavTreeDao")
	private NavTreeDao m_oNavTreeDao;
	
	/**
	 * 获取系统日志
	 * @param nSysLogId
	 * @return SysLog
	 */
	public SysLog getSysLog(int nSysLogId)
	{
		return m_oSysLogDao.getSysLog(nSysLogId);
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
		return m_oSysLogDao.getSysLogList(sName, sContent, nStart, nOffset);
	}
	
	/**
	 * 获取系统日志总记录数
	 * @param sName
	 * @param sContent
	 * @return Long
	 */
	public Long getSysLogListTotal(String sName, String sContent)
	{
		return m_oSysLogDao.getSysLogListTotal(sName, sContent);
	}
	
	/**
	 * 增加一个系统日志
	 * @param sUserId
	 * @param nModuleId
	 * @param sContent
	 * @return int
	 */
	public int addSysLog(String sUserId, Integer nModuleId, String sContent)
	{
		SysLog oSysLog = new SysLog();
		
		//操作用户
		BgCpUser oBgUser = m_oBgUserDao.getBgUser(sUserId);
		oSysLog.setBgUserId(Integer.parseInt(sUserId));
		oSysLog.setName(oBgUser ==  null || oBgUser.getUserName() == null ? "" : oBgUser.getUserName());
		
		//操作模块
		oSysLog.setModuleId(nModuleId);
		BgPower oBgTree = m_oNavTreeDao.getTreeNode(nModuleId);
		oSysLog.setModuleName(oBgTree ==  null || oBgTree.getPowerName() == null ? "" : oBgTree.getPowerName());
		
		//操作内容
		oSysLog.setContent(sContent);
		
		return m_oSysLogDao.addBgPost(oSysLog);
	}	
}
