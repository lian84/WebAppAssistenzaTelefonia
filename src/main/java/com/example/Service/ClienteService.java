package com.example.Service;

import com.example.Model.Cliente;
import com.example.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getListaClienti() {
        return clienteRepository.findAll();
    }

    // Altri metodi per gestire operazioni di business sui clienti, ad esempio creazione, aggiornamento, cancellazione, ecc.
}