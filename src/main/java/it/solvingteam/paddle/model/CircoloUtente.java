package it.solvingteam.paddle.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CircoloUtente {
	
	public enum Stato{APPROVATO, NON_APPROVATO, IN_LAVORAZIONE}
	public enum Tipo{CREAZIONE, ISCRIZIONE}
	
	@EmbeddedId
    private CircoloUtenteId id = new CircoloUtenteId();
	
	@ManyToOne
	@MapsId("idUtente")
	private Utente utente;
	
	@ManyToOne
	@MapsId("idCircolo")
	private Circolo circolo;
	
	@Enumerated(EnumType.STRING)
	private Stato stato;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public CircoloUtenteId getId() {
		return id;
	}

	public void setId(CircoloUtenteId id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Circolo getCircolo() {
		return circolo;
	}

	public void setCircolo(Circolo circolo) {
		this.circolo = circolo;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

}
