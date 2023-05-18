package com.example.Model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "utenti")
public class Utente {

  	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_utente", unique = true)
    private String nomeUtente;

    @Column(name = "password")
    private String password;
    
    //questa é la tabella intermedia che collega gli utenti ai ruoli
    //dove sono presenti le chiavi degli utenti e le chiavi dei ruoli
    //questo permette di fare un rapporto molti a molti
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> role;

	public Utente() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	

}