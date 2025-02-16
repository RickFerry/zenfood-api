package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.CozinhaService;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<Page<Cozinha>> listar(Pageable pageable) {
        return ResponseEntity.ok(cozinhaService.listar(pageable));
    }
}
