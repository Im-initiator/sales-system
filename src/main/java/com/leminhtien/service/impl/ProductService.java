package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.ProductDTO;
import com.leminhtien.entity.CategoryEntity;
import com.leminhtien.entity.ProductEntity;
import com.leminhtien.entity.SizeEntity;
import com.leminhtien.repository.CategoryRepository;
import com.leminhtien.repository.ProductRepository;
import com.leminhtien.repository.SizeRepository;
import com.leminhtien.service.IProductService;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ProductDTO> findAll() {
		List<ProductEntity> list = productRepository.findAll();
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		for(ProductEntity product: list) {
			if(product!= null) {
				result.add(mapper.map(product,ProductDTO.class));
			}
		}
		return result;
	}
	
	@Override
	public List<ProductDTO> findAll(Pageable pageable) {
		List<ProductEntity> list = productRepository.findAllByOrderByName(pageable);
		List<ProductDTO> result = new ArrayList<ProductDTO>();
		for(ProductEntity product: list) {
			if(product!= null) {
				result.add(mapper.map(product,ProductDTO.class));
			}
		}
		return result;
	}

	@Override
	public ProductDTO findById(Long id) {
		ProductEntity product = productRepository.findOne(id);
		if(product!= null) {
			ProductDTO productDTO =  mapper.map(product, ProductDTO.class);
			productDTO.setCategoryCode(productDTO.getCategory().getCode());
			List<String> sizes  = new ArrayList<String>();
			List<SizeEntity> sizeProduct = product.getSizes();
			for(SizeEntity size : sizeProduct) {
				if(size!= null) {
					sizes.add(size.getName());
				}
			}
			productDTO.setSizes(sizes);
			return productDTO;
			
		}
		return null;
	}

	@Override
	@Transactional
	public ProductDTO save(ProductDTO productDTO) {
		try {
			if(productDTO!= null) {
				ProductEntity productEntity = mapper.map(productDTO, ProductEntity.class);
				List<SizeEntity> sizes = new ArrayList<SizeEntity>();
				for(String size:productDTO.getSizes()) {
					SizeEntity sizeEntity = sizeRepository.findOneByName(size);
					if(sizeEntity!= null) {
						sizes.add(sizeEntity);
					}
				}
				productEntity.setSizes(sizes);
//				CategoryDTO categoryDTO = categoryService.findOneByCode(productDTO.getCategoryCode());
				CategoryEntity categoryEntity = categoryRepository.findOneByCode(productDTO.getCategoryCode());
				if(categoryEntity!= null) {
					productEntity.setCategory(categoryEntity);
				}
				
				if(productEntity.getSizes().size() != 0 && productEntity.getCategory() != null) {
				try {
					productEntity = productRepository.save(productEntity);
				} catch (Exception e) {
					e.printStackTrace();
				}
					if(productEntity != null) {
						return mapper.map(productEntity, ProductDTO.class);
					}
				}	
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	@Override
	@Transactional
	public boolean delete(Long[] ids) {
		boolean flag = true;
		try {
			for(Long id : ids ) {
				try {
					productRepository.delete(id);
				} catch (Exception e) {
					e.printStackTrace();
					flag = false;
				}
			}
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public long count() {
		return productRepository.count();
	}


	
	
	

}
