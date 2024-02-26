package com.leminhtien.service.impl;

import com.leminhtien.dto.RoleDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.*;
import com.leminhtien.repository.*;
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
import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.io.EOFException;
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
    private ServletContext servletContext;

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SizeRepository sizeRepository;


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
    public List<ShopDTO> findAllForList(Pageable pageable) {
        try {
            List<ShopEntity> list = shopRepository.findAllByOrderByName(pageable);
            List<ShopDTO> result = new ArrayList<>();
            for (ShopEntity item : list){
                ShopDTO shop = mapper.map(item,ShopDTO.class);
                shop.setItems(item.getProducts().size());
                result.add(shop);
            }
            return result;
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
    @Transactional
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
    @Transactional
    public ShopDTO update(ShopDTO shopDTO) throws Exception {
            Long userId = SecurityUtils.getPrincipal().getId();
            ShopEntity shop = shopRepository.findOneByUserId(userId);
            shop.setEmail(shopDTO.getEmail());
            shop.setName(shopDTO.getName());
            shop.setPhoneNumber(shopDTO.getPhoneNumber());
            shop.setShortDescription(shopDTO.getShortDescription());
            shop.setAddress(shopDTO.getAddress());
            String path = shop.getThumbnail();
            if (shopDTO.getImgThumbnail().getSize()!=0){
                String newPath = FileUtils.saveImage(shopDTO.getImgThumbnail(),servletContext);
                shop.setThumbnail(newPath);
                FileUtils.deleteFile(path,servletContext);
            }
            shop = shopRepository.save(shop);
            if (shop!=null){
                return mapper.map(shop,ShopDTO.class);
            }else {
                throw new Exception();
            }
    }

    @Override
    @Transactional
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

    private boolean remove(Long id) {
        try {
            ShopEntity shop = shopRepository.findOne(id);
            if (shop!=null){
                UserEntity user = shop.getUser();
                RoleEntity roleSale = roleRespository.findOneByCode("SALER");
                user.getRoles().remove(roleSale);
                user.setShop(null);
                return shopRepository.deleteById(id) != 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    @Transactional
    public int remove(Long[] ids) {
        int count =0;
        try {
            for(Long id:ids){

                if(remove(id)){
                    count++;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return count;
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

    @Override
    public ShopDTO getShop(int id) {
        ShopEntity shopEntity = shopRepository.findOne((long) id);
        if (shopEntity!= null){
            return mapper.map(shopEntity,ShopDTO.class);
        }else {
            return null;
        }
    }

    @Transactional
    @Override
    public ShopDTO saveCategory(Long[] ids) {
        try {
            Long userId = SecurityUtils.getPrincipal().getId();
            UserEntity user = userRepository.findOne(userId);
            ShopEntity shop  = user.getShop();
            List<CategoryEntity> list = shop.getCategories();
            for (Long id : ids){
                CategoryEntity category = categoryRepository.findOne(id);
                if(!list.contains(category)){
                    list.add(category);
                }
            }
            shop.setCategories(list);
            ShopEntity s = shopRepository.save(shop);
            if(s!=null){
                return mapper.map(s,ShopDTO.class);
            }
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public int removeCategory(Long[] ids) {
            int count =0;
            try {
                Long userId = SecurityUtils.getPrincipal().getId();
                UserEntity user = userRepository.findOne(userId);
                ShopEntity shop  = user.getShop();
                List<CategoryEntity> list = shop.getCategories();
                for (Long id : ids){
                    CategoryEntity category = categoryRepository.findOne(id);
                    list.remove(category);
                    count++;
                }
                shop.setCategories(list);
                ShopEntity s = shopRepository.save(shop);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException();
            }
            return count;
    }

    @Override
    public ShopDTO getShopByProductId(Long id) {
        try {
            ProductEntity product = productRepository.findOne(id);
            ShopEntity shop = product.getShop();
            if (shop!=null){
                return mapper.map(shop,ShopDTO.class);
            }
        }catch (Exception e){

        }
        return null;
    }

    @Transactional
    @Override
    public ShopDTO saveSize(Long[] ids) {
        try {
            Long userId = SecurityUtils.getPrincipal().getId();
            UserEntity user = userRepository.findOne(userId);
            ShopEntity shop  = user.getShop();
            List<SizeEntity> sizes = shop.getSizes();
            for (Long id : ids){
                SizeEntity size = sizeRepository.findOne(id);
                if (!sizes.contains(size)){
                    sizes.add(size);
                }
            }
            shop.setSizes(sizes);
            ShopEntity shopEntity =  shopRepository.save(shop);
           if (shopEntity!=null){
               return mapper.map(shopEntity,ShopDTO.class);
           }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Transactional
    @Override
    public ShopDTO removeSize(Long[] ids) {
        try {
            Long userId = SecurityUtils.getPrincipal().getId();
            UserEntity user = userRepository.findOne(userId);
            ShopEntity shop  = user.getShop();
            List<SizeEntity> sizes = shop.getSizes();
            for (Long id : ids){
                SizeEntity size = sizeRepository.findOne(id);
                sizes.remove(size);
            }
            shop.setSizes(sizes);
            ShopEntity shopEntity =  shopRepository.save(shop);
            if (shopEntity!=null){
                return mapper.map(shopEntity,ShopDTO.class);
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
