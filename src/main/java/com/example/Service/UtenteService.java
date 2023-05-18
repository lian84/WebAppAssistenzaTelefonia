package com.example.Service;

import org.springframework.stereotype.Service;

import com.example.Model.Utente;
import com.example.Repository.UtenteRepository;

@Service
public class UtenteService {

    private UtenteRepository utenteRepository;

    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public Utente autenticazione(String nomeUtente, String password) {
        Utente utente = utenteRepository.findByNomeUtente(nomeUtente);
        if (utente != null && utente.getPassword().equals(password)) {
            return utente;
        }
        return null;
    }
}
