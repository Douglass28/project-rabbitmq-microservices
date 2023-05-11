package com.br.dsevoluction.msavaliadorcredito.resources;

import com.br.dsevoluction.msavaliadorcredito.domain.DadosAvaliacao;
import com.br.dsevoluction.msavaliadorcredito.domain.RetornoAvaliacaoCliente;
import com.br.dsevoluction.msavaliadorcredito.domain.SituacaoCliente;
import com.br.dsevoluction.msavaliadorcredito.exception.DadosClienteNotFoundExecpetion;
import com.br.dsevoluction.msavaliadorcredito.exception.ErrorComunicacaoMicroserviceException;
import com.br.dsevoluction.msavaliadorcredito.services.AvaliadorCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avaliador-credito")
public class AvaliadorCreditoController {

    @Autowired
    private AvaliadorCreditoService avaliadorCreditoService;
    @GetMapping
    public String get(){
        return "ok";
    }

    @GetMapping (value = "situacao-client", params = "cpf")
        public ResponseEntity consultaSituacaoCliente (@RequestParam("cpf") String cpf){

        try {
            SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
            return ResponseEntity.ok(situacaoCliente);
        } catch (DadosClienteNotFoundExecpetion e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados){

        try {
            RetornoAvaliacaoCliente retornoAvaliacaoCliente =
                    avaliadorCreditoService.realizarAvaliacao(dados.getCpf(), dados.getRenda());
            return ResponseEntity.ok(retornoAvaliacaoCliente);
        } catch (DadosClienteNotFoundExecpetion e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorComunicacaoMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
