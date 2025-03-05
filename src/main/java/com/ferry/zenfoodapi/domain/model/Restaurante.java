package com.ferry.zenfoodapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @PositiveOrZero
    private BigDecimal taxaFrete;

    private Boolean ativo;
    private Boolean aberto;

    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurante", orphanRemoval = true)
    private Set<Produto> produtos = new LinkedHashSet<>();

    //    @JsonIgnore
    @Valid
    @NotNull
    @JoinColumn(name = "cozinha_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @ConvertGroup(to = Groups.CozinhaId.class)
    private Cozinha cozinha;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "restaurantes_formas_pagamento",
            joinColumns = @JoinColumn(name = "restaurante_id"),
            inverseJoinColumns = @JoinColumn(name = "forma_Pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new LinkedHashSet<>();
}
