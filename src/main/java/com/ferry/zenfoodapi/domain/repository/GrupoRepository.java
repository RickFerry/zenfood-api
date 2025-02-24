package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoRepository extends JpaRepository<Grupo, Long> {
}