package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Model.Role;
import com.example.Model.Utente;
import com.example.Repository.UtenteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UtenteRepository userRepository; // Dipendenza per l'accesso al database degli utenti

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = userRepository.findByNomeUtente(username); // Carica l'utente dal database in base al nome utente

        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        }

        // Restituisci un oggetto UserDetails basato sulle informazioni dell'utente caricato dal database
        return org.springframework.security.core.userdetails.User.builder()
            .username(utente.getNomeUtente())
            .password(utente.getPassword())
            .roles(utente.getRole().stream().map(Role::getName).toArray(String[]::new))
            .build();
    }
}




