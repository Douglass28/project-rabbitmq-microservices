package com.br.dsevoluction.msavaliadorcredito.infra.client;

import com.br.dsevoluction.msavaliadorcredito.domain.Cartao;
import com.br.dsevoluction.msavaliadorcredito.domain.CartoesCliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value="mscartoes", path="/cartoes")
public interface CartaoResourceClient {

    @GetMapping(params="cpf")
    ResponseEntity<List<CartoesCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

    @GetMapping(params = "renda")
    public ResponseEntity<List<Cartao>> getCartao(@RequestParam("renda") Long renda);

}
