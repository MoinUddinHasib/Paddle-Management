package it.solvingteam.paddle.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Circolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String city;
	
	private Byte[] logo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "circolo")
	private List<CircoloUtente> utenti = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "circolo")
	private List<Campo> campi = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "circolo")
	private List<Notizia> notizie = new ArrayList<>();

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Byte[] getLogo() {
		return logo;
	}

	public void setLogo(Byte[] logo) {
		this.logo = logo;
	}

	public List<CircoloUtente> getUtenti() {
		return utenti;
	}

	public void setUtenti(List<CircoloUtente> utenti) {
		this.utenti = utenti;
	}

	public List<Campo> getCampi() {
		return campi;
	}

	public void setCampi(List<Campo> campi) {
		this.campi = campi;
	}

	public List<Notizia> getNotizie() {
		return notizie;
	}

	public void setNotizie(List<Notizia> notizie) {
		this.notizie = notizie;
	}

}
