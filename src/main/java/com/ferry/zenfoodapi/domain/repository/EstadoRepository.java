package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
}