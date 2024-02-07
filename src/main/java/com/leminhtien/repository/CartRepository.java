package com.leminhtien.repository;

import com.leminhtien.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<CartEntity,Long> {
    CartEntity findOneByUserIdAndProductId(Long userId, Long productId);
    List<CartEntity> findAllByUserId(Long id);
    CartEntity findOneByIdAndUserId(Long id, Long userId);
    long countByUserId(Long id);
}
