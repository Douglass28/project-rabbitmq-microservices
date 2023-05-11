package com.br.dsevoluction.mscartoes.infra.mqueue;

import com.br.dsevoluction.mscartoes.entities.Cartao;
import com.br.dsevoluction.mscartoes.entities.ClienteCartao;
import com.br.dsevoluction.mscartoes.entities.DadosSolicitacaoEmissaCartao;
import com.br.dsevoluction.mscartoes.repositories.CartaoRepository;
import com.br.dsevoluction.mscartoes.repositories.ClienteCartaoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissaoCartaoSubscriber {
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private ClienteCartaoRepository clienteCartaoRepository;

    @RabbitListener(queues = "emissao-cartoes")
    public void receberSolicitacaoEmissaoCartao(@Payload String payload) {
        try {
            var mapper = new ObjectMapper();
            DadosSolicitacaoEmissaCartao dados = mapper.readValue(payload, DadosSolicitacaoEmissaCartao.class);
            Cartao cartao = cartaoRepository.findById(dados.getIdCartao()).orElseThrow();
            ClienteCartao clienteCartao = new ClienteCartao();
            clienteCartao.setCartao(cartao);
            clienteCartao.setCpf(dados.getCpf());
            clienteCartao.setLimite(dados.getLimiteLiberado());

            clienteCartaoRepository.save(clienteCartao);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
