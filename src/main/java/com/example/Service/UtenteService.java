package com.example.Service;

import com.example.Model.Utente;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UtenteService extends UserDetailsService {

    Utente findByNomeUtente(String nomeUtente);
}
