package com.leminhtien.api.admin;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IUserService;
@PreAuthorize("hasAnyRole('ADMIN')")
@RestController
public class UserAPI {
	
	@Autowired
	IUserService userService;
	
	@GetMapping(value ="/api/admin/user")
	@ResponseBody
	public List<UserDTO> find(@RequestParam("name") String name){
		return userService.findAllByUserNameOrFullNameContaining(name);
	}

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
