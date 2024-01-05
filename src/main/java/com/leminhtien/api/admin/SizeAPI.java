package com.leminhtien.api.admin;

import com.leminhtien.dto.SizeDTO;
import com.leminhtien.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SizeAPI {

    @Autowired
    private ISizeService sizeService;

    @GetMapping(value = "/api/manager/size")
    @ResponseBody
    public List<SizeDTO> find(@RequestParam(value = "name")String name){
        List<SizeDTO> sizes = sizeService.findAllByNameOrderByName(name);
        return sizes;
    }

    @PostMapping(value = "/api/manager/size")
    public ResponseEntity<?> save(@RequestBody SizeDTO sizeDTO){
        sizeDTO = sizeService.save(sizeDTO);
        if (sizeDTO!=null){
            return ResponseEntity.ok(sizeDTO);
        }else {
            return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
        }
    }


    @PutMapping(value = "/api/manager/size")
    public ResponseEntity<?> update(@RequestBody SizeDTO sizeDTO){
        sizeDTO = sizeService.save(sizeDTO);
        if (sizeDTO!=null){
            return ResponseEntity.ok(sizeDTO);
        }else {
            return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
        }
    }

    @DeleteMapping(value = "/api/manager/size")
    public ResponseEntity<?> remove(@RequestBody long[] ids){
        long count = sizeService.remove(ids);
        if (count!=0){
            return ResponseEntity.ok("Xóa thành công"+count+" phần tử");
        }else {
            return ResponseEntity.badRequest().body("Error: Xóa không thành công");
        }
    }


}
