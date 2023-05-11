package com.br.dsevoluction.msavaliadorcredito.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaCartao {
    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}
