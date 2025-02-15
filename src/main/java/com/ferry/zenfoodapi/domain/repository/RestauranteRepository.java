package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}