package it.test.model.entity;

import it.test.utils.SHA;

public class Payment {
	
	private int id;
	
	private long timestamp;
	
	private String tipo;
	
	private int importo;
	
	private String hash;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getImporto() {
		return importo;
	}
	public void setImporto(int importo) {
		this.importo = importo;
	}
	public String getHash() {
		return hash;
	}
	public void generateHash() {
		this.hash = SHA.generateHash(this.id + this.timestamp + this.tipo + this.importo);
	}
	
}
