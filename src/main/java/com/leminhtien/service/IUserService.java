package com.leminhtien.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leminhtien.dto.UserDTO;
import org.springframework.security.core.userdetails.User;

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
	Page<UserDTO> findAllByRole(Pageable pageable,String role);
	Page<UserDTO> findAllByUserNameContainingAndRole(String name,String role,Pageable pageable);
	UserDTO findCurrentUser();
	UserDTO findOneByNameAndRolesCodeAndStatus(String name,String role,int status);

	UserDTO updateInformation(UserDTO userDTO) throws IOException;


}
