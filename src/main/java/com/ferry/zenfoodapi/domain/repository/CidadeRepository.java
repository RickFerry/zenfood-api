package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}