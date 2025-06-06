package com.ferry.zenfoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ferry.zenfoodapi.core.validation.Groups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "estado_id")
    @ConvertGroup(to = Groups.EstadoId.class)
    @JsonIgnoreProperties(value = "nome", allowGetters = true)
    private Estado estado;
}
