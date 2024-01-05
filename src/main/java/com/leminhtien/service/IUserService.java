package com.leminhtien.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.leminhtien.dto.UserDTO;

public interface IUserService {
	
	List<UserDTO> findAllByOrderByFullName(Pageable page);
	UserDTO findByUserNameAndStatus(String userName);
	UserDTO save(UserDTO userDTO);
	UserDTO update(UserDTO userDTO);
	UserDTO findById(Long id);
	Long count();
	UserDTO findOneByUserName(String userName);
	boolean deleteUser(Long[] ids);
	boolean deleteUser(String[] names);

	long countByUserNameORFullNameContaining(String name);
	List<UserDTO> findAllByUserNameOrFullNameContaining(String name);
	List<UserDTO> findAllByUserNameOrFullNameContaining(String name,Pageable pageable);

	UserDTO findCurrentUser();


}
