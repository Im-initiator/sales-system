package com.leminhtien.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.RoleEntity;

public interface RoleRespository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneByCode(String code);
}
