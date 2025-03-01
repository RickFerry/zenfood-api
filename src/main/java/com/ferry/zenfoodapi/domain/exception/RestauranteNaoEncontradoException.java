package com.ferry.zenfoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {
    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
