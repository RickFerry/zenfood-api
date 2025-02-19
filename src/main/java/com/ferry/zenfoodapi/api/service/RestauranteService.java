package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import com.ferry.zenfoodapi.domain.repository.CozinhaRepository;
import com.ferry.zenfoodapi.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestauranteService {
    private final RestauranteRepository restauranteRepository;
    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public Page<Restaurante> listar(Pageable pageable) {
        return restauranteRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Restaurante buscar(Long id) {
        return getRestauranteOrElseThrow(id);
    }

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = getCozinhaOrElseThrow(cozinhaId);
        restaurante.setCozinha(cozinha);
        return restauranteRepository.save(restaurante);
    }

    @Transactional
    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante restauranteAtual = getRestauranteOrElseThrow(id);
        Long cozinhaId = restaurante.getCozinha().getId();
        Cozinha cozinha = getCozinhaOrElseThrow(cozinhaId);
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
        restauranteAtual.setCozinha(cozinha);
        return restauranteRepository.save(restauranteAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            restauranteRepository.delete(getRestauranteOrElseThrow(id));
        } catch (RestauranteNaoEncontradoException e) {
            throw new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id));
        }
    }

    private Cozinha getCozinhaOrElseThrow(Long cozinhaId) {
        return cozinhaRepository.findById(cozinhaId).orElseThrow(
                () -> new CozinhaNaoEncontradaException(String.format("Cozinha de código %d não encontrada", cozinhaId)));
    }

    private Restaurante getRestauranteOrElseThrow(Long id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> new RestauranteNaoEncontradoException(String.format("Restaurante de código %d não encontrado", id)));
    }
}
