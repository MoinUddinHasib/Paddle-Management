package it.solvingteam.paddle.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Utente {
	
	public enum Ruolo{SUPER_ADMIN_ROLE, ADMIN_ROLE, PLAYER_ROLE, GUEST_ROLE}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String cognome;
	
	private LocalDate dataNascita;
	
	private String email;
	
	private String cellulare;
	
	@Enumerated(EnumType.STRING)
	private Ruolo ruolo;
	
	private Byte[] foto;
	
	private Integer livello;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utente")
	private List<CircoloUtente> circoli = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utente")
	private List<PartitaUtente> partite = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public LocalDate getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(LocalDate dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCellulare() {
		return cellulare;
	}

	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

	public Integer getLivello() {
		return livello;
	}

	public void setLivello(Integer livello) {
		this.livello = livello;
	}

	public List<CircoloUtente> getCircoli() {
		return circoli;
	}

	public void setCircoli(List<CircoloUtente> circoli) {
		this.circoli = circoli;
	}

	public List<PartitaUtente> getPartite() {
		return partite;
	}

	public void setPartite(List<PartitaUtente> partite) {
		this.partite = partite;
	}

}
