package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import com.ferry.zenfoodapi.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public Page<Restaurante> listar(Pageable pageable) {
        return restauranteRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id)));
    }
}
