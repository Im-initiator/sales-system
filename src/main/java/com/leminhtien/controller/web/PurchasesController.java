package com.leminhtien.controller.web;

import com.leminhtien.dto.CartDTO;
import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.TransportDTO;
import com.leminhtien.service.ICartService;
import com.leminhtien.service.IOrderService;
import com.leminhtien.service.ITransportService;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "webPurchase")
public class PurchasesController {

    @Autowired
    private ICartService cartService;

    @Autowired
    private ITransportService transportService;

    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/web/redirect/purchase",method = RequestMethod.GET)
    public ModelAndView redirectPurchases(@RequestParam(value = "id") Long[] ids){
        ModelAndView modelAndView = new ModelAndView("/web/product/purchases");
        List<CartDTO> carts = cartService.findByIdAndUserId(ids);
        List<TransportDTO> transport = transportService.findAll();
        modelAndView.addObject("carts",carts);
        modelAndView.addObject("transports",transport);
        modelAndView.addObject("count",cartService.countByUser());
        return modelAndView;
    }

    @RequestMapping(value = "/web/detailOrder",method = RequestMethod.GET)
    public ModelAndView detailPurchases(){
        ModelAndView modelAndView = new ModelAndView("/web/product/detailOrder");
        Long userId = SecurityUtils.getPrincipal().getId();
        List<OrderDTO> orders = orderService.findAllByUserIdAndStatusBetween(userId,(byte) 1,(byte) 5);
        modelAndView.addObject("orders",orders);
        return modelAndView;
    }


}
