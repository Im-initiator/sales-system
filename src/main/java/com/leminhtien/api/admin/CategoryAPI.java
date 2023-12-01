package com.leminhtien.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.service.ICategoryService;

import java.util.List;

@RestController
public class CategoryAPI {
	
	@Autowired
	private ICategoryService categoryService;


	@GetMapping(value = "/api/admin/category")
	@ResponseBody
	public List<CategoryDTO> findByName(@RequestParam("name")String name){
		return categoryService.findByNameContaining(name);
	}

	@PostMapping(value = "/api/admin/category")
	public ResponseEntity<?> save(@RequestBody CategoryDTO category) {
		CategoryDTO categoryResponse = categoryService.save(category);
		if(categoryResponse != null) {
			return ResponseEntity.ok(categoryResponse);
		}else {
			return ResponseEntity.badRequest().body("Error: Lưu không thành công");
		}
	}
	

	@PutMapping(value = "/api/admin/category")
	public ResponseEntity<?> update(@RequestBody CategoryDTO category) {
		CategoryDTO categoryResponse = categoryService.save(category);
		if(categoryResponse != null) {
			return ResponseEntity.ok(categoryResponse);
		}else {
			return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
		}
	}
	
	@DeleteMapping(value = "/api/admin/category")
	public ResponseEntity<?> delete(@RequestBody Long[] ids){
		if(categoryService.delete(ids)) {
			return ResponseEntity.ok().body("Xóa thành công");
		}else {
			return ResponseEntity.badRequest().body("Error: Xóa không thành công");
		}
	}



}
