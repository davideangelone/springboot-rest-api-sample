package it.test.model.response;

import it.test.model.entity.Payment;

public class PaymentResponse {
	
	private int id;
	private long timestamp;
	private String tipo;
	private int importo;
	
	public PaymentResponse(Payment payment) {
		this.id = payment.getId();
		this.timestamp = payment.getTimestamp();
		this.tipo = payment.getTipo();
		this.importo = payment.getImporto();
	}
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

}
