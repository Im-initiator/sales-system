package com.leminhtien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.SizeEntity;

public interface SizeRepository extends JpaRepository<SizeEntity,Long>{
	SizeEntity findOneByName(String name);
}
