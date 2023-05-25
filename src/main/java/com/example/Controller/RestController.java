package com.example.Controller;

import com.example.Model.Assistenza;
import com.example.Model.Cliente;
import com.example.Model.Articoli;
import com.example.Service.ArticoliService;
import com.example.Service.AssistenzaService;
import com.example.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ArticoliService articoliService;

    @Autowired
    private AssistenzaService assistenzaService;



    @GetMapping("/clienti")
    public List<Cliente> getListaClienti() {
        return clienteService.getListaClienti();
    }

    @GetMapping("/articoli")
    public List<Articoli> getListaArticoli() {
        return articoliService.getListaArticoli();
    }

    @GetMapping("/assistenza")
    public List<Assistenza> getListaAssistenza() {
        return assistenzaService.getListaAssistenza();
    }

    @PutMapping("/upassistenza/{id}")
    public Assistenza aggiornaAssistenza(@PathVariable("id") Long id, @RequestBody Assistenza assistenza) {
        assistenza.setId(id);
        return assistenzaService.aggiornaAssistenza(assistenza);
    }

    @DeleteMapping("/assistenza/{id}")
    public ResponseEntity<String> eliminaAssistenza(@PathVariable("id") Long id) {
        // Effettua l'operazione di eliminazione dell'assistenza utilizzando il service
        boolean eliminato = assistenzaService.eliminaAssistenza(id);

        if (eliminato) {
            return ResponseEntity.ok("Assistenza eliminata con successo");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Assistenza non trovata");
        }
    }

    @PostMapping("/assistenza")
    public ResponseEntity<Assistenza> creaAssistenza(@RequestBody Assistenza nuovaAssistenza) {
        try {
            Assistenza assistenzaCreata = assistenzaService.creaAssistenza(nuovaAssistenza);
            return ResponseEntity.status(HttpStatus.CREATED).body(assistenzaCreata);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Se il cliente viene trovato, viene restituito un oggetto ResponseEntity con lo stato HTTP 200 (OK) e il corpo della risposta contenente l'oggetto Cliente.
    // Se il cliente non viene trovato, viene restituito un oggetto ResponseEntity con lo stato HTTP 404 (Not Found) senza corpo della risposta.
    @GetMapping("/clienti/{id}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //il controller recupera il cliente esistente dal database utilizzando l'ID fornito e aggiorna i dati con quelli forniti nel corpo della richiesta.
    // Infine, viene restituita una risposta HTTP corrispondente al risultato dell'aggiornamento.
    @PutMapping("/modclienti/{id}")
    public ResponseEntity<String> aggiornaCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteModificato) {
        Cliente clienteEsistente = clienteService.getClienteById(id);
        if (clienteEsistente != null) {
            clienteEsistente.setNome(clienteModificato.getNome());
            clienteEsistente.setCognome(clienteModificato.getCognome());
            clienteEsistente.setIndirizzo(clienteModificato.getIndirizzo());
            clienteEsistente.setCap(clienteModificato.getCap());
            clienteEsistente.setComune(clienteModificato.getComune());
            clienteEsistente.setProvincia(clienteModificato.getProvincia());
            clienteEsistente.setTel(clienteModificato.getTel());
            clienteEsistente.setMail(clienteModificato.getMail());

            clienteService.aggiornaCliente(clienteEsistente);

            return ResponseEntity.ok("Cliente aggiornato con successo");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente non trovato");
        }
    }


    @GetMapping("/api/clienti/{clienteId}/assistenza")
    public ResponseEntity<List<Articoli>> getArticoliAssistenzaCliente(@PathVariable("clienteId") Long clienteId) {
        // Logica per recuperare gli articoli in assistenza del cliente dal database
        List<Articoli> articoliAssistenza = articoliService.getArticoliAssistenzaByClienteId(clienteId);

        // Restituisci gli articoli in assistenza come corpo della risposta
        return ResponseEntity.ok(articoliAssistenza);
    }


}

