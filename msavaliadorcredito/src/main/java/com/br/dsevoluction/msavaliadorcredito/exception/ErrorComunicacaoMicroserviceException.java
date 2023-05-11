package com.br.dsevoluction.msavaliadorcredito.exception;

import lombok.Getter;

public class ErrorComunicacaoMicroserviceException extends Exception{

    @Getter
    private Integer status;
    public ErrorComunicacaoMicroserviceException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
