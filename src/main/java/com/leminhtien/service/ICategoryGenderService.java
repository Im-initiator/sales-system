package com.leminhtien.service;

import com.leminhtien.dto.CategoryGenderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ICategoryGenderService {
    public CategoryGenderDTO save(CategoryGenderDTO categoryGenderDTO);
    public boolean delete(CategoryGenderDTO categoryGenderDTO);
    public Long delete(Long[] ids);

    public CategoryGenderDTO update(CategoryGenderDTO categoryGenderDTO);
    public List<CategoryGenderDTO> findAll(Pageable pageable);
    public Map<String,String> findAll();

    public long count();
    public long count(String name);

    public List<CategoryGenderDTO> findAllByNameContaining(String name,Pageable pageable);
}
