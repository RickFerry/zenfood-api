package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.RestauranteService;
import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.NegocioException;
import com.ferry.zenfoodapi.domain.exception.RestauranteNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Restaurante;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

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

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante, UriComponentsBuilder uriBuilder) {
        try {
            Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
            return ResponseEntity.created(uriBuilder.path("/{id}")
                    .buildAndExpand(restauranteSalvo.getId())
                    .toUri()).body(restauranteSalvo);
        } catch (CozinhaNaoEncontradaException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        try {
            return ResponseEntity.ok(restauranteService.atualizar(id, restaurante));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        try {
            return ResponseEntity.ok(restauranteService.atualizarParcial(id, campos));
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            restauranteService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (RestauranteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
