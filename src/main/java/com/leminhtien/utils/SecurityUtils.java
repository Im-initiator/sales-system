package com.leminhtien.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.leminhtien.dto.MyUser;

public class SecurityUtils {
	
	public static MyUser getPrincipal() {
		MyUser user =(MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities(){
		List<String> result = new ArrayList<String>();
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		for(GrantedAuthority authority : authorities) {
			result.add(authority.getAuthority());
		}
		return result;
		
		
	}

}
