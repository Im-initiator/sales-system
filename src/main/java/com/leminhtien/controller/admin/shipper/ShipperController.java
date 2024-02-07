package com.leminhtien.controller.admin.shipper;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IOrderService;
import com.leminhtien.service.IUserService;
import com.leminhtien.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "shipperController")
@PreAuthorize("hasAnyRole('SHIPPER')")
public class ShipperController {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;


    @RequestMapping(value = "shipper/order",method = RequestMethod.GET)
    public ModelAndView getOrder(
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "limit",defaultValue = "10")int limit,
            @RequestParam(value = "c",required = false) String code
    ){
        ModelAndView modelAndView = new ModelAndView("/admin/order/orderList");
        UserDTO user;
        try {
            String userName = SecurityUtils.getPrincipal().getUsername();
            user = userService.findOneByNameAndRolesCodeAndStatus(userName,"SHIPPER",1);
        }catch (Exception e){
            return new ModelAndView("/ErrorPage");
        }
        Pageable pageable = new PageRequest(page-1,limit);
        Page<OrderDTO> pages;
        if (code!=null){
            pages = orderService.findAllByShipperIdAndStatusAndCodeContaining(user.getId(),(byte)4,code,pageable);
            modelAndView.addObject("c",code);
        }else {
            pages =  orderService.findAllByShipperIdAndStatus(user.getId(),(byte)4,pageable);
        }
        modelAndView.addObject("user",user);
        modelAndView.addObject("model",pages.getContent());
        modelAndView.addObject("limit",limit);
        modelAndView.addObject("page",page);
        modelAndView.addObject("totalPage",pages.getTotalPages());
        modelAndView.addObject("totalItem",pages.getTotalElements());
        return modelAndView;
    }

    @RequestMapping(value = "shipper/order/delivery",method = RequestMethod.GET)
    public ModelAndView delivery(
            @RequestParam(value = "id") long id,
            @RequestParam(value = "page",defaultValue = "1") int page,
            @RequestParam(value = "limit",defaultValue = "10")int limit
    ){
        ModelAndView modelAndView = new ModelAndView("/admin/order/edit");
        UserDTO user;
        try {
            String userName = SecurityUtils.getPrincipal().getUsername();
            user = userService.findOneByNameAndRolesCodeAndStatus(userName,"SHIPPER",4);
        }catch (Exception e){
            return new ModelAndView("/ErrorPage");
        }
        modelAndView.addObject("order",orderService.findOne(id,user.getId()));
        modelAndView.addObject("api","/api/shipper/order");
        modelAndView.addObject("link","/shipper/order");
        return modelAndView;
    }

}
