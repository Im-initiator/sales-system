package com.leminhtien.controller.admin.manager;

import com.leminhtien.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SizeController {

    @Autowired
    private ISizeService sizeService;

    @RequestMapping(value = "manager/size")
    public ModelAndView findAll(@RequestParam(name = "page",defaultValue = "1") int page, @RequestParam(name = "name", required = false) String name) {
        ModelAndView mav = new ModelAndView("/admin/size/size");
        int limit = 10;
        Pageable pageable = new PageRequest(page-1,limit);
        if (name != null) {
            mav.addObject("model",sizeService.findAllByNameOrderByName(name,pageable));
            mav.addObject("totalItem",sizeService.countByNam(name));
        } else {
            mav.addObject("model",sizeService.findAll(pageable));
            mav.addObject("totalItem",sizeService.count());
        }

        mav.addObject("page", page);
        mav.addObject("limit", limit);
        return mav;
    }
}
