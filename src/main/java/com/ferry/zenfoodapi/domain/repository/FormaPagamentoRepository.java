package com.ferry.zenfoodapi.domain.repository;

import com.ferry.zenfoodapi.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {
}