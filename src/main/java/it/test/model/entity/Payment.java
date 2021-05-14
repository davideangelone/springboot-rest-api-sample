package it.test.model.entity;

import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import it.test.utils.SHA;

@Entity
@Table(name = "payment")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private Date timestamp;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "importo", nullable = false)
	private int importo;
	
	private String hash;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
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
	
	@PrePersist
	@PreUpdate
	public void generateHash() {
		this.timestamp = new Date();
		this.hash = Base64.getEncoder().encodeToString(SHA.generateHash(timestamp.toString() + this.tipo + this.importo));
	}
	
}
