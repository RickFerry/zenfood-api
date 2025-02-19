package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.CozinhaService;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<Page<Cozinha>> listar(Pageable pageable) {
        return ResponseEntity.ok(cozinhaService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(cozinhaService.buscar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha, UriComponentsBuilder uriBuilder) {
        Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);
        return ResponseEntity.created(uriBuilder.path("/{id}")
                .buildAndExpand(cozinhaSalva.getId())
                .toUri()).body(cozinhaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        try {
            Cozinha cozinhaAtualizada = cozinhaService.atualizar(id, cozinha);
            return ResponseEntity.ok(cozinhaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            cozinhaService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
