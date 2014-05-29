package com.management.module.admin.main.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 后台主入口
 * @Author 伍锐凡
 * @Date 2012-4-1
 * @Version 1.0
 * @Remark
 */
@Controller
public class MainController
{
	@RequestMapping(value = "/module/admin/main/index.do", method = RequestMethod.GET)
	public String getNavTreeIndex(HttpServletRequest oRequest)
	{
		return "/admin/main/index";
	}
}
