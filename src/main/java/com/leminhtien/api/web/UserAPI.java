package com.leminhtien.api.web;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "webUser")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/api/web/user")
    public ResponseEntity<?> updateUser(@ModelAttribute UserDTO userDTO){
        try {
            userDTO = userService.updateInformation(userDTO);
            userDTO.setPassword("");
            userDTO.setRoles(null);
            userDTO.setUserName("");
            return ResponseEntity.ok(userDTO);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Update failed");
        }
    }

}
