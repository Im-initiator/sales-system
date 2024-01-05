package com.leminhtien.controller.admin.manager;

import com.leminhtien.dto.CategoryGenderDTO;
import com.leminhtien.service.ICategoryGenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@PreAuthorize("hasAnyRole('MANAGER')")
@Controller(value = "managerGender")
public class CategoryGenderController {

    @Autowired
    ICategoryGenderService categoryGenderService;

    @RequestMapping(value = "/manager/gender",method = RequestMethod.GET)
    public ModelAndView findAll(@RequestParam("page") int page, @RequestParam(value = "name", required = false) String name){
            int limit = 6;
            Pageable pageable = new PageRequest(page-1, limit);
            ModelAndView mav = new ModelAndView("/admin/categoryGender/listCategoryGender");
            List<CategoryGenderDTO> model = new ArrayList<>();
            long totalItem = 0;
            if(name == null){
              model   = categoryGenderService.findAll(pageable);
              totalItem = categoryGenderService.count();
            }else{
              model = categoryGenderService.findAllByNameContaining(name,pageable);
              totalItem = categoryGenderService.count(name);
            }
            mav.addObject("model",model);
            mav.addObject("link","/manager/gender");
            mav.addObject("api","/api/manager/gender");
            mav.addObject("limit",limit);
            mav.addObject("page",page);
            mav.addObject("totalItem",totalItem);
            return mav;
    }




}
