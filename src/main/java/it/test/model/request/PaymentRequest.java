package it.test.model.request;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import it.test.model.validator.PaymentType;

public class PaymentRequest {
	
	@PaymentType()
	private String tipo;
	
	@NotNull(message = "Importo obbligatorio")
	@Digits(fraction = 0, integer = 20, message = "Importo deve essere un intero positivo")
	private String importo;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getImporto() {
		return importo;
	}
	public void setImporto(String importo) {
		this.importo = importo;
	}
	
}
