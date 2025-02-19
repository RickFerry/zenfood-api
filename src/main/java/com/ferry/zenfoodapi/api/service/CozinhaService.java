package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.ViolacaoDeConstraintException;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import com.ferry.zenfoodapi.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CozinhaService {
    private final CozinhaRepository cozinhaRepository;

    @Transactional(readOnly = true)
    public Page<Cozinha> listar(Pageable pageable) {
        return cozinhaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Cozinha buscar(Long id) {
        return getOredElseThrow(id);
    }

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.save(cozinha);
    }

    @Transactional
    public Cozinha atualizar(Long id, Cozinha cozinha) {
        Cozinha cozinhaAtual = getOredElseThrow(id);
        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        return cozinhaRepository.save(cozinhaAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            cozinhaRepository.delete(getOredElseThrow(id));
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeConstraintException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso", id));
        }
    }

    private Cozinha getOredElseThrow(Long id) {
        return cozinhaRepository.findById(id).orElseThrow(
                () -> new CozinhaNaoEncontradaException(String.format("Cozinha de código %d não encontrada", id)));
    }
}
