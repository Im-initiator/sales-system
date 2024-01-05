package com.leminhtien.service;

import java.util.List;

import com.leminhtien.dto.SizeDTO;
import org.springframework.data.domain.Pageable;

public interface ISizeService {
//	Map<String, String> selectAll();
	List<SizeDTO> findAll(Pageable pageable);
	List<String> findAll();
	List<SizeDTO> selectAll();

	List<SizeDTO> findAllByNameOrderByName(String name,Pageable pageable);
	List<SizeDTO> findAllByNameOrderByName(String name);
	SizeDTO findOneByName(String name);

	SizeDTO save(SizeDTO sizeDTO);
	boolean remove(SizeDTO sizeDTO);
	boolean remove(long id);

	long count();
	long countByNam(String name);
	long remove(long[] ids);


}
