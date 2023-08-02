package com.example.Service;

import com.example.Model.Cliente;
import com.example.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getListaClienti() {
        return clienteRepository.findAll();
    }

    //Il metodo getClienteById utilizza il repository per cercare il cliente corrispondente all'ID fornito.
    // Se viene trovato un cliente, viene restituito l'oggetto Cliente, altrimenti viene restituito null.
    public Cliente getClienteById(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        return optionalCliente.orElse(null);
    }


    //il metodo accetta un oggetto Cliente e verifica se il cliente esiste nel database utilizzando il suo ID.
    // Se il cliente esiste, i dati del cliente esistente vengono aggiornati con quelli forniti
    // e il cliente aggiornato viene salvato nel database utilizzando il metodo save del repository.
    // se il cliente non esiste da un messaggio di errore
    public Cliente aggiornaCliente(Cliente cliente) {
        Optional<Cliente> clienteEsistente = clienteRepository.findById(cliente.getId());
        if (clienteEsistente.isPresent()) {
            Cliente clienteAggiornato = clienteEsistente.get();
            clienteAggiornato.setNome(cliente.getNome());
            clienteAggiornato.setCognome(cliente.getCognome());
            clienteAggiornato.setIndirizzo(cliente.getIndirizzo());
            clienteAggiornato.setCap(cliente.getCap());
            clienteAggiornato.setComune(cliente.getComune());
            clienteAggiornato.setProvincia(cliente.getProvincia());
            clienteAggiornato.setTel(cliente.getTel());
            clienteAggiornato.setMail(cliente.getMail());

            return clienteRepository.save(clienteAggiornato);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Impossibile eliminare aggiornare il cliente");
        }
    }

}