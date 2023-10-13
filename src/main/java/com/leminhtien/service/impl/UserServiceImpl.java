package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.RoleRespository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired 
	RoleRespository roleRespository;
	
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
	public UserDTO findByName(String userName) {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(userName, 1);
		if(userEntity != null ) {
			return mapper.map(userEntity,UserDTO.class);
		}else {
			return null;
		}
		
	}
	
	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		userDTO.setStatus(1);
		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		List<RoleEntity>roles = roleRespository.findByCode("USER");
		userEntity.setRoles(roles);
		userEntity = userRepository.save(userEntity);	
		return  mapper.map(userEntity,UserDTO.class);
	}

}
