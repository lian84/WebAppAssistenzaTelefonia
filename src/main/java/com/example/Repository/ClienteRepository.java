package com.example.Repository;

import com.example.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Metodi personalizzati per operazioni specifiche sul repository, se necessario
}