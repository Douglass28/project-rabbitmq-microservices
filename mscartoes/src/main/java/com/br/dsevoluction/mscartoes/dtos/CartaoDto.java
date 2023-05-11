package com.br.dsevoluction.mscartoes.dtos;

import com.br.dsevoluction.mscartoes.entities.Cartao;
import com.br.dsevoluction.mscartoes.enums.BandeiraCartao;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDto {

    private String nome;
    private BandeiraCartao bandeira;
    private BigDecimal renda;
    private BigDecimal limite;

    public Cartao toModel(){
        return new Cartao(nome, bandeira, renda, limite);
    }
}
