package com.leminhtien.controller.admin.manager;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.service.IOrderService;
import org.dom4j.rule.Mode;
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

import java.util.List;

@Controller(value = "managerOrder")
@PreAuthorize("hasAnyRole('MANAGER')")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping(value = "/manager/order",method = RequestMethod.GET)
    public ModelAndView view(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "10")int limit){
        ModelAndView modelAndView = new ModelAndView("admin/order/orderList");
        Pageable pageable = new PageRequest(page-1,limit);
        Page<OrderDTO> pages = orderService.findAll(pageable);
        modelAndView.addObject("model",pages.getContent());
        modelAndView.addObject("limit",limit);
        modelAndView.addObject("page",page);
        modelAndView.addObject("totalPage",pages.getTotalPages());
        modelAndView.addObject("totalItem",pages.getTotalElements());
        modelAndView.addObject("urlEdit","/manager/order/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/manager/order/edit",method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam(value = "id") long id){
        ModelAndView modelAndView = new ModelAndView("/admin/order/edit");
        modelAndView.addObject("order",orderService.findOne(id));
        modelAndView.addObject("api","/api/manager/order");
        modelAndView.addObject("link","/manager/order");
        return modelAndView;
    }
}
