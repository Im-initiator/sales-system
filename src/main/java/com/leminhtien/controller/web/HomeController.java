package com.leminhtien.controller.web;

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

@Controller(value="web")
public class HomeController {
	@Autowired
	private IShopService shopService;
	@RequestMapping(value="/home",method = RequestMethod.GET)
	public ModelAndView homePage(@RequestParam(value = "page",defaultValue = "0") int page, @RequestParam(value = "limit",defaultValue = "10") int limit) {
		ModelAndView mav = new ModelAndView("web/home");
		Pageable pageable = new PageRequest(page,limit);
		Page<ShopDTO> p = shopService.findAll(pageable);
		List<ShopDTO> list = p.getContent();
		mav.addObject("model",list);
		return mav;
	}
	
	
	
}
