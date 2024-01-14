package com.leminhtien.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.leminhtien.dto.MyUser;

public class SecurityUtils {
	
	public static MyUser getPrincipal() {
        return (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
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

	public static boolean isAuthenticated(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> authorities =(List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		return !(authorities.get(0).toString().equals("ROLE_ANONYMOUS")&&authentication.getPrincipal().equals("anonymousUser"));
    }

}
