package com.leminhtien.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.dto.UserDTO;
import com.leminhtien.service.IRoleService;
import com.leminhtien.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping(value="/admin/user", method = RequestMethod.GET)
	public ModelAndView getUser(@RequestParam("page") int page, @RequestParam(value = "name",required = false)String name) {
		ModelAndView mav = new ModelAndView("/admin/user/userList");
		int limit = 10;//limit
		long count = 0;
		Pageable pageable = new PageRequest(page-1, limit);
		UserDTO userDTO = new UserDTO();
		List<RoleDTO> role = roleService.findAll();
		List<UserDTO> users = new ArrayList<>();
		if(name!= null){
			users = userService.findAllByUserNameOrFullNameContaining(name,pageable);
			count = userService.countByUserNameORFullNameContaining(name);
		}else {
			users = userService.findAllByOrderByFullName(pageable);
			count = userService.count();
		}
		userDTO.setList(users);
		mav.addObject("page", page);
		mav.addObject("totalItem",count);
		mav.addObject("model",userDTO);
		mav.addObject("limit",limit);
		mav.addObject("Role",role);
		return mav;
		
	}
	
	@RequestMapping(value="/admin/edit/user", method = RequestMethod.GET)
	public ModelAndView editUserPage(@RequestParam("userName")String userName) {		
		ModelAndView mav = new ModelAndView("/admin/user/updateUser");
		UserDTO user = userService.findOneByUserName(userName);
		user.setPassword("");
		Map<String, String> mapRoles = roleService.selectAll();
		mav.addObject("mapRoles",mapRoles);
		mav.addObject("model", user);
		return mav;
	}

}
