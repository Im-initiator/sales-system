package com.leminhtien.service;

import java.util.List;
import java.util.Map;

import com.leminhtien.dto.RoleDTO;

public interface IRoleService {
	List<RoleDTO> findAll();
	Map<String, String> selectAll();
}
