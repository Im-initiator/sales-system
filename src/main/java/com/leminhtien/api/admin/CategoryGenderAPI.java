package com.leminhtien.api.admin;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.dto.CategoryGenderDTO;
import com.leminhtien.service.ICategoryGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("hasAnyRole('ADMIN')")
@RestController
public class CategoryGenderAPI {
    @Autowired
    private ICategoryGenderService categoryGenderService;

    @GetMapping(value = "/api/manager/gender")
    @ResponseBody
    public List<CategoryGenderDTO> findByName(@RequestParam("name")String name){
        return categoryGenderService.findAllByNameContaining(name);
    }

    @PostMapping(value = "/api/manager/gender")
    public ResponseEntity<?> save(@RequestBody CategoryGenderDTO categoryGenderDTO){
        categoryGenderDTO = categoryGenderService.save(categoryGenderDTO);
        if(categoryGenderDTO != null) {
            return ResponseEntity.ok(categoryGenderDTO);
        }else {
            return ResponseEntity.badRequest().body("Error: Lưu không thành công");
        }
    }

    @PutMapping(value = "/api/manager/gender")
    public ResponseEntity<?> update(@RequestBody CategoryGenderDTO categoryGenderDTO){
        categoryGenderDTO = categoryGenderService.update(categoryGenderDTO);
        if(categoryGenderDTO != null) {
            return ResponseEntity.ok(categoryGenderDTO);
        }else {
            return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
        }
    }

    @DeleteMapping(value = "/api/manager/gender")
    public ResponseEntity<?> delete(@RequestBody Long[]ids){
        long result = categoryGenderService.delete(ids);
        if(result > 0) {
            return ResponseEntity.ok(result);
        }else {
            return ResponseEntity.badRequest().body("Error: Xóa không thành công");
        }
    }

}
