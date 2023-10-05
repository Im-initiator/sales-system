package com.leminhtien.service;

import java.util.List;

import com.leminhtien.dto.UserDTO;

public interface IUserService {
	
	List<UserDTO> findAll();
	UserDTO findByName();

}
