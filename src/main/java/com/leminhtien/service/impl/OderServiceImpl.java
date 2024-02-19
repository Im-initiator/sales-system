package com.leminhtien.service.impl;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.*;
import com.leminhtien.repository.*;
import com.leminhtien.service.IOrderService;
import com.leminhtien.service.IShopService;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private IShopService shopService;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> rs = new ArrayList<>();
        try {
            Long id = SecurityUtils.getPrincipal().getId();
            List<OrderEntity> list = orderRepository.findAllByUserIdAndStatus(id,1);
            for (OrderEntity order : list){
                OrderDTO orderDTO = mapper.map(order,OrderDTO.class);
                orderDTO.setUserName(order.getUser().getUserName());
                rs.add(orderDTO);
            }
            //List<OrderDTO> list1 = list.stream().map(entity->mapper.map(entity, OrderDTO.class)).toList();
            return rs;
        }catch (DataAccessException| TransactionalException|NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Page<OrderDTO> findAll(Pageable pageable) {
        Page<OrderEntity> pages = orderRepository.findAll(pageable);
        return pages.map(entity->{
            OrderDTO order = mapper.map(entity,OrderDTO.class);
            order.setUserName(entity.getUser().getUserName());
            return order;
        });
    }

    @Override
    public OrderDTO findOne(Long id) {
        OrderEntity order = orderRepository.findOne(id);
        if (order!=null){
            OrderDTO result =  mapper.map(order,OrderDTO.class);
            result.setUserName(order.getUser().getUserName());
            return result;
        }
        throw new NullPointerException();
    }

    @Override
    public OrderDTO findOne(Long id, Long shipperId) {
        OrderEntity order = orderRepository.findOneByShipperIdAndId(shipperId,id);
        if (order!=null){
            OrderDTO result =  mapper.map(order,OrderDTO.class);
            result.setUserName(order.getUser().getUserName());
            return result;
        }
        throw new NullPointerException();
    }

    @Override
    public OrderDTO findOne(Long id, Long shipperId, byte status) {
        OrderEntity order = orderRepository.findOneByShipperIdAndIdAndStatus(shipperId,id,status);
        if (order!=null){
            OrderDTO result =  mapper.map(order,OrderDTO.class);
            result.setUserName(order.getUser().getUserName());
            return result;
        }
        throw   new NullPointerException();
    }

    @Override
    public OrderDTO findOneByShop(Long id , byte status) {
        Long shopId = shopService.getShop().getId();
        OrderEntity order = orderRepository.findOneByStatusAndIdAndProductShopId(status,id,shopId);
        if (order != null){
            return mapper.map(order,OrderDTO.class);
        }else {
            throw new NullPointerException();
        }
    }

    @Transactional
    @Override
    public List<OrderDTO> save(OrderDTO orderDTO) {
        List<OrderDTO> result = new ArrayList<>();
        Long userId = SecurityUtils.getPrincipal().getId();
        UserEntity userEntity = userRepository.findOne(userId);
        TransportEntity transport = transportRepository.findOneByCode(orderDTO.getTransportCode());
        for (Long id : orderDTO.getIds()){
            OrderEntity order = mapper.map(orderDTO,OrderEntity.class);
            CartEntity cart = cartRepository.findOneByIdAndUserId(id,userId);
            ProductEntity product = cart.getProduct();
            order.setTransport(transport);
            order.setProduct(product);
            order.setUser(userEntity);
            order.setStatus((byte)1);
            order.setQuantity(cart.getQuantity());
            order.setSendAddress(product.getShop().getAddress());
            order.setShipperId(-1L);
            order.setSize(cart.getSize().getName());
            LocalDateTime dateTime = LocalDateTime.now();
            String code = dateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            order.setCode(code);
            order = orderRepository.save(order);
            if (order!=null){
                result.add(mapper.map(order,OrderDTO.class));
                product.setQuantity(product.getQuantity()-order.getQuantity());
                productRepository.save(product);
            }
        }
        for (Long id : orderDTO.getIds()){
            cartRepository.delete(id);
        }
        return result;
    }

    @Transactional
    @Override
    public void remove(Long id) {
        OrderEntity order = orderRepository.findOneById(id);
        ProductEntity product = order.getProduct();
        product.setQuantity(product.getQuantity()+order.getQuantity());
        productRepository.save(product);
        orderRepository.delete(order);
    }

    @Transactional
    @Override
    public OrderDTO update(Long id, byte status) {
        OrderEntity order = orderRepository.findOneById(id);
        order.setStatus(status);
        if (status==4){
            UserEntity user = order.getProduct().getShop().getUser();
            user.setMoney(user.getMoney()+(order.getQuantity()*order.getProduct().getPrice()));
            userRepository.save(user);
        }
        order = orderRepository.save(order);
        if (order!=null){
            return mapper.map(order,OrderDTO.class);
        }
        return null;
    }

    @Override
    @Transactional
    public List<OrderDTO> setOrRemoveShipperId(Long userId, List<Long> orderIds,boolean flag) {
        List<OrderEntity> list = orderIds.stream().map(id->{
            OrderEntity order ;
           if (flag){
               order = orderRepository.findOne(id);
               order.setStatus((byte) 4);
               order.setShipperId(userId);
           }else {
               order = orderRepository.findOneByShipperIdAndId(userId,id);
               order.setStatus((byte) 3);
               order.setShipperId(-1L);
           }
            return orderRepository.save(order);
        }).toList();
        return list.stream().map(entity->mapper.map(entity,OrderDTO.class)).toList();
    }

    @Override
    public Page<OrderDTO> findAllByShipperIdAndStatus(Long id,byte status,Pageable pageable) {
        Page<OrderEntity> orders = orderRepository.findAllByShipperIdAndStatus(id,status,pageable);
        return orders.map(entity->mapper.map(entity,OrderDTO.class));
    }

    @Override
    public Page<OrderDTO> findAllByShipperIdAndStatusAndCodeContaining(Long id, byte status,String code, Pageable pageable) {
        Page<OrderEntity> pages = orderRepository.findAllByShipperIdAndStatusAndCodeContaining(id,status,code,pageable);
        return pages.map(entity->mapper.map(entity,OrderDTO.class));
    }

    @Override
    public Page<OrderDTO> getAllForShop(Pageable pageable) {
        Long shopId = shopService.getShop().getId();
        Page<OrderEntity> orders = orderRepository.findAllByProductShopId(shopId,pageable);
        return orders.map(entity->mapper.map(entity,OrderDTO.class));
    }

    @Override
    public Page<OrderDTO> getAllForShop(byte status, Pageable pageable) {
        Long shopId = shopService.getShop().getId();
        Page<OrderEntity> orders = orderRepository.findAllByStatusAndProductShopId(status,shopId,pageable);
        return orders.map(entity->mapper.map(entity,OrderDTO.class));
    }

    @Override
    public OrderDTO findOneByIdAndProductShopId(Long id) {
        Long shopId = shopService.getShop().getId();
        OrderEntity order = orderRepository.findOneByIdAndProductShopId(id,shopId);
        if (order != null){
            return mapper.map(order,OrderDTO.class);
        }else {
            throw new NullPointerException();
        }
    }

    @Override
    public List<OrderDTO> findAllByUserIdAndStatusBetween(Long userId, byte statusMin, byte statusMax) {
        List<OrderEntity> list = orderRepository.findAllByUserIdAndStatusBetweenOrderByStatusAsc(userId,statusMin,statusMax);
        return list.stream().map(entity->mapper.map(entity,OrderDTO.class)).toList();
    }

    @Override
    public int countAllByUserIdAndStatusBetween(Long userId, byte statusMin, byte statusMax) {
        return orderRepository.countByUserIdAndStatusBetween(userId,statusMin,statusMax);
    }

}
