package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.EstadoService;
import com.ferry.zenfoodapi.domain.exception.EstadoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.exception.ViolacaoDeConstraintException;
import com.ferry.zenfoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<Page<Estado>> listar(Pageable pageable) {
        return ResponseEntity.ok(estadoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(estadoService.buscar(id));
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado, UriComponentsBuilder uriBuilder) {
        Estado estadoSalvo = estadoService.salvar(estado);
        return ResponseEntity.created(uriBuilder.path("/{id}")
                .buildAndExpand(estadoSalvo.getId())
                .toUri()).body(estadoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            return ResponseEntity.ok(estadoService.atualizar(id, estado));
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            estadoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EstadoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        } catch (ViolacaoDeConstraintException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
