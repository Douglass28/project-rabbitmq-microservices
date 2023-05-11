package com.br.dsevoluction.msavaliadorcredito.infra.client;

import com.br.dsevoluction.msavaliadorcredito.domain.DadosSolicitacaoEmissaCartao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

    /*
    RabbitTemplate é uma classe da biblioteca AMQP que facilita o envio e recebimento de mensagens
    de código aberto muito utilizado em arquiteturas de microservices.
     */
    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissaCartao;

    public void solicitarCartao(DadosSolicitacaoEmissaCartao dados) throws JsonProcessingException{
        var json = convertIntoJson(dados);
        rabbitTemplate.convertAndSend(queueEmissaCartao.getName(), json);

    }
    private String convertIntoJson(DadosSolicitacaoEmissaCartao dados) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        var json = objectMapper.writeValueAsString(dados);
        return json;
    }
}
