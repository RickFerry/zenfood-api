package com.ferry.zenfoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
