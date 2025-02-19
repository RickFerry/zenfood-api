package com.ferry.zenfoodapi.domain.exception;

public class RestauranteNaoEncontradoException extends RuntimeException {
    public RestauranteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
