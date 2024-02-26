package com.leminhtien.api.admin;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

@RestController(value = "apiAdminShop")
public class ShopAPI {

    @Autowired
    private IShopService shopService;

    @DeleteMapping("/api/manager/shop")
    @ResponseBody
    public ResponseEntity<?> remove(@RequestBody Long[] ids){
        int count = shopService.remove(ids);
        if(count==-1||count==0){
            return ResponseEntity.badRequest().body("Xóa không thành côngn");
        }else {
            return ResponseEntity.ok(("Xóa thành công "+count+" phần tử").getBytes(StandardCharsets.UTF_8));
        }
    }

    @PreAuthorize("hasAnyRole('SALER')")
    @PostMapping("/api/saler/shop")
    public ResponseEntity<?> updateShop(@ModelAttribute ShopDTO shopDTO){
        try {
            ShopDTO shop = shopService.update(shopDTO);
            shop.setProducts(null);
            shop.setCategories(null);
            shop.setCategories(null);
            return ResponseEntity.ok(shop);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Update failed");
        }
    }
}
