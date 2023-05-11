package com.br.dsevoluction.msclientes.services;

import com.br.dsevoluction.msclientes.domain.Cliente;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface ClienteServices {

    Cliente salvarCliente(Cliente obj);

    Optional<Cliente> getCliente(String cpf);
}
