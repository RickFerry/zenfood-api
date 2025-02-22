package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}