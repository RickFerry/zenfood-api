package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {
}