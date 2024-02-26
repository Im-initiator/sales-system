package com.leminhtien.service;

import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.ShopEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IShopService {
    List<ShopDTO> findAll();
    Page<ShopDTO> findAll(Pageable pageable);
    List<ShopDTO> findAllForList(Pageable pageable);
    List<ShopDTO> findAllByName(String name);
    ShopDTO save(ShopDTO shopDTO);
    int count();
    int countByName();
    ShopDTO update(ShopDTO shopDTO) throws Exception;
    boolean remove();
    int remove(Long[] ids);
    ShopDTO getShop();
    ShopDTO getShop(int id);
    ShopDTO saveCategory(Long[] ids);
    int removeCategory(Long[] ids);
    ShopDTO getShopByProductId(Long id);
    ShopDTO saveSize(Long[] ids);
    ShopDTO removeSize(Long[] ids);

}
