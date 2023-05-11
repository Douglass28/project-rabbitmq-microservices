package com.br.dsevoluction.mscartoes.repositories;

import com.br.dsevoluction.mscartoes.entities.ClienteCartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteCartaoRepository extends JpaRepository<ClienteCartao, Long> {
    List<ClienteCartao> findByCpf(String cpf);
}
