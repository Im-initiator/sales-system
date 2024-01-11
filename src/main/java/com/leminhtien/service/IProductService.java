package com.leminhtien.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.leminhtien.dto.ProductDTO;

public interface IProductService {
	List<ProductDTO> findAll();
	List<ProductDTO> findAll(Pageable pageable);
	Page<ProductDTO> selectAll(Pageable pageable);
	List<ProductDTO> findAllByShopId(Long id,Pageable pageable);
	ProductDTO findById(Long id);
	ProductDTO save(ProductDTO productDTO);
	boolean delete(Long[] ids);
	long count(boolean isTotal);
	long countByName(String name);
	List<ProductDTO> searchByName(String name,Pageable pageable);
}
