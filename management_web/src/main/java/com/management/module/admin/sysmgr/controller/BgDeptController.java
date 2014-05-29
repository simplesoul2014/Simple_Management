package com.management.module.admin.sysmgr.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.core.util.Tools;
import com.management.module.admin.sysmgr.service.BgDeptService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.sysmgr.BgDept;

/**
 * 后台部门管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Controller
public class BgDeptController
{
	@Resource(name = "BgDeptService")
	private BgDeptService m_oBgDeptService;
	
	/**
	 * 获取后台部门列表
	 * 
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgDeptList.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getBgDeptList(HttpServletRequest oRequest)
	{
		int nBgDeptId = 1;
		if (oRequest.getParameter("node") != null)
		{
			List<String> aBgDeptId = Tools.getRegexResult(
					oRequest.getParameter("node"), "[a-zA-Z_]*([0-9]+)");
			if (aBgDeptId.size() > 0)
			{
				nBgDeptId = StrUtil.toInt(aBgDeptId.get(0), 1);
				if (nBgDeptId == 0) nBgDeptId = 1;
			}
		}
		return m_oBgDeptService.getBgDept(nBgDeptId, Boolean.FALSE);
	}
	
	/**
	 * 后台部门管理
	 * 
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/sysmgr/bg_dept.do", method = RequestMethod.GET)
	public String getBgDeptIndex(HttpServletRequest oRequest)
	{
		return "/admin/sysmgr/bg_dept";
	}
	
	/**
	 * 移到部门
	 * 
	 * @param nDestBgDeptId
	 * @param nSrcBgDeptId
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/admin/sysmgr/editMoveBgDept.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse editMoveBgDept(int nDestBgDeptId,
			int nSrcBgDeptId)
	{
		int nTotal = m_oBgDeptService.editMoveBgDept(nDestBgDeptId,
				nSrcBgDeptId);
		
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "后台部门移动成功" : "后台部门移动失败");
		return oFormResponse;
	}
	
	/**
	 * 获取部门信息
	 * 
	 * @param nBgDeptId
	 * @return BgDept
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgDept.do", method = RequestMethod.POST)
	@ResponseBody
	public BgDept getBgDept(int nBgDeptId)
	{
		return m_oBgDeptService.getBgDept(nBgDeptId);
	}
	
	/**
	 * 增加一个后台部门
	 * 
	 * @param nPId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/sysmgr/addBgDept.do", method = RequestMethod.POST)
	public String addBgDept(HttpServletRequest oRequest, int nPId,
			String sName, String sRemark)
	{
		int nPKId = m_oBgDeptService.addBgDept(nPId, sName,
				sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nPKId > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nPKId > 0 ? "后台部门增加成功" : "后台部门增加失败");
		oFormResponse.setParam("sName", sName);
		oFormResponse.setParam("nBgDeptId", nPKId);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 修改一个后台部门
	 * 
	 * @param nBgDeptId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/sysmgr/editBgDept.do", method = RequestMethod.POST)
	public String editBgDept(HttpServletRequest oRequest, int nBgDeptId, String sName, String sRemark)
	{
		int nTotal = m_oBgDeptService.editBgDept(nBgDeptId, sName, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "后台部门修改成功" : "后台部门修改失败");
		oFormResponse.setParam("sName", sName);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除一个后台部门
	 * 
	 * @param nBgDeptId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/delBgDept.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse delBgDept(int nBgDeptId)
	{
		int nTotal = m_oBgDeptService.delBgDept(nBgDeptId);
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "后台部门删除成功" : "后台部门删除失败");
		return oFormResponse;
	}
}
