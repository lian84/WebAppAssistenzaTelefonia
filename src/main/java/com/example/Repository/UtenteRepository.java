package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    Utente findByNomeUtente(String username);
}