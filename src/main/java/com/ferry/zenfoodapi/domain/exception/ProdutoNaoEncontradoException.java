package com.ferry.zenfoodapi.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
