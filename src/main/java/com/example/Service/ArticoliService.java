package com.example.Service;

import com.example.Model.Articoli;
import com.example.Repository.ArticoliRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticoliService {
    @Autowired
    private ArticoliRepository articoliRepository;

    public List<Articoli> getListaArticoli() {
        return articoliRepository.findAll();
    }

    public List<Articoli> getArticoliAssistenzaByClienteId(Long clienteId) {
        // Effettua una query al database per recuperare gli articoli in assistenza del cliente
        List<Articoli> articoliAssistenza = articoliRepository.findByClienteId(clienteId);

        // Restituisci la lista di articoli in assistenza
        return articoliAssistenza;
    }
}