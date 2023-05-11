package com.br.dsevoluction.mscartoes.entities;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DadosSolicitacaoEmissaCartao {

    private Long idCartao;
    private String cpf;
    private String endereco;
    private BigDecimal limiteLiberado;
}