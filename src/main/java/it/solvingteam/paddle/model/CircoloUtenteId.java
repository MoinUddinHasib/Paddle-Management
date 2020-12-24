package it.solvingteam.paddle.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CircoloUtenteId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer idUtente;
	
	private Integer idCircolo;

	public CircoloUtenteId() {
		super();
	}

	public CircoloUtenteId(Integer idUtente, Integer idCircolo) {
		super();
		this.idUtente = idUtente;
		this.idCircolo = idCircolo;
	}

	public Integer getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Integer idUtente) {
		this.idUtente = idUtente;
	}

	public Integer getIdCircolo() {
		return idCircolo;
	}

	public void setIdCircolo(Integer idCircolo) {
		this.idCircolo = idCircolo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCircolo == null) ? 0 : idCircolo.hashCode());
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
		CircoloUtenteId other = (CircoloUtenteId) obj;
		if (idCircolo == null) {
			if (other.idCircolo != null)
				return false;
		} else if (!idCircolo.equals(other.idCircolo))
			return false;
		if (idUtente == null) {
			if (other.idUtente != null)
				return false;
		} else if (!idUtente.equals(other.idUtente))
			return false;
		return true;
	}
	
}
