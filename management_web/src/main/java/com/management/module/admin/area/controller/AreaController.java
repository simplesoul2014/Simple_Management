package com.management.module.admin.area.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.core.util.Tools;
import com.management.module.admin.area.service.AreaService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.grid.Grid;
import com.management.module.common.tree.Tree;
import com.management.pojo.admin.area.Area;


/**
 * 地区管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Controller
public class AreaController
{
	@Resource(name = "AreaService")
	private AreaService m_oAreaService;
	
	/**
	 * 获取地区列表
	 * 
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/area/getAreaTree.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getAreaTree(HttpServletRequest oRequest)
	{
		int nAreaId = 1;
		if (oRequest.getParameter("node") != null)
		{
			List<String> aAreaId = Tools.getRegexResult(
					oRequest.getParameter("node"), "[a-zA-Z_]*([0-9]+)");
			if (aAreaId.size() > 0)
			{
				nAreaId = StrUtil.toInt(aAreaId.get(0), 1);
				if (nAreaId == 0) nAreaId = 1;
			}
		}
		return m_oAreaService.getAreaTree(nAreaId, Boolean.FALSE);
	}
	
	@RequestMapping(value = "/module/admin/area/getAreaChildTree.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getAreaChildTree(HttpServletRequest oRequest)
	{
		int nAreaId = 2;
		if (oRequest.getParameter("node") != null)
		{
			List<String> aAreaId = Tools.getRegexResult(
					oRequest.getParameter("node"), "[a-zA-Z_]*([0-9]+)");
			if (aAreaId.size() > 0)
			{
				nAreaId = StrUtil.toInt(aAreaId.get(0), 2);
				if (nAreaId == 0) nAreaId = 2;
			}
		}
		return m_oAreaService.getAreaChildTree(nAreaId);
	}
	
	@RequestMapping(value = "/module/admin/area/getAreaList.do", method = RequestMethod.POST)
	@ResponseBody
	public Grid<Area> getAreaList(HttpServletRequest oRequest)
	{
		String sName = StrUtil.toStr(oRequest.getParameter("sName"), ""); 
		int nAreaId = StrUtil.toInt(oRequest.getParameter("nAreaId"), 0); 
		int nStart = StrUtil.toInt(oRequest.getParameter("start"), 0); 
		int nLimit = StrUtil.toInt(oRequest.getParameter("limit"), 100); 
		
		List<Area> aArea = m_oAreaService.getAreaList(nAreaId, sName, nStart, nLimit);
		Long nTotal = m_oAreaService.getAreaListTotal(nAreaId, sName);
		
		Grid<Area> oGrid = new Grid<Area>();
		oGrid.setTotal(nTotal);
		oGrid.setStart(nStart);
		oGrid.setData(aArea);
		return oGrid;
	}
	
	/**
	 * 地区管理
	 * 
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/area/index.do", method = RequestMethod.GET)
	public String getAreaIndex(HttpServletRequest oRequest)
	{
		return "/admin/area/index";
	}
	
	/**
	 * 移到地区
	 * 
	 * @param nDestAreaId
	 * @param nSrcAreaId
	 * @return Map<String,Object>
	 */
	@RequestMapping(value = "/module/admin/area/editMoveArea.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse editMoveArea(int nDestAreaId,
			int nSrcAreaId)
	{
		int nTotal = m_oAreaService.editMoveArea(nDestAreaId,
				nSrcAreaId);
		
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "地区移动成功" : "地区移动失败");
		return oFormResponse;
	}
	
	/**
	 * 获取结点信息
	 * 
	 * @param nAreaId
	 * @return Area
	 */
	@RequestMapping(value = "/module/admin/area/getArea.do", method = RequestMethod.POST)
	@ResponseBody
	public Area getArea(int nAreaId)
	{
		return m_oAreaService.getArea(nAreaId);
	}
	
	/**
	 * 增加一个地区
	 * 
	 * @param nPId
	 * @param sName
	 * @param nType
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/area/addArea.do", method = RequestMethod.POST)
	public String addArea(HttpServletRequest oRequest, int nPId, String sName, int nType)
	{
		int nPKId = m_oAreaService.addArea(nPId, sName,	nType);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nPKId > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nPKId > 0 ? "地区增加成功" : "地区增加失败");
		oFormResponse.setParam("sName", sName);
		oFormResponse.setParam("nAreaId", nPKId);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 修改一个地区
	 * 
	 * @param nAreaId
	 * @param sName
	 * @param nType
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/area/editArea.do", method = RequestMethod.POST)
	public String editArea(HttpServletRequest oRequest, int nAreaId, String sName, int nType)
	{
		int nTotal = m_oAreaService.editArea(nAreaId, sName, nType);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "地区修改成功" : "地区修改失败");
		oFormResponse.setParam("sName", sName);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除一个地区
	 * 
	 * @param nAreaId
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/admin/area/delArea.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse delArea(int nAreaId)
	{
		int nTotal = m_oAreaService.delArea(nAreaId);
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "地区删除成功" : "地区删除失败");
		return oFormResponse;
	}
}
