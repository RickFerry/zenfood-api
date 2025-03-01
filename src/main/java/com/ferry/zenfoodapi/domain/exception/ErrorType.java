package com.ferry.zenfoodapi.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada");

    private final String path;
    private final String title;

    ErrorType(String path, String title) {
        this.path = "https://ferry.com.br" + path;
        this.title = title;
    }
}
