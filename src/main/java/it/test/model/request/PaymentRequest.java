package it.test.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class PaymentRequest {
	
	@NotBlank(message = "Tipo obbligatorio")
	@Pattern(regexp = "^(cash)|(bonifico)|(carta)$", message="Tipo deve essere uno tra: cash, bonifico, carta")
	private String tipo;
	
	@NotNull(message = "Importo obbligatorio")
	@Positive(message = "Importo deve essere positivo")
	private int importo;
	
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
