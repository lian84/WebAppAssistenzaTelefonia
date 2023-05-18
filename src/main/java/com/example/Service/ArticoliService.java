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



    // Altri metodi per gestire operazioni di business sui clienti, ad esempio creazione, aggiornamento, cancellazione, ecc.
}