package com.leminhtien.service;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.repository.SizeRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();
    Page<OrderDTO> findAll(Pageable pageable);
    OrderDTO findOne(Long id);

    OrderDTO findOne(Long id, Long shipperId);
    OrderDTO findOne(Long id, Long shipperId,byte status);
    OrderDTO findOneByShop(Long id,byte status);
    List<OrderDTO> save(OrderDTO orderDTO);
    void remove(Long id);
    OrderDTO update(Long id, byte status);
    List<OrderDTO> setOrRemoveShipperId(Long userId,List<Long> orderIds,boolean flag);
    Page<OrderDTO> findAllByShipperIdAndStatus(Long id,byte status,Pageable pageable);
    Page<OrderDTO> findAllByShipperIdAndStatusAndCodeContaining(Long id,byte status, String code,Pageable pageable);

    Page<OrderDTO> getAllForShop(Pageable pageable);
    Page<OrderDTO> getAllForShop(byte status,Pageable pageable);
    OrderDTO findOneByIdAndProductShopId( Long id);

    List<OrderDTO> findAllByUserIdAndStatusBetween(Long userId,byte statusMin, byte statusMax);
}
