package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.repository.RoleRespository;
import com.leminhtien.service.IRoleService;
import org.springframework.transaction.TransactionSystemException;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class RoleServiceImpl implements IRoleService{

	@Autowired
	RoleRespository roleRespository;

	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<RoleDTO> findAll() {
		
		List<RoleEntity> list = roleRespository.findAll();
		List<RoleDTO> result = new ArrayList<RoleDTO>();
		for(RoleEntity role : list) {
			RoleDTO r = mapper.map(role, RoleDTO.class);
			result.add(r);
		}
		return result;
	}

	@Override
	public List<RoleDTO> findAll(Pageable pageable) {
		try{
			List<RoleEntity> list = roleRespository.findAllByOrderByName(pageable);
			List<RoleDTO> rs = new ArrayList<>();
			if(!list.isEmpty()){
				for (RoleEntity role : list) {
					rs.add(mapper.map(role,RoleDTO.class));
				}
				return rs;
			}else{
				return null;
			}
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Map<String, String> selectAll() {
		List<RoleEntity> list = roleRespository.findAll();
		Map<String, String> result = new HashMap<String, String>();
		for(RoleEntity role: list) {
			result.put(role.getCode(),role.getName());
		}
		return result;
	}

	@Transactional
	@Override
	public RoleDTO save(RoleDTO roleDTO) {
		try{
			RoleEntity role = mapper.map(roleDTO,RoleEntity.class);
			role = roleRespository.save(role);
			if(role!= null){
				return mapper.map(role,RoleDTO.class);
			}else {
				return null;
			}
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@Override
	public Long delete(Long id) {
		try {
			roleRespository.delete(id);
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			return null;
		}
		return 1L;
	}

	@Transactional
	@Override
	public int delete(Long[] ids) {
		int count =0;
		try {
			for(Long id : ids){
				 this.delete(id);
				 count++;
			}
			return count;
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			return 0;
		}

	}

	@Override
	public long count() {
		return roleRespository.count();
	}

}
