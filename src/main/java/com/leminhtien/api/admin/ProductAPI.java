package com.leminhtien.api.admin;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.service.IProductService;

@RestController
public class ProductAPI {

	@Autowired
	private IProductService productService;

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
