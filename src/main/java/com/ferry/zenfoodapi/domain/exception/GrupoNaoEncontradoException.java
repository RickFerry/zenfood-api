package com.ferry.zenfoodapi.domain.exception;

public class GrupoNaoEncontradoException extends RuntimeException {
    public GrupoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
