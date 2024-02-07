package com.leminhtien.controller.admin.censor;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "censorShipper")
public class ShipperController {

    @Autowired
    private IUserService userService;


    @RequestMapping(value="/censor/shipper", method = RequestMethod.GET)
    public ModelAndView getUser(@RequestParam(value = "page",defaultValue = "1") int page, @RequestParam(value = "name",required = false)String name) {
        ModelAndView mav = new ModelAndView("/admin/user/userList");
        int limit = 10;//limit
        Pageable pageable = new PageRequest(page-1, limit);
        UserDTO userDTO = new UserDTO();

        Page<UserDTO> pages;
        if(name!= null){
            pages = userService.findAllByUserNameContainingAndRole(name,"SHIPPER",pageable);
        }else {
            pages = userService.findAllByRole(pageable,"SHIPPER");

        }
        userDTO.setList(pages.getContent());
        mav.addObject("page", page);
        mav.addObject("totalItem",pages.getTotalElements());
        mav.addObject("model",userDTO);
        mav.addObject("limit",limit);

        return mav;

    }




}
