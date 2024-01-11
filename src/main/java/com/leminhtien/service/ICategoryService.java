package com.leminhtien.service;

import java.util.List;
import java.util.Map;

import com.leminhtien.dto.ShopDTO;
import org.springframework.data.domain.Pageable;

import com.leminhtien.dto.CategoryDTO;

public interface ICategoryService {
	List<CategoryDTO> findAll();
	List<CategoryDTO> findAll(Pageable pageable);
	Map<String, String> selectAll();
	CategoryDTO findOneByCode(String code);
	CategoryDTO save(CategoryDTO categoryDTO);
	boolean delete(Long[] ids);
	long count();

	long countByNameContaining(String name);

	List<CategoryDTO> findByNameContaining(String name, Pageable pageable);
	List<CategoryDTO> findByNameContaining(String name);

	CategoryDTO getCategoryByProductId(Long id);

	List<CategoryDTO> findAllByShopId();
	List<CategoryDTO> findAllByRemoveByShop();



}
