package com.example.Repository;

import com.example.Model.Assistenza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssistenzaRepository extends JpaRepository<Assistenza, Long> {
}