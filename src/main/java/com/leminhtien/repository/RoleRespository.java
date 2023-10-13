package com.leminhtien.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.RoleEntity;

public interface RoleRespository extends JpaRepository<RoleEntity, Long>{
	List<RoleEntity> findByCode(String code);
}
