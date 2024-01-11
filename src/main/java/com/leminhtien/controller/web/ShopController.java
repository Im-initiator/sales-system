package com.leminhtien.controller.web;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.service.IProductService;
import com.leminhtien.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller

public class ShopController {
    @Autowired
    private IShopService iShopService;

    @Autowired
    private IProductService productService;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/web/shop",method = RequestMethod.GET)
    public ModelAndView viewShop(@RequestParam(value = "id", required = false) Integer id,
        @RequestParam(value = "page",defaultValue = "0") int page,
        @RequestParam(value = "page",defaultValue = "9") int limit
    ){
        ModelAndView modelAndView;
        if(id==null){
            ShopDTO shopDTO = iShopService.getShop();
            if (shopDTO!= null){
                return new ModelAndView("redirect:/web/shop?id="+shopDTO.getId());
            }else {
                return new ModelAndView("redirect:/web/shop/register");
            }
        }else {
            modelAndView = new ModelAndView("/web/shop/shopHome");
            ShopDTO shopDTO = iShopService.getShop(id);
            Pageable pageable = new PageRequest(page,limit);
            modelAndView.addObject("shop",shopDTO);
            List<ProductDTO> products = productService.findAllByShopId(shopDTO.getId(),pageable);
            modelAndView.addObject("shop",shopDTO);
            modelAndView.addObject("products",products);


        }
        return modelAndView;
    }

    @RequestMapping(value = "/web/shop/register",method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    public ModelAndView viewRegister(){
        ModelAndView modelAndView;
        ShopDTO shopDTO = iShopService.getShop();
            modelAndView = new ModelAndView("/web/shop/registerShop");
        return modelAndView;
    }

    @RequestMapping(value = "/web/registershop",method = RequestMethod.POST)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> registerShop(@ModelAttribute("model") ShopDTO shopDTO){
        ShopDTO shopSave = iShopService.save(shopDTO);
        if (shopSave!=null){
            return ResponseEntity.ok("Lưu thành công");
        }else {
            return ResponseEntity.badRequest().body("Error: Lưu không thành công".getBytes(StandardCharsets.UTF_8));
        }
    }





}
