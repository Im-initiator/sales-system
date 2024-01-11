package com.leminhtien.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CardController {

    @RequestMapping(value = "/web/cart",method = RequestMethod.GET)
    public ModelAndView view(){
        ModelAndView mav = new ModelAndView("/web/carts/cart");
        return  mav;
    }
}
