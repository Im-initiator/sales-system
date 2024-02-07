package com.leminhtien.repository;

import java.util.List;

import org.springframework.data.domain.Page;
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
	long countByUserNameOrFullNameContaining(String userName, String fullName);
	List<UserEntity> findAllByUserNameOrFullNameContaining(String name,String fullName, Pageable pageable);
	List<UserEntity> findAllByUserNameOrFullNameContaining(String name,String fullName);
	Page<UserEntity> findAllByRolesCodeAndStatus( String role, int status,Pageable pageable);
	Page<UserEntity> findAllByUserNameContainingAndRolesNameAndStatus(String name,String role,int status,Pageable pageable);

	UserEntity findOneByUserNameAndRolesCodeAndStatus(String name,String code,int status);

}
