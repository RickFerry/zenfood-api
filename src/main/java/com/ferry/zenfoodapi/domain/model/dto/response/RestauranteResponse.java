package com.ferry.zenfoodapi.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestauranteResponse {
    private Long id;
    private String nome;
    private BigDecimal taxaFrete;
    private CozinhaResponse cozinha;
}
