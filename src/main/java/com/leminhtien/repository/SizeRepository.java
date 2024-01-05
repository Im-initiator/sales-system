package com.leminhtien.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.SizeEntity;

import java.util.List;

public interface SizeRepository extends JpaRepository<SizeEntity,Long>{
	SizeEntity findOneByName(String name);
	List<SizeEntity> findAllByOrderByName(Pageable pageable);
	List<SizeEntity> findAllByNameContainingOrderByName(String name,Pageable pageable);
	List<SizeEntity> findAllByNameContainingOrderByName(String name);
	Long countAllByNameContaining(String name);

}
