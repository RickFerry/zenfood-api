package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.RestauranteService;
import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Restaurante;
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
@RequestMapping("/restaurantes")
public class RestauranteController {
    private final RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<Page<Restaurante>> listar(Pageable pageable) {
        return ResponseEntity.ok(restauranteService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(restauranteService.buscar(id));
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
