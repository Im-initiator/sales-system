package com.leminhtien.service.impl;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.RoleEntity;
import com.leminhtien.entity.ShopEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.RoleRespository;
import com.leminhtien.repository.ShopRepository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.service.IShopService;
import com.leminhtien.utils.FileUtils;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.TransactionalException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    ServletContext servletContext;

    @Autowired
    private RoleRespository roleRespository;

    @Override
    public List<ShopDTO> findAll() {
        List<ShopDTO> rs = new ArrayList<>();
       try {
           List<ShopEntity> shops = shopRepository.findAll();
           for (ShopEntity shop : shops){
               ShopDTO s =  mapper.map(shop,ShopDTO.class);
               s.setUserId(shop.getUser().getId());
               rs.add(s);
           }
           return rs;
       }catch (NullPointerException| TransactionalException| DataAccessException e){
           e.printStackTrace();
           return null;
       }
    }

    //viết đến đây
    @Override
    public Page<ShopDTO> findAll(Pageable pageable) {
        try {
            Page<ShopEntity> shops = shopRepository.findAll(pageable);
            return shops.map(entity->mapper.map(entity,ShopDTO.class));
        }catch (NullPointerException| TransactionalException| DataAccessException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ShopDTO> findAllByName(String name) {
        return null;
    }

    @Override
    public ShopDTO save(ShopDTO shopDTO) {
        if (shopDTO!=null){
            String img = "";
            try {
                Long userId = SecurityUtils.getPrincipal().getId();
                UserEntity user = userRepository.getOne(userId);
                ShopEntity userShop = user.getShop();
                if(userShop!=null){
                    return null;
                }
                ShopEntity shopEntity = mapper.map(shopDTO,ShopEntity.class);
                try {
                    if (shopDTO.getImgThumbnail()!= null){
                         img = FileUtils.saveImage(shopDTO.getImgThumbnail(),servletContext);
                        if (img!= null){
                            shopEntity.setThumbnail(img);
                        }else {
                            return null;
                        }
                    }
                }catch (IOException e){
                    throw new Exception();
                }
                RoleEntity roleEntity = roleRespository.findOneByCode("SALER");
                List<RoleEntity> roles = user.getRoles();
                roles.add(roleEntity);
                user.setRoles(roles);
                userRepository.save(user);
                shopEntity = shopRepository.save(shopEntity);
                if (shopEntity!= null){
                    user.setShop(shopEntity);
                    userRepository.save(user);
                    return mapper.map(shopEntity,ShopDTO.class);
                }else {
                    return null;
                }
            }catch (Exception e){
                if (!img.equals("")){
                    FileUtils.deleteFile(img,servletContext);
                }
                return null;
            }
        }else {
            return null;
        }
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public int countByName() {
        return 0;
    }

    @Override
    public ShopDTO update(ShopDTO shopDTO) {
        try {
            ShopEntity shopOld = shopRepository.findOne(shopDTO.getId());
            ShopEntity shopUpdate = mapper.map(shopDTO,ShopEntity.class);
            if(shopDTO.getImgThumbnail().getSize()!=0){
                FileUtils.deleteFile(shopOld.getThumbnail(),servletContext);
                String path = FileUtils.saveImage(shopDTO.getImgThumbnail(),servletContext);
                shopUpdate.setThumbnail(path);
            }else {
                shopUpdate.setThumbnail(shopOld.getThumbnail());
            }
            return mapper.map(shopRepository.save(shopUpdate),ShopDTO.class);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean remove() {
        try {
            Long userId = SecurityUtils.getPrincipal().getId();
            Long shopId = userRepository.findOne(userId).getShop().getId();
            shopRepository.delete(shopId);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean remove(Long id) {
        try {
            shopRepository.delete(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ShopDTO getShop() {
        Long userId = SecurityUtils.getPrincipal().getId();
        ShopEntity shopEntity = userRepository.findOne(userId).getShop();
        System.out.println("sfs");
        if (shopEntity!= null){
            return mapper.map(shopEntity,ShopDTO.class);
        }else {
            return null;
        }
    }

}
