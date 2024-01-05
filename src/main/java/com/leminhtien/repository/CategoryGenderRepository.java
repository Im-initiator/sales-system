package com.leminhtien.repository;

import com.leminhtien.entity.CategoryGenderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryGenderRepository extends JpaRepository<CategoryGenderEntity,Long > {
    public List<CategoryGenderEntity> findAllByNameContaining(String name, Pageable pageable);
    public List<CategoryGenderEntity> findAllByNameContaining(String name);
    public long countByNameContaining(String name);
    public CategoryGenderEntity findOneByCode(String code);
    public List<CategoryGenderEntity> findAllByOrderByName(Pageable pageable);
}
