package com.leminhtien.controller.admin.manager;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "shopManager")
public class ShopController {

    @Autowired
    private IShopService shopService;
    @RequestMapping(value = "/manager/shop",method = RequestMethod.GET)
    public ModelAndView view(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "page",defaultValue = "0")int page,
            @RequestParam(value = "limit",defaultValue = "10") int limit){
        ModelAndView mav = new ModelAndView("/admin/shop/shopList");
        Pageable pageable = new PageRequest(page,limit);
        Page<ShopDTO> list = shopService.findAll(pageable);
        List<ShopDTO> model = list.getContent();
        mav.addObject("model",model);
        mav.addObject("totalItem",list.getTotalElements());
        mav.addObject("totalPage",list.getTotalPages());
        mav.addObject("page",page);
        mav.addObject("limit",limit);
        return mav;
    }
}
