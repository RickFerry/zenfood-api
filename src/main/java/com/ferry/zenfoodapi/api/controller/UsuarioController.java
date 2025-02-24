package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.UsuarioService;
import com.ferry.zenfoodapi.domain.exception.UsuarioNaoEncontradoException;
import com.ferry.zenfoodapi.domain.model.Usuario;
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
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<Usuario>> listar(Pageable pageable) {
        return ResponseEntity.ok(usuarioService.listar(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(usuarioService.buscar(id));
        } catch (UsuarioNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
