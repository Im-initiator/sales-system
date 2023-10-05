package com.leminhtien.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView loginPage() {
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("/register");
		return mav;
	}
	
	@RequestMapping(value="/access-denied", method = RequestMethod.GET)
	public ModelAndView aaccessDeniedPage() {
		ModelAndView mav = new ModelAndView("ErrorPage");
		return mav;
	}
	

}
