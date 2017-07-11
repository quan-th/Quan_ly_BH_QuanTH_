package com.example.demo.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (uri.contains(".do")) {
			String loginId = (String) request.getSession().getAttribute("loginId");
			if (loginId == null) {
				response.sendRedirect("/Login");
				return false;
			}
		}
		return true;
	}
}