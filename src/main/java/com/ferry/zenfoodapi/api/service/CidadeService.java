package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.CidadeNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Cidade;
import com.ferry.zenfoodapi.domain.model.Estado;
import com.ferry.zenfoodapi.domain.repository.CidadeRepository;
import com.ferry.zenfoodapi.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CidadeService {
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;

    @Transactional(readOnly = true)
    public Page<Cidade> listar(Pageable page) {
        return cidadeRepository.findAll(page);
    }

    @Transactional(readOnly = true)
    public Cidade buscar(Long id) {
        return getCidadeOrElseThrow(id);
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = getEstadoOrElseThrow(estadoId);
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeSalva = getCidadeOrElseThrow(id);
        Long estadoId = cidade.getEstado().getId();
        Estado estado = getEstadoOrElseThrow(estadoId);
        BeanUtils.copyProperties(cidade, cidadeSalva, "id");
        cidadeSalva.setEstado(estado);
        return cidadeRepository.save(cidadeSalva);
    }

    @Transactional
    public void remover(Long id) {
        cidadeRepository.delete(getCidadeOrElseThrow(id));
    }

    Cidade getCidadeOrElseThrow(Long id) {
        return cidadeRepository.findById(id).orElseThrow(
                () -> new CidadeNaoEncontradaException(String.format("Cidade com id %d não encontrada", id)));
    }

    private Estado getEstadoOrElseThrow(Long estadoId) {
        return estadoRepository.findById(estadoId).orElseThrow(
                () -> new EstadoNaoEncontradoException(String.format("Estado com id %d não encontrado", estadoId)));
    }
}
