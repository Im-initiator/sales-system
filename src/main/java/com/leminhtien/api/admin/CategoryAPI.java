package com.leminhtien.api.admin;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.service.ICategoryService;

import java.util.List;
//@PreAuthorize("hasAnyRole('SALE')")
@RestController
public class CategoryAPI {
	
	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private IShopService shopService;

	@GetMapping(value = "/api/manager/category")
	@ResponseBody
	public List<CategoryDTO> findByName(@RequestParam("name")String name){
		return categoryService.findByNameContaining(name);
	}

	@PostMapping(value = "/api/manager/category")
	public ResponseEntity<?> save(@RequestBody CategoryDTO category) {
		CategoryDTO categoryResponse = categoryService.save(category);
		if(categoryResponse != null) {
			return ResponseEntity.ok(categoryResponse);
		}else {
			return ResponseEntity.badRequest().body("Error: Lưu không thành công");
		}
	}


	@PutMapping(value = "/api/manager/category")
	public ResponseEntity<?> update(@RequestBody CategoryDTO category) {
		CategoryDTO categoryResponse = categoryService.save(category);
		if(categoryResponse != null) {
			return ResponseEntity.ok(categoryResponse);
		}else {
			return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
		}
	}

	@DeleteMapping(value = "/api/manager/category")
	public ResponseEntity<?> delete(@RequestBody Long[] ids){
		if(categoryService.delete(ids)) {
			return ResponseEntity.ok().body("Xóa thành công");
		}else {
			return ResponseEntity.badRequest().body("Error: Xóa không thành công");
		}
	}

/*-----------------------------------------------------------------------------------------*/
	/*SALER*/
	@PostMapping(value = "/api/saler/category")
	public ResponseEntity<?> saveForShop(@RequestBody Long[] ids){

			ShopDTO shop = shopService.saveCategory(ids);
			if (shop!=null){
				return ResponseEntity.ok("Thêm thành công");
			}else {
				return ResponseEntity.badRequest().body("Error: Thêm không thành công");
			}
	}

	@DeleteMapping(value = "/api/saler/category")
	public ResponseEntity<?> removeForShop(@RequestBody Long[] ids){
			int count = shopService.removeCategory(ids);
			if (count!=0){
				return ResponseEntity.ok("Xóa thành công"+count+" phần tử");
			}else {
				return ResponseEntity.badRequest().body("Error: Xóa không thành công");
			}
	}



}
