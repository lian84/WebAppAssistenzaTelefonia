package com.example.Model;

import javax.persistence.*;

@Entity
@Table(name="tipologie_prodotti")
public class Tipologie_Prodotti {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="tipologia_prod")
	private String nome_tipologia;

	@OneToOne(mappedBy = "tipologia", cascade = CascadeType.ALL)
	private Articoli articolo;

	public Tipologie_Prodotti() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome_tipologia() {
		return nome_tipologia;
	}

	public void setNome_tipologia(String nome_tipologia) {
		this.nome_tipologia = nome_tipologia;
	}
}