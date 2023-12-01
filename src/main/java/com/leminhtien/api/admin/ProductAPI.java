package com.leminhtien.api.admin;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.service.IProductService;

@RestController
public class ProductAPI {

	@Autowired
	private IProductService productService;

	@GetMapping(value = "/api/admin/product")
	@ResponseBody
	public List<ProductDTO> find(@RequestParam("name") String name, @RequestParam(value = "page",required = false) Integer page){
		Pageable pageable = null;
		if(page == null){
			 pageable = new PageRequest(0,3);
		}else{
			 pageable = new PageRequest(page-1,3);
		}
        return productService.searchByName(name,pageable);
	}

	@PostMapping(value = "/api/admin/product")
	public ResponseEntity<?> saveProduct(@RequestBody ProductDTO product) {
		product.setSellNumber(0);
		product.setPrize(0f);
		ProductDTO productDTO = productService.save(product);
		if (productDTO != null) {
			return ResponseEntity.ok(productDTO);
		} else {
			return ResponseEntity.badRequest().body("Error: Lưu không thành công".getBytes(StandardCharsets.UTF_8));
		}
	}

	@PutMapping(value = "/api/admin/product")
	public ResponseEntity<?> update(@RequestBody ProductDTO product) {
		try {
			ProductDTO productDTO = productService.save(product);
			if (productDTO != null) {
				return ResponseEntity.ok(productDTO);
			} else {
				return ResponseEntity.badRequest()
						.body("Error: Cập nhật không thành công".getBytes(StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@DeleteMapping(value = "/api/admin/product")
	public ResponseEntity<?> delete(@RequestBody Long[] ids) {
		try {
			boolean flag = productService.delete(ids);
			if (flag) {
				return ResponseEntity.ok("Xóa thành công".getBytes(StandardCharsets.UTF_8));
			} else {
				return ResponseEntity.badRequest().body("Error: Xóa không thành công".getBytes(StandardCharsets.UTF_8));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
