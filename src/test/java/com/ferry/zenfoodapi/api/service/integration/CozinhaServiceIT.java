package com.ferry.zenfoodapi.api.service.integration;

import com.ferry.zenfoodapi.api.service.CozinhaService;
import com.ferry.zenfoodapi.domain.exception.CozinhaNaoEncontradaException;
import com.ferry.zenfoodapi.domain.exception.ViolacaoDeConstraintException;
import com.ferry.zenfoodapi.domain.model.Cozinha;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.DisabledIf;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.assertNotNull;


@SpringBootTest
@RunWith(SpringRunner.class)
public class CozinhaServiceIT {
    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void listar() {
    }

    @Test
    public void buscar() {
    }

    @Test
    public void salvarComSucesso() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Brasileira");

        Cozinha cozinhaSalva = cozinhaService.salvar(cozinha);

        assertNotNull(cozinhaSalva);
        assertNotNull(cozinhaSalva.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void salvarComFalha() {
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);

        cozinhaService.salvar(cozinha);
    }

    @Test
    public void atualizar() {
    }

    @Test(expected = ViolacaoDeConstraintException.class)
    public void removerCozinhaEmUso() {
        cozinhaService.remover(1L);
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void removerCozinhaIdInexistente() {
        cozinhaService.remover(100L);
    }
}