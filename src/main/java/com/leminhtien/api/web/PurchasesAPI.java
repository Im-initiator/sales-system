package com.leminhtien.api.web;

import com.leminhtien.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@PreAuthorize("isAuthenticated()")
public class PurchasesAPI {

    @Autowired
    private IOrderService orderService;




    @PostMapping(value = "/api/web/purchase")
    public ResponseEntity<?> save(@RequestBody List<Objects> objects){
        return null;
    }


}
