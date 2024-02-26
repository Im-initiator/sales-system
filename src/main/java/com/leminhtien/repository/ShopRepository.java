package com.leminhtien.repository;

import com.leminhtien.entity.ShopEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<ShopEntity,Long> {
    List<ShopEntity> findAllByNameContainingOrderByName(String name, Pageable pageable);
    List<ShopEntity> findAllByNameContainingOrderByName(String name);

    List<ShopEntity> findAllByOrderByName(Pageable pageable);
    long countAllByNameContaining(String name);
    int deleteById(Long id);
    ShopEntity findOneByUserId(Long userId);
}
