package com.leminhtien.api.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.UserRepository;
@PreAuthorize("hasAnyRole('ADMIN')")
@RestController
public class TestAPI {
	
	private UserRepository userRepository;
	
	@PostMapping("/api/test/user")
	public UserEntity testUser(@RequestBody UserEntity user) {
		return userRepository.findOneByUserNameAndStatus(user.getUserName(), 1);
	}

}
