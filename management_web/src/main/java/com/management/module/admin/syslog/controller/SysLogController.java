package com.management.module.admin.syslog.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.management.module.admin.syslog.service.SysLogService;
import com.management.module.common.grid.Grid;
import com.management.pojo.admin.syslog.SysLog;

/**
 * 系统日志控制层
 * @Author 伍锐凡
 * @Date 2012-4-9
 * @Version 1.0
 * @Remark
 */
@Controller
public class SysLogController
{
	@Resource(name = "SysLogService")
	private SysLogService m_oSysLogService;
	
	/**
	 * 系统日志主入口
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/syslog/index.do", method = RequestMethod.GET)
	public String getSysLogIndex(HttpServletRequest oRequest)
	{
		return "/admin/syslog/index";
	}
	
	/**
	 * 获取系统日志列表
	 * @param oRequest
	 * @return Grid<SysLog>
	 */
	@RequestMapping(value = "/module/admin/syslog/getSysLogList.do", method = RequestMethod.POST)
	@ResponseBody
	public Grid<SysLog> getSysLogList(HttpServletRequest oRequest)
	{
		//获取后台用户姓名和帐号
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), ""); 
		String sContent = StrUtil.toStr(oRequest.getParameter("sContent"), ""); 
		int nStart = StrUtil.toInt(oRequest.getParameter("start"), 0); 
		int nLimit = StrUtil.toInt(oRequest.getParameter("limit"), 100); 
		
		//获取后台用户列表
		List<SysLog> aSysLog = m_oSysLogService.getSysLogList(sName, sContent, nStart, nLimit);
		Long nTotal = m_oSysLogService.getSysLogListTotal(sName, sContent);
		
		Grid<SysLog> oGrid = new Grid<SysLog>();
		oGrid.setTotal(nTotal);
		oGrid.setStart(nStart);
		oGrid.setData(aSysLog);
		return oGrid;
	}
	
	/**
	 * 获取一个系统日志资料
	 * @param oRequest
	 * @return SysLog
	 */
	@RequestMapping(value = "/module/admin/syslog/getSysLog.do", method = RequestMethod.POST)
	@ResponseBody
	public SysLog getSysLog(HttpServletRequest oRequest)
	{
		Integer nSysLogId = StrUtil.toInt(oRequest.getParameter("nSysLogId"), 0);
		return m_oSysLogService.getSysLog(nSysLogId);
	}
}
