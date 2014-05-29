package com.management.module.bglogin.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.StrUtil;
import com.management.module.admin.sysmgr.service.BgUserService;
import com.management.module.common.form.FormResponse;
import com.management.pojo.admin.sysmgr.BgCpUser;

 
/**
 * 后台用户登录管理控制层
 * 
 * @Author 伍锐凡
 * @Date 2012-3-26
 * @Version 1.0
 * @Remark
 */
@Controller
public class BgLoginController
{
	@Resource(name = "BgUserService")
	private BgUserService m_oBgUserService;

	/**
	 * 获取后台用户登录主入口
	 * 
	 * @param oRequest
	 * @return String
	 */
	@RequestMapping(value = "/module/bglogin/index.do", method = RequestMethod.GET)
	public String getBgUserIndex(HttpServletRequest oRequest)
	{
		return "/bglogin/index";
	}

	/**
	 * 后台用户登录
	 * 
	 * @param oRequest
	 * @return FormResponse
	 */
	@RequestMapping(value = "/module/bglogin/login.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse login(HttpServletRequest oRequest)
	{
		String sAccount = StrUtil.toStr(oRequest.getParameter("sAccount"), "");
		String sPassword = StrUtil
				.toStr(oRequest.getParameter("sPassword"), "");
		FormResponse oFormResponse = new FormResponse();

		BgCpUser oBgUser = m_oBgUserService.getBgCpUser(sAccount, sPassword);
		if (oBgUser != null)
		{
			oRequest.getSession().setAttribute("GlobalSessionBgUserId",
					oBgUser.getUserId());
		}
		oFormResponse
				.setSuccess(oBgUser != null ? Boolean.TRUE : Boolean.FALSE);
		oFormResponse.setMsg(oBgUser != null ? "后台用户登录成功"
				: "后台用户登录失败<br/>请确认您的帐号或密码是否正确");

		return oFormResponse;
	}

	@RequestMapping(value = "/module/bglogin/logout.do", method = RequestMethod.POST)
	@ResponseBody
	public FormResponse logout(HttpServletRequest oRequest)
	{
		FormResponse oFormResponse = new FormResponse();
		Object oBgUserId = oRequest.getSession().getAttribute(
				"GlobalSessionBgUserId");
		if (oBgUserId != null)
		{
			oRequest.getSession().removeAttribute("GlobalSessionBgUserId");
		}

		oFormResponse.setSuccess(Boolean.TRUE);
		oFormResponse.setMsg("后台用户退出成功");

		return oFormResponse;
	}
}
