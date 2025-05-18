package com.ferry.zenfoodapi.api.controller;

import com.ferry.zenfoodapi.api.service.FormaPagamentoService;
import com.ferry.zenfoodapi.domain.exception.FormaPagamentoNaoEncontradaException;
import com.ferry.zenfoodapi.domain.model.FormaPagamento;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@AllArgsConstructor
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {
    private final FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<Page<FormaPagamento>> listar(Pageable pageable) {
        return ResponseEntity.ok(formaPagamentoService.listar(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(formaPagamentoService.buscar(id));
        } catch (FormaPagamentoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody FormaPagamento formaPagamento, UriComponentsBuilder uriBuilder) {
        FormaPagamento formaPagamentoSalva = formaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.created(uriBuilder.path("/{id}").buildAndExpand(formaPagamentoSalva.getId()).toUri())
                .body(formaPagamentoSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        try {
            return ResponseEntity.ok(formaPagamentoService.atualizar(id, formaPagamento));
        } catch (FormaPagamentoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            formaPagamentoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (FormaPagamentoNaoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
