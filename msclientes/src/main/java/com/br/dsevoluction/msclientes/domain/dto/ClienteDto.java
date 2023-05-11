package com.br.dsevoluction.msclientes.domain.dto;

import com.br.dsevoluction.msclientes.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ClienteDto {

    private String cpf;
    private String nome;
    private Integer idade;

    public Cliente model(){
        return new Cliente(cpf, nome, idade);
    }
}
