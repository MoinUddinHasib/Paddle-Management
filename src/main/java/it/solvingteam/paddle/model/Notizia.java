package it.solvingteam.paddle.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notizia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String messagio;
	
	private LocalDate data;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circolo_fk")
	private Circolo circolo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessagio() {
		return messagio;
	}

	public void setMessagio(String messagio) {
		this.messagio = messagio;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Circolo getCircolo() {
		return circolo;
	}

	public void setCircolo(Circolo circolo) {
		this.circolo = circolo;
	}

}
