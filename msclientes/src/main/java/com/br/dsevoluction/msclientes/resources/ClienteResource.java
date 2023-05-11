package com.br.dsevoluction.msclientes.resources;

import com.br.dsevoluction.msclientes.domain.Cliente;
import com.br.dsevoluction.msclientes.domain.dto.ClienteDto;
import com.br.dsevoluction.msclientes.services.ClienteServices;
import com.ctc.wstx.shaded.msv_core.util.Uri;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteResource {

    @Autowired
    private ClienteServices services;

    @GetMapping
    public String status(){
        log.info("Obtendo o status do microservice de clientes");
        return "ok";
    }

    @PostMapping
    public ResponseEntity salvarCliente(@RequestBody ClienteDto obj){
        Cliente cliente=  obj.model();
        services.salvarCliente(cliente);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(cliente.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity getCliente(@RequestParam("cpf") String cpf){
         Optional<Cliente> cliente = services.getCliente(cpf);
         if (cliente.isEmpty()){
             return ResponseEntity.notFound().build();
         }
         return ResponseEntity.ok(cliente);
    }
}
