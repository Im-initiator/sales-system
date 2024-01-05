package com.leminhtien.controller.web;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;

@Controller
@PreAuthorize("isAuthenticated()")
public class ShopController {
    @Autowired
    private IShopService iShopService;
    @RequestMapping(value = "/web/registershop",method = RequestMethod.GET)
    public ModelAndView HomeRegister(){
       return  new ModelAndView("/web/shop/registerShop");
    }

    @RequestMapping(value = "/web/shop",method = RequestMethod.GET)
    public ModelAndView viewShop(){
        ModelAndView modelAndView;
        ShopDTO shopDTO = iShopService.getShop();
        if (shopDTO!= null){
            modelAndView = new  ModelAndView("/web/shop/shopHome");
            modelAndView.addObject("model",shopDTO);
        }else {
            return new ModelAndView("redirect:/web/shop/register");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/web/shop/register",method = RequestMethod.GET)
    public ModelAndView viewRegister(){
        ModelAndView modelAndView;
        ShopDTO shopDTO = iShopService.getShop();
            modelAndView = new ModelAndView("/web/shop/registerShop");
        return modelAndView;
    }

    @RequestMapping(value = "/web/registershop",method = RequestMethod.POST)
    public ResponseEntity<?> registerShop(@ModelAttribute("model") ShopDTO shopDTO){
        ShopDTO shopSave = iShopService.save(shopDTO);
        if (shopSave!=null){
            return ResponseEntity.ok("Lưu thành công");
        }else {
            return ResponseEntity.badRequest().body("Error: Lưu không thành công".getBytes(StandardCharsets.UTF_8));
        }
    }



}
