package com.hms.indus.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{
	
	RequestDispatcher requestDispatcher;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		String url=httpServletRequest.getRequestURI();
		HttpSession httpSession=httpServletRequest.getSession();
		if(url.contains("api"))
		{
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		if(url.endsWith("login"))
		{
			requestDispatcher=httpServletRequest.getRequestDispatcher("login");
			requestDispatcher.forward(httpServletRequest, httpServletResponse);
			return;
		}
		if(url.contains("resources") || url.contains("images") && url.contains("ImageServlet") || url.endsWith("isAvailEmailId") || url.endsWith("isAvailUserName"))
		{
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		if(url.endsWith("/"))
		{
			httpServletResponse.sendRedirect("indus/login");
			return;
		}
		
		if(httpSession.getAttribute("userName")!=null && httpSession.getAttribute("password")!=null)
		{
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		else
		{
			if(url.contains("loginAuthentication"))
			{
				requestDispatcher=httpServletRequest.getRequestDispatcher("loginAuthentication");
				requestDispatcher.forward(httpServletRequest, httpServletResponse);
			}
			else if(url.contains("resetPassword"))
			{
				requestDispatcher=httpServletRequest.getRequestDispatcher("resetPassword");
				requestDispatcher.forward(httpServletRequest, httpServletResponse);
			}
			else if(url.contains("incorrectLogin"))
			{
				chain.doFilter(httpServletRequest, httpServletResponse);
				return;
			}
			else
			{
				httpServletResponse.sendRedirect("login");
				return;
			}
		}
			
		
		/*if(url.contains("resetPassword"))
		{
			requestDispatcher=httpServletRequest.getRequestDispatcher("resetPassword");
			requestDispatcher.forward(httpServletRequest, httpServletResponse);
		}
		else
		{
			httpServletResponse.sendRedirect("login");
			return;
		}*/
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	
}
