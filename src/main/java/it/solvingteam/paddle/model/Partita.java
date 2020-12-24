package it.solvingteam.paddle.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Partita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDate data;
	
	private Boolean statoPagamento;
	
	private Integer giocatoriMancanti;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campo_fk")
	private Campo campo;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "partita_slot",
	joinColumns = @JoinColumn(name = "partita_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "slot_id", referencedColumnName = "id"))
	private List<Slot> slots = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partita")
	private List<PartitaUtente> utenti = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Boolean getStatoPagamento() {
		return statoPagamento;
	}

	public void setStatoPagamento(Boolean statoPagamento) {
		this.statoPagamento = statoPagamento;
	}

	public Integer getGiocatoriMancanti() {
		return giocatoriMancanti;
	}

	public void setGiocatoriMancanti(Integer giocatoriMancanti) {
		this.giocatoriMancanti = giocatoriMancanti;
	}

	public Campo getCampo() {
		return campo;
	}

	public void setCampo(Campo campo) {
		this.campo = campo;
	}

	public List<Slot> getSlots() {
		return slots;
	}

	public void setSlots(List<Slot> slots) {
		this.slots = slots;
	}

	public List<PartitaUtente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<PartitaUtente> utenti) {
		this.utenti = utenti;
	}

}
