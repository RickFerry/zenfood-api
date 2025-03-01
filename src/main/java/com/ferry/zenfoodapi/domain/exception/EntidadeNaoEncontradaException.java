package com.ferry.zenfoodapi.domain.exception;

public abstract class EntidadeNaoEncontradaException extends NegocioException {
    protected EntidadeNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
