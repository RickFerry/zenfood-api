package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    @Query(value = "from Restaurante r join fetch r.cozinha",
            countQuery = "select count(r) from Restaurante r")
    Page<Restaurante> findAll(Pageable pageable);
}