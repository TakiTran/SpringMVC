package com.topica.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class SessionFilter implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Object token = session.getAttribute("token");
		if (token == null) {
			response.sendRedirect(request.getContextPath() + "/page-403");
			return false;
		}
		return true;
	}
}
