package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.CidadeService;
import com.ferry.zenfoodapi.domain.exception.CidadeNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<Page<Cidade>> listar(Pageable page) {
        return ResponseEntity.ok(cidadeService.listar(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cidadeService.buscar(id));
        } catch (CidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Cidade cidade, UriComponentsBuilder uri) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidade);
            return ResponseEntity.created(uri.path("/{id}").buildAndExpand(cidadeSalva.getId()).toUri())
                    .body(cidadeSalva);
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            return ResponseEntity.ok(cidadeService.atualizar(id, cidade));
        } catch (CidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            cidadeService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (CidadeNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
