package com.management.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 作者：伍锐凡
 * 创建：2011-3-4
 * 版本：Version 1.0
 * 描述：过滤锁
 */
public class BgLock implements Filter
{

	public void init(FilterConfig config)
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest oReq = (HttpServletRequest) request;
		HttpServletResponse oRep = (HttpServletResponse) response;
		
		if(oReq.getSession().getAttribute("GlobalSessionBgUserId") != null)
		{
			chain.doFilter(request, response);
		}else
		{
			oRep.sendRedirect(oReq.getContextPath() + "/module/bglogin/index.do");
		}
	}

	public void destroy()
	{
		
	}

}
