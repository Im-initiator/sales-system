package com.leminhtien.controller.admin.saler;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.service.IOrderService;
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

@Controller(value = "salerOrder")
@PreAuthorize("hasAnyRole('SALER')")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping(value = "/saler/order",method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10")int limit,
                             @RequestParam(value = "t")String type){
        ModelAndView modelAndView = new ModelAndView("admin/order/orderList");
        Pageable pageable = new PageRequest(page-1,limit);
        Long userId = SecurityUtils.getPrincipal().getId();
        Page<OrderDTO> pages;
        if(type.equals("get-all")){
            pages =orderService.getAllForShop(pageable);
            modelAndView.addObject("urlUpdate","/saler/order/view");
        }else if (type.equals("wait-confirm")){

            pages =orderService.getAllForShop((byte)1,pageable);
            modelAndView.addObject("urlUpdate","/saler/order/confirm-order");
        }else if (type.equals("delivery-confirm")){
            pages = orderService.getAllForShop((byte) 2,pageable);
            modelAndView.addObject("urlUpdate","/saler/order/order-delivery");
        }else {
            return new ModelAndView("ErrorPage");
        }
        modelAndView.addObject("model",pages.getContent());
        modelAndView.addObject("limit",limit);
        modelAndView.addObject("page",page);
        modelAndView.addObject("totalPage",pages.getTotalPages());
        modelAndView.addObject("totalItem",pages.getTotalElements());
        modelAndView.addObject("urlEdit","/saler/order/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/saler/order/view",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(value = "id") long id){
        ModelAndView modelAndView = new ModelAndView("/admin/order/edit");
        Long userId = SecurityUtils.getPrincipal().getId();
        modelAndView.addObject("order",orderService.findOne(id,userId));
        modelAndView.addObject("api","/api/saler/order");
        modelAndView.addObject("link","/saler/order?t=get-all");
        return modelAndView;
    }

    @RequestMapping(value = "/saler/order/confirm-order",method = RequestMethod.GET)
    public ModelAndView confirmOrders(@RequestParam(value = "id") long id){
        ModelAndView modelAndView = new ModelAndView("/admin/order/edit");
        modelAndView.addObject("order",orderService.findOneByShop(id,(byte) 1));
        modelAndView.addObject("api","/api/saler/order");
        modelAndView.addObject("link","/saler/order?t=wait-confirm");
        return modelAndView;
    }

    @RequestMapping(value = "/saler/order/order-delivery",method = RequestMethod.GET)
    public ModelAndView confirmDelivery(@RequestParam(value = "id") long id){
        ModelAndView modelAndView = new ModelAndView("/admin/order/edit");
        modelAndView.addObject("order",orderService.findOneByShop(id,(byte) 2));
        modelAndView.addObject("api","/api/saler/order");
        modelAndView.addObject("link","/saler/order?t=delivery-confirm");
        return modelAndView;
    }
}
