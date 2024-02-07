package com.leminhtien.service.impl;

import com.leminhtien.dto.TransportDTO;
import com.leminhtien.entity.TransportEntity;
import com.leminhtien.repository.TransportRepository;
import com.leminhtien.service.ITransportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements ITransportService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TransportRepository transportRepository;

    @Override
    public List<TransportDTO> findAll() {
        List<TransportEntity> list = transportRepository.findAll();
       return list.stream().map(entity->mapper.map(entity,TransportDTO.class)).collect(Collectors.toList());
    }

    @Override
    public Page<TransportDTO> findAll(Pageable pageable) {
        Page<TransportEntity> list = transportRepository.findAll(pageable);
        return list.map(entity->mapper.map(entity,TransportDTO.class));
    }

    @Override
    public TransportDTO findOneByCode(String code) {
        TransportEntity transport = transportRepository.findOneByCode(code);
        if (transport!= null){
            return mapper.map(transport,TransportDTO.class);
        }
        return null;
    }

    @Transactional
    @Override
    public TransportDTO save(TransportDTO transportDTO) {
        TransportEntity transport = mapper.map(transportDTO,TransportEntity.class);
        transport = transportRepository.save(transport);
        if (transport!=null){
            return mapper.map(transport,TransportDTO.class);
        }
        return null;
    }

    @Transactional
    @Override
    public int delete(Long[] ids) {
        int rs = 0;
        for (Long id:ids){
            transportRepository.delete(id);
            rs++;
        }
        return rs;
    }
}
