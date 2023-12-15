package com.leminhtien.api.admin;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.dto.RoleDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.service.IRoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleAPI {

    @Autowired
    IRoleService iRoleService;

    @PostMapping(value = "api/admin/role")
    public ResponseEntity<?> save(@RequestBody RoleDTO roleDTO){
        RoleDTO role =iRoleService.save(roleDTO);
        if(role != null) {
            return ResponseEntity.ok(role);
        }else {
            return ResponseEntity.badRequest().body("Error: Lưu không thành công");
        }
    }

    @PutMapping(value = "api/admin/role")
    public ResponseEntity<?> update(@RequestBody RoleDTO roleDTO){
        RoleDTO role =iRoleService.save(roleDTO);
        if(role != null) {
            return ResponseEntity.ok(role);
        }else {
            return ResponseEntity.badRequest().body("Error: Cập nhật không thành công");
        }
    }

    @DeleteMapping(value = "api/admin/role")
    public ResponseEntity<?> delete(@RequestBody Long[] ids){
        int numberdelete = iRoleService.delete(ids);
        if(numberdelete != 0) {
            return ResponseEntity.ok("Xóa thành công "+ numberdelete+" Phần tử");
        }else {
            return ResponseEntity.badRequest().body("Error: Xóa không thành công");
        }
    }
}
