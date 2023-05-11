package com.br.dsevoluction.mscartoes.resources;

import com.br.dsevoluction.mscartoes.dtos.CartaoDto;
import com.br.dsevoluction.mscartoes.entities.Cartao;
import com.br.dsevoluction.mscartoes.entities.ClienteCartao;
import com.br.dsevoluction.mscartoes.representation.CartoesPorClienteResponse;
import com.br.dsevoluction.mscartoes.services.CartaoService;
import com.br.dsevoluction.mscartoes.services.ClienteCartaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

    @Autowired
    private CartaoService service;

    @Autowired
    private ClienteCartaoService clienteCartaoService;
    @GetMapping
    public ResponseEntity getAllcartoes(){
        return new ResponseEntity<>(service.getAllCartao(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveCartao(@RequestBody CartaoDto cartaoDto){
        Cartao cartao = cartaoDto.toModel();
        service.saveCartao(cartao);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(params = "renda")
    public ResponseEntity getCartao(@RequestParam("renda") Long renda){
        List<Cartao> cartaoList = service.getCartaoListRendaMenorIgual(renda);
        return ResponseEntity.ok(cartaoList);
    }

    @GetMapping(params = "cpf")
    public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf){
        List<ClienteCartao> cartaoList = clienteCartaoService.listCartoesByCpf(cpf);
        List<CartoesPorClienteResponse> resultList = cartaoList.stream().map(CartoesPorClienteResponse::forModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultList);
    }
}
