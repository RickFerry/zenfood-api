package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.ProdutoService;
import com.ferry.zenfoodapi.domain.exception.ProdutoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Produto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<Produto>> listar(Pageable pageable) {
        return ResponseEntity.ok(produtoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(produtoService.buscar(id));
        } catch (ProdutoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
