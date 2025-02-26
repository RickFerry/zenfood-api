package com.ferry.zenfoodapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    @JoinColumn(name = "cidade_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;
}
