package com.leminhtien.service;

import com.leminhtien.dto.CartDTO;

import java.util.List;

public interface ICartService {
    CartDTO save(CartDTO cartItemDTO);
    CartDTO update(CartDTO cartDTO);
    void remove(Long[] ids);
    CartDTO findOneByUserIdAndProductId(Long userId, Long productId);
    List<CartDTO> findAll();

    int countByUser();

    double getTotalPrice();
}
