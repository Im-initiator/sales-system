package com.leminhtien.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/admin/user", method = RequestMethod.GET)
	public ModelAndView getUser() {
		ModelAndView mav = new ModelAndView("/admin/list/user");
		List<UserDTO> users = userService.findAll();
		UserDTO userDTO = new UserDTO();
		userDTO.setList(users);
		mav.addObject("model",userDTO);
		return mav;
		
	}

}
