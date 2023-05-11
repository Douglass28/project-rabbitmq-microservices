package com.br.dsevoluction.msavaliadorcredito.resources;

import com.br.dsevoluction.msavaliadorcredito.domain.DadosSolicitacaoEmissaCartao;
import com.br.dsevoluction.msavaliadorcredito.domain.ProtocoloSolicitacaoCartao;
import com.br.dsevoluction.msavaliadorcredito.exception.ErrorSolicitacaoCartaoException;
import com.br.dsevoluction.msavaliadorcredito.services.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/solitarCartao")
public class SolicitarCartaoCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;

    @PostMapping
    private ResponseEntity solicitarCartao(@RequestBody DadosSolicitacaoEmissaCartao dados){
        try {
            ProtocoloSolicitacaoCartao protocolo = avaliadorCreditoService.solicitarEmissaoCartao(dados);
            return ResponseEntity.ok(protocolo);
        }catch (ErrorSolicitacaoCartaoException e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
