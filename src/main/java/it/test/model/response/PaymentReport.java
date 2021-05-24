package it.test.model.response;

public class PaymentReport implements IPaymentReport {
	
	private String tipo;
	private int importoTotale;
	private int numeroPagamenti;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getImportoTotale() {
		return importoTotale;
	}
	public void setImportoTotale(int importoTotale) {
		this.importoTotale = importoTotale;
	}
	public int getNumeroPagamenti() {
		return numeroPagamenti;
	}
	public void setNumeroPagamenti(int numeroPagamenti) {
		this.numeroPagamenti = numeroPagamenti;
	}

}
