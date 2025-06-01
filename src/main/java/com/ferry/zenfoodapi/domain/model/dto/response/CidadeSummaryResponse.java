package com.ferry.zenfoodapi.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CidadeSummaryResponse {
    private Long id;
    private String nome;
    private String estado;
}
