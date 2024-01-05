package com.leminhtien.controller.admin.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leminhtien.service.ICategoryService;

@PreAuthorize("hasAnyRole('MANAGER')")
@Controller(value = "managerCategory")
public class CategoryController {
	
	@Autowired
	private ICategoryService categoryService;
	

	@RequestMapping(value = "/manager/category",method = RequestMethod.GET)
	public ModelAndView findAll(@RequestParam("page") int page, @RequestParam(value = "name",required = false) String name) {
		ModelAndView mav = new ModelAndView("/admin/category/listCategory");
		int limit = 6;
		Pageable pageable = new PageRequest(page-1, limit);
		if(name!= null){
			mav.addObject("model",categoryService.findByNameContaining(name,pageable));
			mav.addObject("page",page);
			mav.addObject("limit", limit);
			mav.addObject("totalItem", categoryService.countByNameContaining(name));
		}else{
			mav.addObject("model",categoryService.findAll(pageable));
			mav.addObject("page",page);
			mav.addObject("limit", limit);
			mav.addObject("totalItem", categoryService.count());

		}
		mav.addObject("link","/manager/category");
		mav.addObject("api","/api/manager/category");
		return mav;
	}

}
