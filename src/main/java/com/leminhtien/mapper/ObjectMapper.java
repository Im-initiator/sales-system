package com.leminhtien.mapper;

public interface ObjectMapper <T,E>{
	
	public T toDTO(E entity);
	public E toEntity(T modelDTO);

}
