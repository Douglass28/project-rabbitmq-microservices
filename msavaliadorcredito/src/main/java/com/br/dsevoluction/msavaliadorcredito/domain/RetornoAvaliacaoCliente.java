package com.br.dsevoluction.msavaliadorcredito.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@AllArgsConstructor
@Data
public class RetornoAvaliacaoCliente {

    private List<CartaoAprovado> cartoes;
}
