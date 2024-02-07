package com.leminhtien.repository;

import com.leminhtien.entity.TransportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<TransportEntity,Long> {
    TransportEntity findOneByCode(String code);
}
