package it.solvingteam.paddle.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class PartitaUtente {
	
	public enum Tipo{CREATORE, PARTECIPANTE}
	
	@EmbeddedId
    private PartitaUtenteId id = new PartitaUtenteId();
	
	@ManyToOne
	@MapsId("idUtente")
	private Utente utente;
	
	@ManyToOne
	@MapsId("idPartita")
	private Partita partita;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;

	public PartitaUtenteId getId() {
		return id;
	}

	public void setId(PartitaUtenteId id) {
		this.id = id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Partita getPartita() {
		return partita;
	}

	public void setPartita(Partita partita) {
		this.partita = partita;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
}
