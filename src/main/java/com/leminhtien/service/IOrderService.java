package com.leminhtien.service;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.ShopDTO;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();
    OrderDTO save(OrderDTO orderDTO);
    boolean remove(Long id);
}
