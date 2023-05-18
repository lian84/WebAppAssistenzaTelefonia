package com.example.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
//	@Autowired
//	ArticoliService articoliService;
	
//	@Autowired
//	ArticoliRepository articoliRepository;

	
//	@RequestMapping("/")
//	public String homePage(Model model) {
//
//		model.addAttribute("listaArticoli", articoliService.getAllArt());
//		return"dashboard";
//	}

//	@RequestMapping("/userForm")
//	public String userForm(Model model) {
//		Articoli p= new Articoli();
//		model.addAttribute("articolo", p);
//		return "form";
//	}

//	@GetMapping("/cancellaArticolo/{id}")
//	public String deleteArt(@PathVariable(value="id")String id, Model model) {
//
//		this.articoliService.deleteArtById(id);
//		return "redirect:/"; //viene reindirizzato automaticamente nella home page
//	}

//	@GetMapping("aggiornaArticolo/{id}")
//	public String updateArt(@PathVariable(value="id")String id, Model model) {
//		Articoli p= articoliService.getArtById(id);
//		model.addAttribute("articoli", p);
//		return "update";
//	}


//	@PostMapping("/salvaArticolo")
//	public String saveArtCOntroller(@ModelAttribute ("articolo")Articoli articoli) {
//		articoliService.saveArt(articoli);
//		return "redirect:/";
//	}
	
//	@GetMapping("/ricercaPerId")
//	public String ricercaArticolo(@RequestParam("id") String id, Model model) {
//		Articoli articolo = articoliRepository.findById(id).orElse(null);
//		model.addAttribute("articolo", articolo);
//		return "ricercaPerId.html";
//	}

//	 @GetMapping("/ricercaPerDescrizione")
//		public String ricercaArticoliPerDescrizione(@RequestParam("keywords") String keywords, Model model) {
//			List<Articoli> articoli = articoliRepository.findByDescrizioneContaining(keywords);
//			model.addAttribute("articoli", articoli);
//			return "ricercaPerDescrizione";
//		}

 //per gestire il login
 	@GetMapping("/login")
		public String mostraPaginaLogin() {
			return "login"; // Ritorna il nome della pagina del form di accesso
		}

	  //per gestire la pagina della dashboard
	  @GetMapping("/dashboard")
		public String mostraPaginaDasboard() {
			return "dashboard"; // Ritorna il nome della pagina della dashboard
		}

//	Questo Get Mapping si utilizza per istradare tutte le richieste sempre verso una pagina, ad esmepio per le SPA
//	che peró hanno anche un lato amministratore
//	@GetMapping("/**/{path:[^\\.]*}")
//	fun forward(request: HttpServletRequest): String? {
//		if(request.requestURI.startsWith("/admin")) {
//			return "forward:/admin/index.html"
//		}
//		return "forward:/index.html"
//	}

//	Questo tipo di RequestMapping si utilizza invece quando si vuole dirottare tutto verso una sola pagina
//	@RequestMapping(value = "/{path:[^\\.]*}")
//	public String redirect() {
//		return "forward:/";
//	}

//	@InitBinder
//	public void initBinder(WebDataBinder binder){
//	binder.registerCustomEditor( Date.class,
//	new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
//	}

}
