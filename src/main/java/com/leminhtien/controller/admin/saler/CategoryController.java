package com.leminhtien.controller.admin.saler;

import com.leminhtien.service.ICategoryGenderService;
import com.leminhtien.service.ICategoryService;
import com.leminhtien.service.IProductService;
import com.leminhtien.service.ISizeService;
import com.leminhtien.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "saleCategory")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ISizeService sizeService;
    @RequestMapping(value = "/saler/register/category", method = RequestMethod.GET)
    public ModelAndView viewRegister(){
        ModelAndView modelAndView = new ModelAndView("/admin/product/registerSize");
        modelAndView.addObject("categories",categoryService.findAllByRemoveByShop());
        modelAndView.addObject("sizes",sizeService.findAllByShopRemove());
        modelAndView.addObject("sizeShop",sizeService.findAllByShop());
        modelAndView.addObject("categoryShop",categoryService.findAllByShopId());
        return modelAndView;
    }
}
