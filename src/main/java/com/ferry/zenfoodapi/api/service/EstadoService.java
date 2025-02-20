package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.exception.ViolacaoDeConstraintException;
import com.ferry.zenfoodapi.domain.model.Estado;
import com.ferry.zenfoodapi.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional(readOnly = true)
    public Estado buscar(Long id) {
        return getEstadoOrElseThrow(id);
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoAtual = getEstadoOrElseThrow(id);
        BeanUtils.copyProperties(estado, estadoAtual, "id");
        return estadoRepository.save(estadoAtual);
    }

    @Transactional
    public void remover(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ViolacaoDeConstraintException(
                    String.format("Estado de id %d não pode ser removido, pois está em uso", id));
        }
    }

    private Estado getEstadoOrElseThrow(Long id) {
        return estadoRepository.findById(id).orElseThrow(
                () -> new EstadoNaoEncontradoException(String.format("Estado com id %d não encontrado", id)));
    }
}
