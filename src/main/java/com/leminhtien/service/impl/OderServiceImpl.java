package com.leminhtien.service.impl;

import com.leminhtien.dto.OrderDTO;
import com.leminhtien.entity.OrderEntity;
import com.leminhtien.repository.OrderRepository;
import com.leminhtien.service.IOrderService;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.TransactionalException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OderServiceImpl implements IOrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<OrderDTO> findAll() {
        List<OrderDTO> rs = new ArrayList<>();
        try {
            Long id = SecurityUtils.getPrincipal().getId();
            List<OrderEntity> list = orderRepository.findAllByUserIdAndStatus(id,1);
            for (OrderEntity order : list){
                rs.add(mapper.map(order,OrderDTO.class));
            }
            return rs;
        }catch (DataAccessException| TransactionalException|NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
