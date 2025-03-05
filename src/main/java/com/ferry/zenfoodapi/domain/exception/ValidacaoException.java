package com.ferry.zenfoodapi.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BeanPropertyBindingResult;

@Getter
@RequiredArgsConstructor
public class ValidacaoException extends RuntimeException {
    private final BeanPropertyBindingResult bindingResult;
}
