package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.Model.Cliente;
import com.example.Model.Utente;
import com.example.Service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Model.Articoli;
import com.example.Repository.ArticoliRepository;
import com.example.Service.ArticoliService;

@Controller
public class AppController {


	@Autowired
	private UtenteService utenteService;

 	//per gestire il login
 	@GetMapping("login")
		public String mostraPaginaLogin() {
			return "login"; // Ritorna il nome della pagina del form di accesso
		}

    //per gestire la pagina della dashboard
    @GetMapping("/admin/dashboard")
		public String mostraPaginaDasboard() {
		return "admin/dashboard"; // Ritorna il nome della pagina della dashboard
	}

	//percorso riservato agli utenti
	@GetMapping("/user/utente")
		public String mostraPaginaUtenti() { return "user/utente"; } //questo end-point dirotta verso il file /user/utente.html


}
