package com.br.dsevoluction.msclientes.servicesImpl;

import com.br.dsevoluction.msclientes.domain.Cliente;
import com.br.dsevoluction.msclientes.repository.ClienteRepository;
import com.br.dsevoluction.msclientes.services.ClienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteServices {

    @Autowired
    private ClienteRepository repository;

    @Override
    public Cliente salvarCliente(Cliente obj) {
        return repository.save(obj);
    }

    @Override
    public Optional<Cliente> getCliente(String cpf) {
         return repository.findByCpf(cpf);
    }
}
