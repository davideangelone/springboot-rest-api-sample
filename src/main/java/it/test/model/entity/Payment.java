package it.test.model.entity;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import it.test.utils.SHA;

@Entity
@Table(name = "PAYMENT")
@NamedQuery(name = "Payment.findAll", query = "SELECT p FROM Payment p")
public class Payment implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int paymentId;
	
	private Date timestamp;
	
	@Column(name = "tipo", nullable = false)
	private String tipo;
	
	@Column(name = "importo", nullable = false)
	private int importo;
	
	private String hash;
	
	@ManyToOne
	@JoinColumn(name="USER_ID", nullable = false, updatable = false)
	private User user;
	
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@PrePersist
	@PreUpdate
	public void generateHash() {
		this.timestamp = new Date();
		this.hash = Base64.getEncoder().encodeToString(SHA.generateHash(timestamp.toString() + this.tipo + this.importo));
	}
}
