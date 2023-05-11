package com.br.dsevoluction.mscartoes.services;

import com.br.dsevoluction.mscartoes.entities.ClienteCartao;
import com.br.dsevoluction.mscartoes.repositories.CartaoRepository;
import com.br.dsevoluction.mscartoes.repositories.ClienteCartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteCartaoService {
    @Autowired
    private ClienteCartaoRepository repository;

    public List<ClienteCartao> listCartoesByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
}
