package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.model.Estado;
import com.ferry.zenfoodapi.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public Page<Estado> listar(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }
}
