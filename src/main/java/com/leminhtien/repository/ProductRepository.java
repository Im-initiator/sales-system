package com.leminhtien.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	List<ProductEntity> findAllByOrderByName(Pageable pageable);
	List<ProductEntity> findAllByNameContaining(String name, Pageable pageable);
	long countByNameContaining(String name);

	long countByShopId(Long id);
	List<ProductEntity>findAllByShopId(Pageable pageable,Long id);

}
