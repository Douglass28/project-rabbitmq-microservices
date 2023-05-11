package com.br.dsevoluction.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoAprovado {
    private String nome;
    private String bandeira;
    private BigDecimal LimiteAprovado;
}
