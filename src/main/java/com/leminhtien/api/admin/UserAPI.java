package com.leminhtien.api.admin;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		UserDTO userResponse =  userService.save(userDTO);
		if(userResponse== null) {
			return ResponseEntity.badRequest().body("Error: Tên tài khoản đã tồn tại".getBytes(StandardCharsets.UTF_8));//trả về mã trạng thái 400 đi kèm với nội dung
		}else {
			return ResponseEntity.ok(userResponse);	 
		}
		
	}
	
	@DeleteMapping("/api/admin/user")
	public ResponseEntity<?> deleteUser(@RequestBody String[]names){
		boolean flag = userService.deleteUser(names);
		if(flag) {
			return ResponseEntity.ok("User deleted successfully");	 
		}else {
			return ResponseEntity.badRequest().body("Error: Xóa không thành công".getBytes(StandardCharsets.UTF_8));
		}
	}
	
	@PutMapping("/api/admin/user")
	public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO){
		UserDTO userResponse =  userService.update(userDTO);
		if(userResponse== null) {
			return ResponseEntity.badRequest().body("Error: Cập nhật không thành công".getBytes(StandardCharsets.UTF_8));//trả về mã trạng thái 400 đi kèm với nội dung
		}else {
			return ResponseEntity.ok(userResponse);	 
		}
		
	}
	
	

}
