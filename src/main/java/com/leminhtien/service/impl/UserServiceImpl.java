package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserDTO> findAll() {
		List<UserEntity> listUser = userRepository.findAll();
		List<UserDTO> result = new ArrayList<UserDTO>();
		for(UserEntity user : listUser) {
			result.add(mapper.map(user,UserDTO.class));
		}
		return result;
	}

	@Override
	public UserDTO findByName() {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus("admin", 1);
		return mapper.map(userEntity,UserDTO.class);
	}

}
