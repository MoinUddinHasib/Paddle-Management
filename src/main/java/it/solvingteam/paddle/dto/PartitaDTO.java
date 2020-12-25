package it.solvingteam.paddle.dto;

public class PartitaDTO {
	
	private String id;
	
	private String data;
	
	private String statoPagamento;
	
	private String giocatoriMancanti;
	
	private String campoId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getStatoPagamento() {
		return statoPagamento;
	}

	public void setStatoPagamento(String statoPagamento) {
		this.statoPagamento = statoPagamento;
	}

	public String getGiocatoriMancanti() {
		return giocatoriMancanti;
	}

	public void setGiocatoriMancanti(String giocatoriMancanti) {
		this.giocatoriMancanti = giocatoriMancanti;
	}

	public String getCampoId() {
		return campoId;
	}

	public void setCampoId(String campoId) {
		this.campoId = campoId;
	}
	
}
