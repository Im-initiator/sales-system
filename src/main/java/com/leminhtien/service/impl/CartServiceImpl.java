package com.leminhtien.service.impl;

import com.leminhtien.dto.CartDTO;
import com.leminhtien.dto.SizeDTO;
import com.leminhtien.entity.CartEntity;
import com.leminhtien.entity.ProductEntity;
import com.leminhtien.entity.SizeEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.CartRepository;
import com.leminhtien.repository.ProductRepository;
import com.leminhtien.repository.SizeRepository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.service.ICartService;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public CartDTO save(CartDTO cartItemDTO) {
        Long userId = SecurityUtils.getPrincipal().getId();
        UserEntity user = userRepository.findOne(userId);
        CartEntity cart = cartRepository.findOneByUserIdAndProductId(userId, cartItemDTO.getProductId());
        if(cart!=null){
            cart.setQuantity(cart.getQuantity()+1);
            cart = cartRepository.save(cart);
            if (cart!=null){
                return mapper.map(cart,CartDTO.class);
            }
        }else {
            SizeEntity size = sizeRepository.findOneByName(cartItemDTO.getSizeName());
            ProductEntity product = productRepository.findOne(cartItemDTO.getProductId());
            CartEntity item = new CartEntity();
            item.setProduct(product);
            item.setSize(size);
            item.setUser(user);
            item.setQuantity(1);
            item.setStatus(1);
            item = cartRepository.save(item);
            if(item==null){
                return  null;
            }
            return mapper.map(item,CartDTO.class);
        }
        return null;

    }

    @Transactional
    @Override
    public CartDTO update(CartDTO cartDTO) {
            CartEntity cart = cartRepository.findOne(cartDTO.getId());
            cart.setQuantity(cartDTO.getQuantity());
            cart = cartRepository.save(cart);
            if (cart!=null){
                return mapper.map(cart,CartDTO.class);
            }
            return null;
    }

    @Transactional
    @Override
    public void remove(Long[] ids) {
        for (Long id : ids){
            cartRepository.delete(id);
        }
    }

    @Override
    public CartDTO findOneByUserIdAndProductId(Long userId, Long productId) {
        CartEntity cart = cartRepository.findOneByUserIdAndProductId(userId,productId);
        if (cart!=null){
            CartDTO cartDTO =  mapper.map(cart,CartDTO.class);
            cartDTO.setSize(cart.getSize().getName());
            return cartDTO;
        }
        return null;
    }

    @Override
    public List<CartDTO> findAll() {
       try {
           Long userId = SecurityUtils.getPrincipal().getId();
           UserEntity user = userRepository.findOne(userId);
           List<CartEntity> carts = user.getCart();
           List<CartDTO> result = new ArrayList<>();
           for (CartEntity cart: carts){
               if (cart!=null){
                   CartDTO cartDTO = mapper.map(cart,CartDTO.class);
                   cartDTO.setSize(cart.getSize().getName());
                   result.add(cartDTO);
               }
           }
           return result;
       }catch (Exception e){
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public int countByUser() {
        Long userId = SecurityUtils.getPrincipal().getId();
        return (int) cartRepository.countByUserId(userId);
    }

    @Override
    public CartDTO findByIdAndUserId(Long id) {
        Long userId = SecurityUtils.getPrincipal().getId();
        CartEntity cart = cartRepository.findOneByIdAndUserId(id,userId);
        if (cart!=null){
            return mapper.map(cart, CartDTO.class);
        }
        return null;
    }

    @Override
    public List<CartDTO> findByIdAndUserId(Long[] ids) {
        List<CartDTO> carts = new ArrayList<>();
        for (Long id : ids){
            CartDTO cart = this.findByIdAndUserId(id);
            if (cart!=null){
                carts.add(cart);
            }
        }
        return carts;
    }

    @Override
    public double getTotalPrice() {
        List<CartDTO> list = this.findAll();
        return list.stream().reduce(0.0,(sum,cart)->sum+(cart.getProduct().getPrice()*cart.getQuantity()),Double::sum);
    }


}
