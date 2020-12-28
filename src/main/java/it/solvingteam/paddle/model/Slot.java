package it.solvingteam.paddle.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Slot implements Comparable<Slot>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Integer ora;
	
	private Integer minuti;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "slots")
	private List<Partita> partite = new ArrayList<>();

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOra() {
		return ora;
	}


	public void setOra(Integer ora) {
		this.ora = ora;
	}


	public Integer getMinuti() {
		return minuti;
	}


	public void setMinuti(Integer minuti) {
		this.minuti = minuti;
	}


	public List<Partita> getPartite() {
		return partite;
	}


	public void setPartite(List<Partita> partite) {
		this.partite = partite;
	}

	@Override
	public int compareTo(Slot o) {
		return id.compareTo(o.getId());
	}

}
