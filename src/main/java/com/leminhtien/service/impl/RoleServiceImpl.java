package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.repository.RoleRespository;
import com.leminhtien.service.IRoleService;

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
	public Map<String, String> selectAll() {
		List<RoleEntity> list = roleRespository.findAll();
		Map<String, String> result = new HashMap<String, String>();
		for(RoleEntity role: list) {
			result.put(role.getCode(),role.getName());
		}
		return result;
	}

}
