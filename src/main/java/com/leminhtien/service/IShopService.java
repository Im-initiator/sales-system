package com.leminhtien.service;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IShopService {
    List<ShopDTO> findAll();
    Page<ShopDTO> findAll(Pageable pageable);
    List<ShopDTO> findAllByName(String name);
    ShopDTO save(ShopDTO shopDTO);
    int count();
    int countByName();
    ShopDTO update(ShopDTO shopDTO);
    boolean remove();
    boolean remove(Long id);
    ShopDTO getShop();

}
