package com.leminhtien.api.web;

import com.leminhtien.dto.CartDTO;
import com.leminhtien.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;

@RestController
@PreAuthorize("isAuthenticated()")
public class CartAPI {

    @Autowired
    private ICartService cartService;

    @PostMapping(value = "/api/web/cart")
    public ResponseEntity<?> saveCart(@RequestBody CartDTO cartDTO) {
        try {
            CartDTO cart = cartService.save(cartDTO);
            if (cart == null) {
                return ResponseEntity.badRequest().body("Error: Thêm sản phẩm không thành công".getBytes(StandardCharsets.UTF_8));
            } else {
                return ResponseEntity.ok("Thêm sản phẩm thành công".getBytes(StandardCharsets.UTF_8));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: Thêm sản phẩm không thành công".getBytes(StandardCharsets.UTF_8));
        }

    }

    @PutMapping(value = "/api/web/cart")
    @ResponseBody
    public ResponseEntity<?> updateCart(@RequestBody CartDTO cartDTO) {
        try {
            if (cartDTO.getQuantity()<=0){
                throw new DataFormatException();
            }
            CartDTO cart = cartService.update(cartDTO);
            if (cart == null) {
                return ResponseEntity.badRequest().body("Error: Cập nhật sản phẩm không thành công".getBytes(StandardCharsets.UTF_8));
            } else {
                return ResponseEntity.ok(cart);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: Cập nhật sản phẩm không thành công".getBytes(StandardCharsets.UTF_8));
        }

    }

    @DeleteMapping(value = "/api/web/cart")
    public ResponseEntity<?> delete(@RequestBody Long[] ids) {
        try {
            cartService.remove(ids);
            return ResponseEntity.ok("Cập nhật sản phẩm thành công".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: Cập nhật sản phẩm không thành công".getBytes(StandardCharsets.UTF_8));
        }

    }


}
