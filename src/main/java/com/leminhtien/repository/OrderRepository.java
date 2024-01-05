package com.leminhtien.repository;

import com.leminhtien.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    OrderEntity findOneById(Long id);
    List<OrderEntity> findAllByUserIdAndStatus(Long userId,int status);
}
