package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.entity.CategoryEntity;
import com.leminhtien.repository.CategoryRepository;
import com.leminhtien.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> list = categoryRepository.findAll();
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.add(mapper.map(category,CategoryDTO.class));
			}
		}
		return result;
	}
	
	@Override
	public List<CategoryDTO> findAll(Pageable pageable) {
		List<CategoryEntity> list = categoryRepository.findAllByOrderByName(pageable);
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.add(mapper.map(category,CategoryDTO.class));
			}
		}
		return result;
	}

	@Override
	public Map<String, String> selectAll() {		
		List<CategoryEntity> list = categoryRepository.findAll();
		Map<String, String> result = new HashMap<String, String>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.put(category.getCode(),category.getName());
			}
		}
		return result;
	}

	@Override
	public CategoryDTO findOneByCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		if(categoryEntity != null) {
			return mapper.map(categoryEntity, CategoryDTO.class);
		}
		return null;
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		try {
			CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
			 categoryEntity = categoryRepository.save(categoryEntity);
			 return mapper.map(categoryEntity, CategoryDTO.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long[] ids) {
		try {
			for(Long id : ids) {
				categoryRepository.delete(id);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	

}
