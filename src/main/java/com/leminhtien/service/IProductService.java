package com.leminhtien.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.leminhtien.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll();
	List<ProductDTO> findAll(Pageable pageable);
	ProductDTO findById(Long id);
	ProductDTO save(ProductDTO productDTO);
	boolean delete(Long[] ids);
	long count();
}
