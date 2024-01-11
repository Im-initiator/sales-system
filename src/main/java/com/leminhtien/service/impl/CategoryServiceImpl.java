package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.dto.ShopDTO;
import com.leminhtien.entity.ProductEntity;
import com.leminhtien.entity.ShopEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.ProductRepository;
import com.leminhtien.repository.ShopRepository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.entity.CategoryEntity;
import com.leminhtien.repository.CategoryRepository;
import com.leminhtien.service.ICategoryService;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Propagation;

@Service
public class CategoryServiceImpl implements ICategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired 
	private ModelMapper mapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShopRepository shopRepository;

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> list = categoryRepository.findAll();
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.add(mapper.map(category,CategoryDTO.class));
			}
		}
		return result;
	}
	
	@Override
	public List<CategoryDTO> findAll(Pageable pageable) {
		List<CategoryEntity> list = categoryRepository.findAllByOrderByName(pageable);
		List<CategoryDTO> result = new ArrayList<CategoryDTO>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.add(mapper.map(category,CategoryDTO.class));
			}
		}
		return result;
	}

	@Override
	public Map<String, String> selectAll() {		
		List<CategoryEntity> list = categoryRepository.findAll();
		Map<String, String> result = new HashMap<String, String>();
		for(CategoryEntity category: list) {
			if(category!= null) {
				result.put(category.getCode(),category.getName());
			}
		}
		return result;
	}

	@Override
	public CategoryDTO findOneByCode(String code) {
		CategoryEntity categoryEntity = categoryRepository.findOneByCode(code);
		if(categoryEntity != null) {
			return mapper.map(categoryEntity, CategoryDTO.class);
		}
		return null;
	}

	@Override
	public CategoryDTO save(CategoryDTO categoryDTO) {
		try {
			CategoryEntity categoryEntity = mapper.map(categoryDTO, CategoryEntity.class);
			 categoryEntity = categoryRepository.save(categoryEntity);
			 return mapper.map(categoryEntity, CategoryDTO.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional
	public boolean delete(Long[] ids) {
		try {
			for(Long id : ids) {
				categoryRepository.delete(id);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long count() {
		return categoryRepository.count();
	}

	@Override
	public long countByNameContaining(String name) {
		return categoryRepository.countByNameContaining(name);
	}

	@Override
	public List<CategoryDTO> findByNameContaining(String name, Pageable pageable) {
		try{
			List<CategoryEntity> list = categoryRepository.findByNameContaining(name,pageable);
			List<CategoryDTO> result = new ArrayList<>();
			for (CategoryEntity item : list){
				result.add(mapper.map(item,CategoryDTO.class));
			}
			return result;
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			return null;
		}
	}

	@Override
	public List<CategoryDTO> findByNameContaining(String name) {
		try{
			List<CategoryEntity> list = categoryRepository.findByNameContaining(name);
			List<CategoryDTO> result = new ArrayList<>();
			for (CategoryEntity item : list){
				result.add(mapper.map(item,CategoryDTO.class));
			}
			return result;
		}catch (TransactionSystemException | DataAccessException | EntityNotFoundException e){
			return null;
		}
	}

	@Override
	public CategoryDTO getCategoryByProductId(Long id) {
		try {
			ProductEntity product = productRepository.findOne(id);
			if(product!=null){
				CategoryEntity category = product.getCategory();
				return mapper.map(category,CategoryDTO.class);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
//dang làm đến đây
	@Override
	public List<CategoryDTO> findAllByShopId() {
		try {
			Long userId = SecurityUtils.getPrincipal().getId();
			UserEntity user = userRepository.findOne(userId);
			Long shopId  = user.getShop().getId();
			if(shopId!= null){
				List<CategoryEntity> list = categoryRepository.findAllByShopsId(shopId);
				List<CategoryDTO> result = new ArrayList<>();
				for (CategoryEntity category : list){
					if(category!= null){
						result.add(mapper.map(category,CategoryDTO.class));
					}
				}
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryDTO> findAllByRemoveByShop() {
		List<CategoryDTO> list = this.findAll();
		List<CategoryDTO> categoryRemove = this.findAllByShopId();
		for (CategoryDTO category:categoryRemove){
			list.remove(category);
		}

		return list;
	}


}
