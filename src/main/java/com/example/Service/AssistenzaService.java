package com.example.Service;

import com.example.Model.Assistenza;
import com.example.Repository.AssistenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssistenzaService {
    @Autowired
    private AssistenzaRepository assistenzaRepository;

    public List<Assistenza> getListaAssistenza()
    {
        return assistenzaRepository.findAll();
    }

    public Assistenza aggiornaAssistenza(Assistenza assistenza) {
        return assistenzaRepository.save(assistenza);
    }

    // Altri metodi per gestire operazioni di business sui clienti, ad esempio creazione, aggiornamento, cancellazione, ecc.
}