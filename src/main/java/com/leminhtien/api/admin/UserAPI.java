package com.leminhtien.api.admin;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IUserService;

@RestController
public class UserAPI {
	
	@Autowired
	IUserService userService;
	
	
	@PostMapping("/api/user")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {	
		//ResponseEntity<?> đại diện cho phải hồi HTTP
		try {
			if(userService.findByName(userDTO.getUserName())!= null) {
				return ResponseEntity.badRequest().body("Tên tài khoản đã tồn tại".getBytes(StandardCharsets.UTF_8));//trả về mã trạng thái 400 đi kèm với nội dung
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		UserDTO userResponse =  userService.save(userDTO);
		return ResponseEntity.ok(userResponse);
		 
	}
	

}
