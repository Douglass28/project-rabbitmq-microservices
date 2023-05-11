package com.br.dsevoluction.mscartoes.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;



@NoArgsConstructor
@Data
@Entity
public class ClienteCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    @ManyToOne
    @JoinColumn(name = "id_cartao")
    private Cartao cartao;
    private BigDecimal limite;

    public ClienteCartao(String cpf, Cartao cartao, BigDecimal limite) {
        this.cpf = cpf;
        this.cartao = cartao;
        this.limite = limite;
    }
}