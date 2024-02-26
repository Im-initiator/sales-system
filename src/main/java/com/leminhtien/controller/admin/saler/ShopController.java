package com.leminhtien.controller.admin.saler;

import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "adminShop")
@PreAuthorize("hasAnyRole('SALER')")
public class ShopController {

    @Autowired
    private IShopService shopService;


    @RequestMapping(value = "/saler/shop",method = RequestMethod.GET)
    public ModelAndView EditShop(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/updateInformation");
        modelAndView.addObject("model",shopService.getShop());
        return modelAndView;
    }
}
