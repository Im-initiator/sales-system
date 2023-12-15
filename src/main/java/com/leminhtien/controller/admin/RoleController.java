package com.leminhtien.controller.admin;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@PreAuthorize("hasAnyRole('ADMIN')")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;


    @RequestMapping(value = "/admin/role",method = RequestMethod.GET)
    public ModelAndView view(@RequestParam int page){
        ModelAndView mav = new ModelAndView("/admin/roles/listrole");
        int limit = 10;
        long totalItem = iRoleService.count();
        Pageable pageable = new PageRequest(page-1,limit);
        List<RoleDTO> roles = iRoleService.findAll(pageable);
        mav.addObject("model",roles);
        mav.addObject("limit",limit);
        mav.addObject("totalItem",totalItem);
        mav.addObject("page",page);
        return mav;
    }



}
