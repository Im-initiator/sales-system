package com.leminhtien.service.impl;

import com.leminhtien.dto.CategoryGenderDTO;
import com.leminhtien.entity.CategoryGenderEntity;
import com.leminhtien.repository.CategoryGenderRepository;
import com.leminhtien.service.ICategoryGenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.transaction.TransactionalException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryGenderServiceImpl implements ICategoryGenderService {

    @Autowired
    private CategoryGenderRepository categoryGenderRepository;

    @Autowired
    ModelMapper mapper;
    @Transactional
    @Override
    public CategoryGenderDTO save(CategoryGenderDTO categoryGenderDTO) {
        try {
            CategoryGenderEntity categoryGender = mapper.map(categoryGenderDTO,CategoryGenderEntity.class);
            return mapper.map(categoryGenderRepository.save(categoryGender),CategoryGenderDTO.class);
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return null;
        }
    }

    @Transactional
    @Override
    public boolean delete(CategoryGenderDTO categoryGenderDTO) {
        try {
            CategoryGenderEntity categoryGenderEntity = mapper.map(categoryGenderDTO,CategoryGenderEntity.class);
            categoryGenderRepository.delete(categoryGenderEntity);
            return true;
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return false;
        }
    }

    @Transactional
    @Override
    public Long delete(Long[] ids) {
       try {
        long count = 0;
        for (Long id : ids){
            categoryGenderRepository.delete(id);
            count++;
        }
        return count;
       }catch (DataAccessException | NullPointerException | TransactionalException e){
           return null;
       }
    }

    @Transactional
    @Override
    public CategoryGenderDTO update(CategoryGenderDTO categoryGenderDTO) {
        try{
            CategoryGenderEntity categoryGenderEntity = mapper.map(categoryGenderDTO,CategoryGenderEntity.class);
      //      CategoryGenderEntity categoryGenderOld = categoryGenderRepository.findOne(categoryGenderDTO.getId());
          return  mapper.map(categoryGenderRepository.save(categoryGenderEntity),CategoryGenderDTO.class);
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return null;
        }
    }

    @Override
    public List<CategoryGenderDTO> findAll(Pageable pageable) {
        try {
            List<CategoryGenderEntity> list = categoryGenderRepository.findAllByOrderByName(pageable);
            List<CategoryGenderDTO> result = new ArrayList<>();
            for (CategoryGenderEntity item: list){
                result.add(mapper.map(item,CategoryGenderDTO.class));
            }
            return result;
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return null;
        }
    }

    @Override
    public Map<String,String> findAll() {
        try {
            List<CategoryGenderEntity> list = categoryGenderRepository.findAll();
            Map<String,String> result = new HashMap<>();
            for (CategoryGenderEntity item: list){
                result.put(item.getCode(),item.getName());
            }
            return result;
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return null;
        }
    }

    @Override
    public long count() {
        return categoryGenderRepository.count();
    }

    @Override
    public long count(String name) {
        return categoryGenderRepository.countByNameContaining(name);
    }

    @Override
    public List<CategoryGenderDTO> findAllByNameContaining(String name,Pageable pageable) {
       try {
           List<CategoryGenderEntity> list = categoryGenderRepository.findAllByNameContaining(name,pageable);
           List<CategoryGenderDTO> result = new ArrayList<>();
           for (CategoryGenderEntity item : list){
               result.add(mapper.map(item,CategoryGenderDTO.class));
           }
           return result;
       }catch (DataAccessException | NullPointerException | TransactionalException e){
           return null;
       }
    }

    @Override
    public List<CategoryGenderDTO> findAllByNameContaining(String name) {
        try {
            List<CategoryGenderEntity> list = categoryGenderRepository.findAllByNameContaining(name);
            List<CategoryGenderDTO> result = new ArrayList<>();
            for (CategoryGenderEntity item : list){
                result.add(mapper.map(item,CategoryGenderDTO.class));
            }
            return result;
        }catch (DataAccessException | NullPointerException | TransactionalException e){
            return null;
        }
    }
}
