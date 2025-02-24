package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.GrupoService;
import com.ferry.zenfoodapi.domain.exception.GrupoNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Grupo;
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
@RequestMapping("/grupos")
public class GrupoController {
    private final GrupoService grupoService;

    @GetMapping
    public ResponseEntity<Page<Grupo>> listar(Pageable pageable) {
        return ResponseEntity.ok(grupoService.listar(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Grupo> buscar( @PathVariable Long id) {
        try {
            return ResponseEntity.ok(grupoService.buscar(id));
        } catch (GrupoNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
