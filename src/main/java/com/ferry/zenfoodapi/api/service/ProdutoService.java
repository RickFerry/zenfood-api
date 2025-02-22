package com.ferry.zenfoodapi.api.service;

import com.ferry.zenfoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Produto;
import com.ferry.zenfoodapi.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public Page<Produto> listar(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Produto buscar(Long id) {
        return getProdutoOrElseThrow(id);
    }

    private Produto getProdutoOrElseThrow(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> new ProdutoNaoEncontradoException(String.format("Produto de id %d não encontrado", id)));
    }
}
