package com.leminhtien.service;

import com.leminhtien.dto.CategoryDTO;
import com.leminhtien.dto.TransportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;


public interface ITransportService {

    List<TransportDTO> findAll();
    Page<TransportDTO> findAll(Pageable pageable);
    TransportDTO findOneByCode(String code);
    TransportDTO save(TransportDTO categoryDTO);
    int delete(Long[] ids);
}
