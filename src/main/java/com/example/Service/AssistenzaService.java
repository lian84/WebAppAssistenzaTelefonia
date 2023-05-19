package com.example.Service;

import com.example.Model.Assistenza;
import com.example.Repository.AssistenzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    public boolean eliminaAssistenza(Long id) {
        try {
            assistenzaRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (DataIntegrityViolationException e) {
            // Gestisci l'eccezione di violazione del vincolo di integrità
            // Ad esempio, puoi loggare l'errore o fornire un messaggio di errore specifico
            System.out.println("Impossibile eliminare l'assistenza. È associata ad altri record.");

            // Restituisci l'errore 409 (Conflict)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossibile eliminare l'assistenza. È associata ad altri record.", e);
        }
    }

    @Transactional
    public Assistenza creaAssistenza(Assistenza nuovaAssistenza) {
        // Esegui le validazioni necessarie sui dati dell'assistenza

        // Gestisci la logica di creazione dell'assistenza
        Assistenza assistenzaCreata = assistenzaRepository.save(nuovaAssistenza);

        // Puoi eseguire altre operazioni o business logic qui

        return assistenzaCreata;
    }
}