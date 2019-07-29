package com.topica.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AdminFilter implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("token");
		if (!token.equals("admin")) {
			response.sendRedirect(request.getContextPath() + "/page-403");
			return false;
		}
		return true;
		
	}

}
