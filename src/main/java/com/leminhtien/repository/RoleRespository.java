package com.leminhtien.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leminhtien.entity.RoleEntity;

import java.util.List;

public interface RoleRespository extends JpaRepository<RoleEntity, Long>{
	RoleEntity findOneByCode(String code);

	List<RoleEntity> findAllByOrderByName(Pageable pageable);
}
