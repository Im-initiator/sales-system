package com.leminhtien.controller.web;

import com.leminhtien.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@PreAuthorize("isAuthenticated()")
@Controller
public class CardController {

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/web/cart",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView mav = new ModelAndView("/web/carts/cart");
        mav.addObject("cart",cartService.findAll());
        mav.addObject("count",cartService.countByUser());
        double d = cartService.getTotalPrice();
        mav.addObject("total",d);
        return  mav;
    }
}
