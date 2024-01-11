package com.leminhtien.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.leminhtien.entity.ProductEntity;
import com.leminhtien.entity.ShopEntity;
import com.leminhtien.entity.UserEntity;
import com.leminhtien.repository.ProductRepository;
import com.leminhtien.repository.UserRepository;
import com.leminhtien.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.leminhtien.dto.SizeDTO;
import com.leminhtien.entity.SizeEntity;
import com.leminhtien.repository.SizeRepository;
import com.leminhtien.service.ISizeService;

@Service
public class SizeServiceImpl implements ISizeService{

	@Autowired
	private SizeRepository sizeRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<SizeDTO> findAll(Pageable pageable) {
		try {
			List<SizeEntity> list = sizeRepository.findAllByOrderByName(pageable);
			List<SizeDTO> result = new ArrayList<SizeDTO>();
			for(SizeEntity size : list) {
				if(size!= null) {
					result.add(mapper.map(size,SizeDTO.class));
				}
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<String> findAll() {
		List<SizeEntity> list = sizeRepository.findAll();
		List<String> result = new ArrayList<String>();
		for(SizeEntity size : list) {
			if(size!= null) {
				result.add(size.getName());
			}
		}
		return result;
	}

	@Override
	public List<SizeDTO> selectAll() {
		try {
			List<SizeEntity> list = sizeRepository.findAll();
			List<SizeDTO> result = new ArrayList<SizeDTO>();
			for(SizeEntity size : list) {
				if(size!= null) {
					result.add(mapper.map(size,SizeDTO.class));
				}
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SizeDTO> findAllByNameOrderByName(String name,Pageable pageable) {
		try {
			List<SizeEntity> list = sizeRepository.findAllByNameContainingOrderByName(name,pageable);
			List<SizeDTO> result = new ArrayList<>();
			for(SizeEntity size : list) {
				if(size!= null) {
					result.add(mapper.map(size,SizeDTO.class));
				}
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SizeDTO> findAllByNameOrderByName(String name) {
		try {
			List<SizeEntity> list = sizeRepository.findAllByNameContainingOrderByName(name);
			List<SizeDTO> result = new ArrayList<>();
			for(SizeEntity size : list) {
				if(size!= null) {
					result.add(mapper.map(size,SizeDTO.class));
				}
			}
			return result;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public SizeDTO findOneByName(String name) {
		SizeEntity size = sizeRepository.findOneByName(name);
		if(size!= null) {
			return mapper.map(size, SizeDTO.class);
		}
		return null;
	}

	@Override
	public SizeDTO save(SizeDTO sizeDTO) {
		try{
			SizeEntity size = mapper.map(sizeDTO,SizeEntity.class);
			size = sizeRepository.save(size);
			if (size!= null){
				return mapper.map(size,SizeDTO.class);
			}else {
				return null;
			}
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean remove(SizeDTO sizeDTO) {
		try {
			SizeEntity size = mapper.map(sizeDTO,SizeEntity.class);
			sizeRepository.delete(size);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(long id) {
		try {
			sizeRepository.delete(id);
			return true;
		}catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public long count() {
		return sizeRepository.count();
	}

	@Override
	public long countByNam(String name) {
		return sizeRepository.countAllByNameContaining(name);
	}

	@Override
	public long remove(long[] ids) {
		long rs = 0;
		try {
			for (long id : ids){
				this.remove(id);
				rs++;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public List<SizeDTO> findAllByProductId(Long id) {
		ProductEntity product = productRepository.findOne(id);
		List<SizeEntity> sizes = product.getSizes();
		List<SizeDTO> result= new ArrayList<>();
		for(SizeEntity size : sizes){
			if(size!= null){
				result.add(mapper.map(size,SizeDTO.class));
			}
		}
		return result;
	}

	@Override
	public List<SizeDTO> findAllByShop() {
		Long userId = SecurityUtils.getPrincipal().getId();
		UserEntity user = userRepository.findOne(userId);
		ShopEntity shop  = user.getShop();
		List<SizeEntity> sizes = shop.getSizes();
		List<SizeDTO> result= new ArrayList<>();
		for(SizeEntity size : sizes){
			if(size!= null){
				result.add(mapper.map(size,SizeDTO.class));
			}
		}
		return result;
	}

	@Override
	public List<SizeDTO> findAllByShopRemove() {
		List<SizeDTO> list = this.selectAll();
		List<SizeDTO> sizeRemove = this.findAllByShop();
		for (SizeDTO s : sizeRemove){
			list.remove(s);
		}
		return list;
	}

}
