package com.leminhtien.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{
	CategoryEntity findOneByCode(String code);
	List<CategoryEntity> findAllByOrderByName(Pageable pageable);

	List<CategoryEntity> findByNameContaining(String name, Pageable pageable);
	List<CategoryEntity> findByNameContaining(String name);

	long countByNameContaining(String name);
}
