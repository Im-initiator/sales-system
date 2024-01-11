package com.leminhtien.api.admin;

import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
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
}
