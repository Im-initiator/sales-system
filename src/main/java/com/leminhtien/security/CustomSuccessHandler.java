package com.leminhtien.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.leminhtien.utils.SecurityUtils;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		String url = determineTargetUrl(authentication);
		redirectStrategy.sendRedirect(request, response, url);
	}
	
	private String determineTargetUrl(Authentication authentication) {
		String url = "";
		List<String> roles = SecurityUtils.getAuthorities();

		if(checkUrl(roles, "ADMIN")) {
			url= "/admin/home";
		}else if(checkUrl(roles, "USER")) {
			url = "/home"; 
		}
		
		return url;
	}
	
	private boolean checkUrl(List<String> roles,String role) {
		return roles.contains(role);
	}

}
