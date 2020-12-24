package it.solvingteam.paddle.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class PartitaUtenteId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUtente;
	
	private Integer idPartita;

	public PartitaUtenteId() {
		super();
	}

	public PartitaUtenteId(Integer idUtente, Integer idPartita) {
		super();
		this.idUtente = idUtente;
		this.idPartita = idPartita;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Integer getIdPartita() {
		return idPartita;
	}

	public void setIdPartita(Integer idPartita) {
		this.idPartita = idPartita;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPartita == null) ? 0 : idPartita.hashCode());
		result = prime * result + ((idUtente == null) ? 0 : idUtente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartitaUtenteId other = (PartitaUtenteId) obj;
		if (idPartita == null) {
			if (other.idPartita != null)
				return false;
		} else if (!idPartita.equals(other.idPartita))
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (!idUtente.equals(other.idUtente))
			return false;
		return true;
	}

}
