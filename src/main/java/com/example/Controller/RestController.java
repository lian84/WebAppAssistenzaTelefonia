package com.example.Controller;

import com.example.Model.Assistenza;
import com.example.Model.Cliente;
import com.example.Model.Articoli;
import com.example.Service.ArticoliService;
import com.example.Service.AssistenzaService;
import com.example.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


}

