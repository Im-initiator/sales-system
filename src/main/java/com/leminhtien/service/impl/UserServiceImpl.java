package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;

import org.hibernate.QueryException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.UserDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.RoleRespository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.service.IUserService;
import com.leminhtien.utils.SecurityUtils;

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
	public List<UserDTO> findAllByOrderByFullName(Pageable page) {
		List<UserEntity> listUser = userRepository.findAllByOrderByFullName(page);
		List<UserDTO> result = new ArrayList<UserDTO>();
		for(UserEntity user : listUser) {
			result.add(mapper.map(user,UserDTO.class));
		}
		return result;
	}

	@Override
	public UserDTO findByUserNameAndStatus(String userName) {
		UserEntity userEntity = userRepository.findOneByUserNameAndStatus(userName, 1);
		if(userEntity != null ) {
			return mapper.map(userEntity,UserDTO.class);
		}else {
			return null;
		}
	}
	

	@Override
	public UserDTO findOneByUserName(String userName) {
		UserEntity userEntity = userRepository.findOneByUserName(userName);
		if(userEntity != null ) {
			UserDTO userDTO =  mapper.map(userEntity,UserDTO.class);
			List<RoleEntity> listRole = userEntity.getRoles();
			List<String> roleOfUser = new ArrayList<>();
			for(RoleEntity r : listRole) {
				roleOfUser.add(r.getCode());
			}
			userDTO.setRoles(roleOfUser);
			return userDTO;
		}else {
			return null;
		}
	}
	
	@Override
	@Transactional
	public UserDTO save(UserDTO userDTO) {
		try {
			if(this.findOneByUserName(userDTO.getUserName())!= null) {
				return null;
			}else {
				userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
				userDTO.setStatus(1);
				UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
				List<RoleEntity>roles = new ArrayList<RoleEntity>();
				if(SecurityUtils.getAuthorities().contains("ADMIN")) {
					if(userDTO.getRoles()!= null) {
						for(String codeRole : userDTO.getRoles()) {
							RoleEntity role = roleRespository.findOneByCode(codeRole);
							if(role!= null) {
								roles.add(role);
							}
						}
					}else {
						roles.add(roleRespository.findOneByCode("USER"));
					}
				}else {
					roles.add(roleRespository.findOneByCode("USER"));
				}

				userEntity.setRoles(roles);
				userEntity = userRepository.save(userEntity);	
				return  mapper.map(userEntity,UserDTO.class);
			}
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public UserDTO findById(Long id) {
		UserEntity userEntity = userRepository.findOne(id);
		if(userEntity!= null) {
			UserDTO user = mapper.map(userEntity, UserDTO.class);
			return user;
		}
		return null;
	}

	@Override
	public Long count() {
		
		return userRepository.count();
	}

	@Override
	@Transactional
	public boolean deleteUser(Long[] ids) {
		try {
			for(Long id : ids) {
				userRepository.delete(id);
			}
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	@Override
	@Transactional
	public boolean deleteUser(String[] names) {
		try {
			for(String name : names) {
				userRepository.deleteByUserName(name);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long countByUserNameORFullNameContaining(String name) {
		return userRepository.countByUserNameOrFullNameContaining(name,name);
	}

	@Override
	public List<UserDTO> findAllByUserNameOrFullNameContaining(String name) {
		try {
			List<UserEntity>  list =  userRepository.findAllByUserNameOrFullNameContaining(name,name);
			List<UserDTO> result = new ArrayList<>();
			for(UserEntity item : list){
				result.add(mapper.map(item,UserDTO.class));
			}
			return result;
		}catch (TransactionalException | EntityNotFoundException | DataAccessException | QueryException e){
			return null;
		}
	}

	@Override
	public List<UserDTO> findAllByUserNameOrFullNameContaining(String name, Pageable pageable) {
		try {
			List<UserEntity>  list =  userRepository.findAllByUserNameOrFullNameContaining(name,name,pageable);
			List<UserDTO> result = new ArrayList<>();
			for(UserEntity item : list){
				result.add(mapper.map(item,UserDTO.class));
			}
			return result;
		}catch (TransactionalException | EntityNotFoundException | DataAccessException | QueryException e){
			return null;
		}
	}

	@Override
	public UserDTO findCurrentUser() {
		Long id = SecurityUtils.getPrincipal().getId();
		UserEntity user = userRepository.getOne(id);
		UserDTO rs = mapper.map(user,UserDTO.class);
		rs.setShopId(user.getShop().getId());
		return rs;
	}

	@Override
	public UserDTO update(UserDTO userDTO) {
		try {
			userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
			List<RoleEntity>roles = new ArrayList<RoleEntity>();
			for(String codeRole : userDTO.getRoles()) {
				RoleEntity role = roleRespository.findOneByCode(codeRole);
				if(role!= null) {
					roles.add(role);
				}
			}
			userEntity.setRoles(roles);
			userEntity = userRepository.save(userEntity);
			if(userEntity!= null) {
				return mapper.map(userEntity,UserDTO.class);
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


}
