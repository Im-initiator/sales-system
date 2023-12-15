package com.leminhtien.service;

import java.util.List;
import java.util.Map;

import com.leminhtien.dto.RoleDTO;
import org.springframework.data.domain.Pageable;

public interface IRoleService {
	List<RoleDTO> findAll();
	List<RoleDTO> findAll(Pageable pageable);
	Map<String, String> selectAll();
	RoleDTO save(RoleDTO roleDTO);
	Long delete(Long id);

	int delete(Long[] ids);
	long count();

}
