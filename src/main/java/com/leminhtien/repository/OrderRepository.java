package com.leminhtien.repository;

import com.leminhtien.entity.OrderEntity;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
    OrderEntity findOneById(Long id);
    List<OrderEntity> findAllByUserIdAndStatus(Long userId,int status);
    Page<OrderEntity> findAllByShipperIdAndStatus(Long id,byte status, Pageable pageable);
    Page<OrderEntity> findAllByShipperIdAndStatusAndCodeContaining(Long id,byte status,String code,Pageable pageable);

    int countByUserIdAndStatusBetween(Long id,byte min, byte max);
    List<OrderEntity> findAllByUserIdAndStatusBetweenOrderByStatusAsc(Long id,byte min,byte max);
    OrderEntity findOneByShipperIdAndId(Long shipperId, Long orderId);
    OrderEntity findOneByShipperIdAndIdAndStatus(Long shipperId,Long orderId,byte status);
    Page<OrderEntity> findAllByProductShopId(Long shopId,Pageable pageable);
    Page<OrderEntity> findAllByStatusAndProductShopId(byte status,Long shopId,Pageable pageable);
    OrderEntity findOneByIdAndProductShopId(Long id,Long shopId);
    OrderEntity findOneByStatusAndIdAndProductShopId(byte status,Long id,Long shopId);
}
