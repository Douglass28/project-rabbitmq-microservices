package com.br.dsevoluction.msavaliadorcredito.services;

import com.br.dsevoluction.msavaliadorcredito.domain.*;
import com.br.dsevoluction.msavaliadorcredito.exception.DadosClienteNotFoundExecpetion;
import com.br.dsevoluction.msavaliadorcredito.exception.ErrorComunicacaoMicroserviceException;
import com.br.dsevoluction.msavaliadorcredito.exception.ErrorSolicitacaoCartaoException;
import com.br.dsevoluction.msavaliadorcredito.infra.client.CartaoResourceClient;
import com.br.dsevoluction.msavaliadorcredito.infra.client.ClienteResourceClient;
import com.br.dsevoluction.msavaliadorcredito.infra.client.SolicitacaoEmissaoCartaoPublisher;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AvaliadorCreditoService {

    // obter dados clientes -MSCLIENTES
    // obter dados cartao -MSCARTOES
    @Autowired
    private ClienteResourceClient clienteResource;

    @Autowired
    private CartaoResourceClient cartaoResource;

    @Autowired
    private SolicitacaoEmissaoCartaoPublisher solicitacaoEmissaoCartaoPublisher;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundExecpetion, ErrorComunicacaoMicroserviceException {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResource.getCliente(cpf);
            ResponseEntity<List<CartoesCliente>> cartoesResponse = cartaoResource.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .client(dadosClienteResponse.getBody())
                    .cartoes(cartoesResponse.getBody())
                    .build();

        } catch (FeignException.FeignClientException e) {
            Integer status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundExecpetion();
            }
            throw new ErrorComunicacaoMicroserviceException(e.getMessage(), status);
        }
    }

    public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, Long renda)
            throws DadosClienteNotFoundExecpetion, ErrorComunicacaoMicroserviceException {

        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResource.getCliente(cpf);
            ResponseEntity<List<Cartao>> cartoesResponse = cartaoResource.getCartao(renda);

            List<Cartao> cartoes = cartoesResponse.getBody();
            var ListaCartoesAprovados = cartoes.stream().map(cartao -> {

                DadosCliente dadosCliente = dadosClienteResponse.getBody();

                BigDecimal limiteBasico = cartao.getLimiteBasico();
                BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

                BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
                BigDecimal limiteAprovado = fator.multiply(limiteBasico);


                CartaoAprovado aprovado = new CartaoAprovado();
                aprovado.setNome(cartao.getNome());
                aprovado.setBandeira(cartao.getBandeira());
                aprovado.setLimiteAprovado(limiteAprovado);

                return aprovado;

            }).collect(Collectors.toList());

            return new RetornoAvaliacaoCliente(ListaCartoesAprovados);

        } catch (FeignException.FeignClientException e) {
            Integer status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundExecpetion();
            }
            throw new ErrorComunicacaoMicroserviceException(e.getMessage(), status);

        }
    }
    public ProtocoloSolicitacaoCartao solicitarEmissaoCartao(DadosSolicitacaoEmissaCartao dados) {
        try {
            solicitacaoEmissaoCartaoPublisher.solicitarCartao(dados);
            var protocol = UUID.randomUUID().toString();
            return new ProtocoloSolicitacaoCartao(protocol);
        } catch (Exception e) {
            throw  new ErrorSolicitacaoCartaoException(e.getMessage());
        }
    }

}
