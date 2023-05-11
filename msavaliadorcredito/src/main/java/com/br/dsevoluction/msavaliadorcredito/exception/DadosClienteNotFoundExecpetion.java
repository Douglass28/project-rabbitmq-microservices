package com.br.dsevoluction.msavaliadorcredito.exception;

public class DadosClienteNotFoundExecpetion extends Exception{

    public DadosClienteNotFoundExecpetion() {
        super("Dados do cliente não encontrado para o cpf informado.");
    }
}
