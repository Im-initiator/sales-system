package com.leminhtien.mapper;

import org.springframework.stereotype.Component;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.entity.UserEntity;

@Component
public class UserMapper implements ObjectMapper<UserDTO,UserEntity>{

	@Override
	public UserDTO toDTO(UserEntity entity) {
		UserDTO user = new UserDTO();
		user.setFullName(entity.getFullName());
		user.setUserName(entity.getUserName());
		user.setStatus(entity.getStatus());
		return user;
	}

	@Override
	public UserEntity toEntity(UserDTO modelDTO) {
		UserEntity user = new UserEntity();
		user.setFullName(modelDTO.getFullName());
		user.setUserName(modelDTO.getUserName());
		user.setStatus(modelDTO.getStatus());
		return user;
	}

	
	
}
