package com.example.Service;

import com.example.Model.Role;
import com.example.Model.Utente;
import com.example.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utente utente = utenteRepository.findByNomeUtente(username);

        if (utente == null) {
            throw new UsernameNotFoundException("Utente non trovato: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : utente.getRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(utente.getNomeUtente(), utente.getPassword(), authorities);
    }

    @Override
    public Utente findByNomeUtente(String nomeUtente) {
        return utenteRepository.findByNomeUtente(nomeUtente);
    }

}



