package com.ferry.zenfoodapi.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeResponse {
    private Long id;
    private String nome;
    private EstadoResponse estado;
}
