package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.MyUser;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.UserRepository;

@Service
public class CustomUserdetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepository.findOneByUserNameAndStatus(username,1);
		if(user == null) {
			throw new UsernameNotFoundException("User name not found");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(RoleEntity role : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		MyUser myUser = new MyUser(username, user.getPassword(), true, true, true, true, authorities);
		myUser.setFullName(user.getFullName());
		myUser.setId(user.getId());
		return myUser;
	}
	
	

}
