package com.leminhtien.controller.web;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.*;
import com.leminhtien.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller(value="web")
public class HomeController {
	@Autowired
	private IShopService shopService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IProductService productService;

	@Autowired
	private ICartService cartService;

	@Autowired
	private IOrderService orderService;

	@RequestMapping("/web")
	@PreAuthorize("isAuthenticated()")
	@ModelAttribute("yourUser")
	public UserDTO sendUser(){
		UserDTO user = userService.findCurrentUser();
		user.setPassword("");
		return user;
	}




	@RequestMapping(value="/home",method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "limit",defaultValue = "12") int limit) {
		ModelAndView mav = new ModelAndView("web/home");
		Pageable pageable = new PageRequest(page-1,limit);
		Pageable p = new PageRequest(0,10);
		List<ShopDTO> list = shopService.findAllForList(p);

		mav.addObject("model",list);
		if (SecurityUtils.isAuthenticated()){
			UserDTO user = userService.findCurrentUser();
			user.setPassword("");
			mav.addObject("user",user);
			Long userId = SecurityUtils.getPrincipal().getId();
			int ca = cartService.countByUser();
			mav.addObject("count",ca);
			mav.addObject("countOrder",orderService.countAllByUserIdAndStatusBetween(userId,(byte) 1,(byte) 4));
		}
		Page<ProductDTO> pageProduct = productService.selectAll(pageable);
		mav.addObject("totalItem",pageProduct.getTotalElements());
		mav.addObject("totalPage",pageProduct.getTotalPages());
		mav.addObject("page",page);
		mav.addObject("limit",limit);
		mav.addObject("products",pageProduct.getContent());
		return mav;
	}
	
	
	
}
