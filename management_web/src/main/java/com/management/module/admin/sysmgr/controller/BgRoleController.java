package com.management.module.admin.sysmgr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.core.util.Tools;
import com.management.module.admin.navtree.service.NavTreeService;
import com.management.module.admin.sysmgr.service.BgRoleService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.grid.Grid;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.sysmgr.BgCpRole;
import com.management.pojo.admin.sysmgr.BgRole;

/**
 * 后台角色管理控制层
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Controller
public class BgRoleController
{
	@Resource(name = "BgRoleService")
	private BgRoleService m_oBgRoleService;
	
	@Resource(name = "NavTreeService")
	private NavTreeService m_oNavTreeService;
	
	/**
	 * 获取角色列表
	 * @param oRequest
	 * @return Grid<BgRole>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgRoleList.do", method = RequestMethod.POST)
	@ResponseBody
	public Grid<BgCpRole> getBgRoleList(HttpServletRequest oRequest)
	{
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), ""); 
		int nStart = StrUtil.toInt(oRequest.getParameter("start"), 0); 
		int nLimit = StrUtil.toInt(oRequest.getParameter("limit"), 100); 
		
		List<BgCpRole> aBgRole = m_oBgRoleService.getBgRoleList(sName, nStart, nLimit);
		Long nTotal = m_oBgRoleService.getBgRoleListTotal(sName);
		
		Grid<BgCpRole> oGrid = new Grid<BgCpRole>();
		oGrid.setTotal(nTotal);
		oGrid.setStart(nStart);
		oGrid.setData(aBgRole);
		return oGrid;
	}
	
	/**
	 * 获取后台角色树
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgRoleTree.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getBgRoleTree(HttpServletRequest oRequest)
	{
		return m_oBgRoleService.getBgRoleTree(1);
	}
	
	/**
	 * 获取一个后台角色资料
	 * @param oRequest
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgRole.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBgRole(HttpServletRequest oRequest)
	{
		Map<String, Object> aReturnMap = new HashMap<String, Object>();
		Integer nBgRoleId = StrUtil.toInt(oRequest.getParameter("nBgRoleId"), 0);
		aReturnMap.put("sName", m_oBgRoleService.getBgRole(nBgRoleId).getName());
		aReturnMap.put("aNavTreeId", m_oBgRoleService.getBgRRTList(nBgRoleId));
		return aReturnMap;
	}
	
	/**
	 * 增加一个后台角色
	 * @param oRequest
	 * @param oResponse void
	 */
	@RequestMapping(value = "/module/admin/sysmgr/addBgRole.do", method = RequestMethod.POST)
	public String addBgRole(HttpServletRequest oRequest)
	{
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), "");
		String sBgTreeIdList = StrUtil.toStr(oRequest.getParameter("sBgTreeIdList"), "0");
		String sRemark = StrUtil.toStr(oRequest.getParameter("sRemark"), "");
		
		int nPKId = m_oBgRoleService.addBgRole(sName, sBgTreeIdList, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nPKId > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nPKId > 0 ? "角色增加成功" : "角色增加失败");
		oRequest.setAttribute("sResponse", oFormResponse);
		
		return "/admin/common/form_response";
	}
	
	/**
	 * 修改一个后台角色
	 * @param oRequest
	 * @param oResponse void
	 */
	@RequestMapping(value = "/module/admin/sysmgr/editBgRole.do", method = RequestMethod.POST)
	public String editBgRole(HttpServletRequest oRequest)
	{
		Integer nBgRoleId = StrUtil.toInt(oRequest.getParameter("nBgRoleId"), 0);
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), "");
		String sBgTreeIdList = StrUtil.toStr(oRequest.getParameter("sBgTreeIdList"), "0");
		String sRemark = StrUtil.toStr(oRequest.getParameter("sRemark"), "");
		
		int nTotal = m_oBgRoleService.editBgRole(nBgRoleId, sName, sBgTreeIdList, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "角色修改成功" : "角色修改失败");
		oRequest.setAttribute("sResponse", oFormResponse);
		
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除后台角色
	 * @param nBgRoleId
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/admin/sysmgr/delBgRole.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse delBgRole(int nBgRoleId)
	{
		FormResponse oFormResponse = new FormResponse();
		int nTotal = m_oBgRoleService.delBgRole(nBgRoleId);
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "后台角色删除成功" : "后台角色删除失败");
		return oFormResponse;
	}	
	
	/**
	 * 后台用户归角色
	 * @param sBgUserId
	 * @param nBgRoleId
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/admin/sysmgr/moveToRole.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse moveToRole(String sBgUserId, int nBgRoleId)
	{
		FormResponse oFormResponse = new FormResponse();
		int nTotal = m_oBgRoleService.addBgUserToRole(sBgUserId, nBgRoleId);
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "后台用户归角色成功" : "后台用户归角色失败");
		return oFormResponse;
	}
	
	/**
	 * 获取角色的导航树结点
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/sysmgr/getBgNavTree.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getBgNavTree(HttpServletRequest oRequest)
	{
		int nBgTreeId = 1;
		if (oRequest.getParameter("node") != null)
		{
			List<String> aBgTreeId = Tools.getRegexResult(
					oRequest.getParameter("node"), "[a-zA-Z_]*([0-9]+)");
			if (aBgTreeId.size() > 0)
			{
				nBgTreeId = StrUtil.toInt(aBgTreeId.get(0), 1);
				if (nBgTreeId == 0) nBgTreeId = 1;
			}
		}
		return m_oNavTreeService.getBgNavTree(nBgTreeId, Boolean.TRUE);
	}
		
}
