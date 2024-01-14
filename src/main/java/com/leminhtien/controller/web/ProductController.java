package com.leminhtien.controller.web;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.dto.SizeDTO;
import com.leminhtien.service.*;
import com.leminhtien.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value = "webProduct")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISizeService sizeService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired IShopService shopService;

    @Autowired
    private ICartService cartService;

    @RequestMapping(value = "/web/product")
    public ModelAndView detail(@RequestParam(value = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("/web/product/detail");
        ProductDTO productDTO = productService.findById(id);
        modelAndView.addObject("product",productDTO);
        List<SizeDTO> sizes = sizeService.findAllByProductId(id);
        if (SecurityUtils.isAuthenticated()){
            modelAndView.addObject("count",cartService.countByUser());
        }
        modelAndView.addObject("sizes",sizes);
        modelAndView.addObject("category",categoryService.getCategoryByProductId(id));
        modelAndView.addObject("shop",shopService.getShopByProductId(id));
        return modelAndView;
    }
}
