package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Model.Articoli;

import java.util.Optional;

@Repository
public interface ArticoliRepository extends JpaRepository<Articoli, Long> {

}
