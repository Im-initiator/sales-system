package com.leminhtien.controller.web;

import com.leminhtien.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "webUserController")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/web/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(){
        ModelAndView modelAndView = new ModelAndView("/web/user/profile");
        modelAndView.addObject("model",userService.findCurrentUser());
        return modelAndView;
    }
}
