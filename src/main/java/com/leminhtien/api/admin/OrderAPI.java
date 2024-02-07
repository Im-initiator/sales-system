package com.leminhtien.api.admin;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IOrderService;
import com.leminhtien.service.IUserService;
import com.leminhtien.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController(value = "orderApi")
public class OrderAPI {

    private final String MANAGER_API = "/api/manager/order";
    private final String SALER_API = "/api/saler/order";
    private final String CENSOR_API="/api/censor/order";
    private final String SHIPPER_API = "/api/shipper/order";

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    /*MANAGER*/

    @PutMapping(value = MANAGER_API)
    @PreAuthorize("hasAnyRole('MANAGER','SALER')")
    public ResponseEntity<?> managerUpdate(@RequestBody Map<String, Object> json){
        try {
            long id = Long.parseLong((String)json.get("id"));
            byte status = Byte.parseByte((String) json.get("status"));
            if(SecurityUtils.getAuthorities().contains("MANAGER")){
                if (status<-1||status>6){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
                }
            }
            if(SecurityUtils.getAuthorities().contains("SALER")){
                if ((status != -1) && (status != 2) && (status != 3)){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
                }
            }

            OrderDTO order = orderService.update(id,status);
            if (order!=null){
                return ResponseEntity.ok(order);
            }
            return ResponseEntity.badRequest().body("Error");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error");
        }
    }

    @GetMapping(value = "/order/search")
    @ResponseBody
    public OrderDTO find(@RequestParam(value = "c")String code, HttpServletResponse response){
        try {
            Pageable pageable = new PageRequest(0,10);
            return orderService.findAllByShipperIdAndStatusAndCodeContaining(-1L,(byte)1,code,pageable).getContent().get(0);
        }catch (Exception e){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
    }


    /*SHIPPER*/
    @PutMapping(CENSOR_API)
    public ResponseEntity<?> addOrderForShipper(@RequestBody Map<String,Object> data){
        try {
             Object ids = data.get("orderId");
            boolean flag = (boolean)data.get("flag");
            if (ids instanceof ArrayList<?>){
                ArrayList<String>list = (ArrayList<String>) ids;
                List<Long> orderIds = list.stream().map(Long::parseLong).toList();

                String userName = (String) data.get("userName");
                UserDTO userDTO = userService.findOneByUserName(userName);
                orderService.setOrRemoveShipperId(userDTO.getId(),orderIds,flag);
                return ResponseEntity.ok("success");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not success");

    }

    @PutMapping(value = "/api/shipper/{id}/orders")
    public ResponseEntity<?> updateStatusByShipper(@RequestBody Map<String,Object> json,@PathVariable("id")long orderId){
        byte status = Byte.parseByte(json.get("status").toString());
        if (status != 5 && status != 0){
            return ResponseEntity.badRequest().body("Status not valid");
        }
        Long userId = SecurityUtils.getPrincipal().getId();
        OrderDTO orderDTO = orderService.findOne(orderId,userId,(byte) 4);
        if (orderDTO!=null){
            orderDTO = orderService.update(orderId,status);
            return ResponseEntity.ok(orderDTO);
        }else {
            throw new NullPointerException();
        }
    }

    /*SALER*/
    @PutMapping(value = "/api/saler/{id}/orders")
    public ResponseEntity<?> updateStatusBySaler(@RequestBody Map<String,Object> json,@PathVariable("id")long orderId){
        byte status = Byte.parseByte(json.get("status").toString());
        if (status != -1 && status != 2 && status != 3){
            return ResponseEntity.badRequest().body("Status not valid");
        }
        OrderDTO orderDTO = orderService.findOneByIdAndProductShopId(orderId);
        if (orderDTO!=null){
            orderDTO = orderService.update(orderId,status);
            return ResponseEntity.ok(orderDTO);
        }else {
            throw new NullPointerException();
        }
    }

}
