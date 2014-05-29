package com.management.module.admin.navtree.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.core.util.Tools;
import com.management.module.admin.navtree.service.NavTreeService;
import com.management.module.common.form.FormResponse;
import com.management.module.common.tree.Tree;

import com.management.pojo.admin.sysmgr.BgPower;

/**
 * 后台导航树管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-30
 * @Version 1.0
 * @Remark
 */
@Controller
public class NavTreeController
{
	@Resource(name = "NavTreeService")
	private NavTreeService m_oNavTreeService;
	
	/**
	 * 获取后台导航树列表
	 * 
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/navtree/getBgNavTree.do", method = RequestMethod.POST)
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
		return m_oNavTreeService.getBgNavTree(nBgTreeId, Boolean.FALSE);
	}
	
	/**
	 * 获取用户导航树
	 * @param oRequest
	 * @return List<Tree>
	 */
	@RequestMapping(value = "/module/admin/navtree/getMyBgNavTree.do", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> getMyBgNavTree(HttpServletRequest oRequest)
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
		String sUserId = (String) (oRequest.getSession().getAttribute("GlobalSessionBgUserId"));
		List<Tree> test=null;
		test=m_oNavTreeService.getMyBgNavTree(sUserId, nBgTreeId);
		return test;
		//return m_oNavTreeService.getMyBgNavTree(nBgUserId, nBgTreeId);
	}
	
	/**
	 * 后台导航树管理
	 * 
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/navtree/index.do", method = RequestMethod.GET)
	public String getNavTreeIndex(HttpServletRequest oRequest)
	{
		return "/admin/navtree/index";
	}
	
	/**
	 * 移到树结点
	 * 
	 * @param nDestBgTreeId
	 * @param nSrcBgTreeId
	 * @return Map<String,Object>
	 */
	/*@RequestMapping(value = "/module/admin/navtree/editMoveBgNavTree.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse editMoveBgNavTree(int nDestBgTreeId,
			int nSrcBgTreeId)
	{
		int nTotal = m_oNavTreeService.editMoveBgNavTree(nDestBgTreeId,
				nSrcBgTreeId);
		
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "导航结点移动成功" : "导航结点移动失败");
		return oFormResponse;
	}*/
	
	/**
	 * 获取结点信息
	 * 
	 * @param nBgTreeId
	 * @return BgTree
	 */
	@RequestMapping(value = "/module/admin/navtree/getBgNavTreeNode.do", method = RequestMethod.POST)
	@ResponseBody
	public BgPower getBgNavTreeNode(int nBgTreeId)
	{ 
		BgPower test=null;
		test=m_oNavTreeService.getTreeNode(nBgTreeId);
		return m_oNavTreeService.getTreeNode(nBgTreeId);
	}
	
	/**
	 * 增加一个导航结点
	 * 
	 * @param nPId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/navtree/addBgNavTreeNode.do", method = RequestMethod.POST)
	public String addBgNavTreeNode(HttpServletRequest oRequest, int nPId,
			String sName, String sUrl, String sRemark)
	{
		int nPKId = m_oNavTreeService.addBgNavTreeNode(nPId, sName, sUrl,
				sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nPKId > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nPKId > 0 ? "导航结点增加成功" : "导航结点增加失败");
		oFormResponse.setParam("sName", sName);
		oFormResponse.setParam("nBgTreeId", nPKId);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 修改一个导航结点
	 * 
	 * @param nBgTreeId
	 * @param sName
	 * @param sUrl
	 * @param sRemark
	 * @return String
	 */
	@RequestMapping(value = "/module/admin/navtree/editBgNavTreeNode.do", method = RequestMethod.POST)
	public String editBgNavTreeNode(HttpServletRequest oRequest, int nBgTreeId, String sName,
			String sUrl, String sRemark)
	{
		int nTotal = m_oNavTreeService.editBgNavTreeNode(nBgTreeId, sName,
				sUrl, sRemark);
		
		//用Freemaker呈现结果
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "导航结点修改成功" : "导航结点修改失败");
		oFormResponse.setParam("sName", sName);
		oRequest.setAttribute("sResponse", oFormResponse);
		return "/admin/common/form_response";
	}
	
	/**
	 * 删除一个导航结点
	 * 
	 * @param nBgTreeId
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/admin/navtree/delBgNavTreeNode.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse delBgNavTreeNode(int nBgTreeId)
	{
		int nTotal = m_oNavTreeService.delTreeNode(nBgTreeId);
		FormResponse oFormResponse = new FormResponse();
		oFormResponse.setSuccess(nTotal > 0 ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(nTotal > 0 ? "导航结点删除成功" : "导航结点删除失败");
		return oFormResponse;
	}
}
