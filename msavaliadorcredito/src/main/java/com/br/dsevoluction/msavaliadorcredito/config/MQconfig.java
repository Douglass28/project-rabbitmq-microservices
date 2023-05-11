package com.br.dsevoluction.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQconfig {

    @Value("emissao-cartoes")
    private String emissaoCartoesFila;

    @Bean
    public Queue queueEmissoesCartoes(){
        return new Queue(emissaoCartoesFila, true);
    }
}