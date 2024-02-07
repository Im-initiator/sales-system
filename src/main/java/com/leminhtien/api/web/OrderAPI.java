package com.leminhtien.api.web;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class OrderAPI {

    private final String ORDER_API = "/api/web/order";

    @Autowired
    private IOrderService orderService;

    @PostMapping(value = ORDER_API)
    @ResponseBody
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO){
        List<OrderDTO> list = orderService.save(orderDTO);
        if (list==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lưu không thành công");
        }
        return ResponseEntity.ok(list);
    }
}
