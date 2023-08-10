package com.example.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "articoli")
public class Articoli {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@OneToOne
	@JoinColumn(name = "id_tipologia")
	private Tipologie_Prodotti tipologia;
	@Column(name="marca")
	private String marca;
	@Column(name="modello")
	private String modello;
	@Column(name="tipo_guasto")
	private String tipo_guasto;
	@OneToOne
	@JoinColumn(name = "id_assistenza")
	private Assistenza assistenza;


	//un cliente puó avere piú articoli
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_cliente", nullable = false)
	@JsonBackReference
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Cliente cliente;


	//un articolo puó avere un solo stato assistenza
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_stato_assistenza", nullable = false)
	private StatoAssistenza stato;



	//Costruttore
	public Articoli() {
		super();
	}


	//classi getter e setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tipologie_Prodotti getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologie_Prodotti tipologia) {
		this.tipologia = tipologia;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getTipo_guasto() {
		return tipo_guasto;
	}

	public void setTipo_guasto(String tipo_guasto) {
		this.tipo_guasto = tipo_guasto;
	}

	public Assistenza getAssistenza() {
		return assistenza;
	}

	public void setAssistenza(Assistenza assistenza) {
		this.assistenza = assistenza;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public StatoAssistenza getStato() {
		return stato;
	}

	public void setStato(StatoAssistenza stato) {
		this.stato = stato;
	}
}