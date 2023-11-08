package com.leminhtien.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leminhtien.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long>{
	UserEntity findOneByUserNameAndStatus(String userName,int status);
	List<UserEntity> findAllByOrderByFullName(Pageable pageable);
	UserEntity findOneByUserName(String userName);
	void deleteByUserName(String name);
}
