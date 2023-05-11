package com.br.dsevoluction.mscartoes.services;

import com.br.dsevoluction.mscartoes.entities.Cartao;
import com.br.dsevoluction.mscartoes.repositories.CartaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartaoService {

    @Autowired
    private CartaoRepository repository;

    @Transactional
    public Cartao saveCartao(Cartao cartao) {
        return repository.save(cartao);
    }

    public List<Cartao> getCartaoListRendaMenorIgual(Long renda) {
        var rendaBigDecimal = BigDecimal.valueOf(renda);
        return repository.findByRendaLessThanEqual(rendaBigDecimal);
    }

    public List<Cartao> getAllCartao(){
        return repository.findAll();
    }

}
