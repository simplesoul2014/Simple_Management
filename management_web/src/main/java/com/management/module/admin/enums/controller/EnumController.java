package com.management.module.admin.enums.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.core.util.Tools;
import com.management.module.admin.enums.service.EnumService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.enums.Enums;

/**
 * 枚举管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Controller
public class EnumController
{
	@Resource(name = "EnumService")
	private EnumService m_oEnumService;
	
	/**
	 * 枚举主入口
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/index.do", method = RequestMethod.GET)
	public String getNavTreeIndex(HttpServletRequest oRequest)
	{
		return "/admin/enums/index";
	}
	
	/**
	 * 获取枚举
	 * @param oRequest
	 * @return Enums
	 */
	@RequestMapping(value = "/module/admin/enums/getEnum.do", method = RequestMethod.POST)
	@ResponseBody
	public Enums getEnum(HttpServletRequest oRequest)
	{
		int nEnumId = StrUtil.toInt(oRequest.getParameter("nEnumId"), 0);
		return m_oEnumService.getEnum(nEnumId);
	}
	
	/**
	 * 获取枚举列表
	 * 
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/enums/getEnumList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getEnumList(HttpServletRequest oRequest)
	{
		List<Tree> aTree = null;
		List<String> aParam = null;
		String sId = StrUtil.toStr(oRequest.getParameter("node"), "A-0");
		char cFirstChar = sId.charAt(0);
		switch(cFirstChar)
		{
			case 'B' :
				aParam = Tools.getRegexResult(sId, "^B\\-(.+)");
				aTree = m_oEnumService.getFieldList(StrUtil.toStr(aParam.get(0), ""));
			break;
			case 'C' :
				aParam = Tools.getRegexResult(sId, "^C\\-(.+)\\-(.+)");
				aTree = m_oEnumService.getEnumList(StrUtil.toStr(aParam.get(0), ""), StrUtil.toStr(aParam.get(1), ""));
			break;
			default :
				aTree = m_oEnumService.getTableList();
			break;
		}
		
		return aTree;
	}
	
	/**
	 * 增加枚举
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/addEnum.do", method = RequestMethod.POST)
	public String addEnum(HttpServletRequest oRequest)
	{
		String sTable = StrUtil.toStr(oRequest.getParameter("sTable"), ""); 
		String sField = StrUtil.toStr(oRequest.getParameter("sField"), "");
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), ""); 
		int nValue = StrUtil.toInt(oRequest.getParameter("nValue"), 1);
		String sRemark = StrUtil.toStr(oRequest.getParameter("sRemark"), ""); 
		
		int nPKId = m_oEnumService.addEnum(sTable, sField, sName, nValue, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nPKId > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nPKId > 0 ? "枚举增加成功" : "枚举增加失败");
		oFormResponse.setParam("sName", sName);
		oFormResponse.setParam("nEnumId", nPKId);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 修改枚举
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/editEnum.do", method = RequestMethod.POST)
	public String editEnum(HttpServletRequest oRequest)
	{
		int nEnumId = StrUtil.toInt(oRequest.getParameter("nEnumId"), 0);
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), "");
		int nValue = StrUtil.toInt(oRequest.getParameter("nValue"), 1);
		String sRemark = StrUtil.toStr(oRequest.getParameter("sRemark"), "");
		
		int nTotal = m_oEnumService.editEnum(nEnumId, sName, nValue, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "枚举修改成功" : "枚举修改失败");
		oFormResponse.setParam("sName", sName);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除枚举表记录
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/delTable.do", method = RequestMethod.POST)
	public String delTable(HttpServletRequest oRequest)
	{
		String sTable = StrUtil.toStr(oRequest.getParameter("sTable"), ""); 
		
		int nTotal = m_oEnumService.delEnum(sTable);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "枚举表删除成功" : "枚举表删除失败");
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除枚举字段
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/delField.do", method = RequestMethod.POST)
	public String delField(HttpServletRequest oRequest)
	{
		String sTable = StrUtil.toStr(oRequest.getParameter("sTable"), ""); 
		String sField = StrUtil.toStr(oRequest.getParameter("sField"), "");
		
		int nTotal = m_oEnumService.delEnum(sTable, sField);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "枚举字段删除成功" : "枚举字段删除失败");
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除枚举
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/enums/delEnum.do", method = RequestMethod.POST)
	public String delEnum(HttpServletRequest oRequest)
	{
		int nEnumId = StrUtil.toInt(oRequest.getParameter("nEnumId"), 0);
		
		int nTotal = m_oEnumService.delEnum(nEnumId);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "枚举删除成功" : "枚举删除失败");
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}

}
