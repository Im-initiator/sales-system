package com.leminhtien.service;

import java.util.List;

import com.leminhtien.dto.SizeDTO;

public interface ISizeService {
//	Map<String, String> selectAll();
	List<String> findAll();
	SizeDTO findOneByName(String name);
}
