package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.GrupoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Grupo;
import com.ferry.zenfoodapi.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository grupoRepository;

    @Transactional(readOnly = true)
    public Page<Grupo> listar(Pageable pageable) {
        return grupoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Grupo buscar(Long id) {
        return getGrupoOrElseThrow(id);
    }

    private Grupo getGrupoOrElseThrow(Long id) {
        return grupoRepository.findById(id).orElseThrow(
                () -> new GrupoNaoEncontradoException(String.format("Grupo com id %d não encontrado", id)));
    }
}
