package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.SizeDTO;
import com.leminhtien.entity.SizeEntity;
import com.leminhtien.repository.SizeRepository;
import com.leminhtien.service.ISizeService;

@Service
public class SizeServiceImpl implements ISizeService{

	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public List<String> findAll() {
		List<SizeEntity> list = sizeRepository.findAll();
		List<String> result = new ArrayList<String>();
		for(SizeEntity size : list) {
			if(size!= null) {
				result.add(size.getName());
			}
		}
		return result;
	}

	@Override
	public SizeDTO findOneByName(String name) {
		SizeEntity size = sizeRepository.findOneByName(name);
		if(size!= null) {
			return mapper.map(size, SizeDTO.class);
		}
		return null;
	}

}
