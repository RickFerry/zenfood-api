package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.EstadoService;
import com.ferry.zenfoodapi.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<Page<Estado>> listar(Pageable pageable) {
        return ResponseEntity.ok(estadoService.listar(pageable));
    }
}
