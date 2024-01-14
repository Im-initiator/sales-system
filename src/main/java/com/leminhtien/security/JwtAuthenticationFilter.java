package com.leminhtien.security;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.leminhtien.utils.SecurityUtils;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	RestAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {

			String requestUri = request.getRequestURI();
			int lenth = request.getContextPath().length();
			String path = requestUri.substring(lenth);
			if (!path.startsWith("/api")) {
				filterChain.doFilter(request, response);
			} else {
				path = path.substring(5);
				if (path.startsWith("admin")) {
					List<String> roles = SecurityUtils.getAuthorities();
					if (checkRole(roles, "ADMIN")) {
						String jwt = getJwtFromRequest(request);
						if (tokenProvider.validateToken(jwt)) {
							filterChain.doFilter(request, response);
						} else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				} else if(path.startsWith("saler")){
					List<String> roles = SecurityUtils.getAuthorities();
					if (checkRole(roles, "SALER")||checkRole(roles, "MANAGER")) {
						String jwt = getJwtFromRequest(request);
						if (tokenProvider.validateToken(jwt)) {
							filterChain.doFilter(request, response);
						} else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				} else if(path.startsWith("shipper")){
					List<String> roles = SecurityUtils.getAuthorities();
					if (checkRole(roles, "SHIPPER")||checkRole(roles, "MANAGER")) {
						String jwt = getJwtFromRequest(request);
						if (tokenProvider.validateToken(jwt)) {
							filterChain.doFilter(request, response);
						} else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				}else if(path.startsWith("manager")){
					List<String> roles = SecurityUtils.getAuthorities();
					if (checkRole(roles, "MANAGER")) {
						String jwt = getJwtFromRequest(request);
						if (tokenProvider.validateToken(jwt)) {
							filterChain.doFilter(request, response);
						} else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				}else if(path.startsWith("censor")){
					List<String> roles = SecurityUtils.getAuthorities();
					if (checkRole(roles, "CENSOR")||checkRole(roles, "MANAGER")) {
						String jwt = getJwtFromRequest(request);
						if (tokenProvider.validateToken(jwt)) {
							filterChain.doFilter(request, response);
						} else {
							throw new Exception();
						}
					}else {
						throw new Exception();
					}
				} else if(path.startsWith("web")){
					String jwt = getJwtFromRequest(request);
					if (tokenProvider.validateToken(jwt)) {
						filterChain.doFilter(request, response);
					} else {
						throw new Exception();
					}
				}else {
					filterChain.doFilter(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath() + "/access-denied");
			response.sendError(404,"Lỗi hệ thống!");
		}

	}

	private String getJwtFromRequest(HttpServletRequest request) throws Exception {
		String token = "";
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("tokenJWT")) {
					token = cookie.getValue();
				}
			}
		} else {
			throw new Exception("No JWT token found in request headers");
		}
		if (token.isEmpty()) {
			throw new Exception("No JWT token found in request headers");
		} else {
			return token;
		}

	}

	private boolean checkRole(List<String> roles, String role) {
		return roles.contains(role);
	}

}
