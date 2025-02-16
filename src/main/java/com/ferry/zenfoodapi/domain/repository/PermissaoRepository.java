package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}