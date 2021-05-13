package it.test.model.error;

public class Errore {
	
	public String campo;
	public String errore;
	
	public Errore(String campo, String errore) {
		this.campo = campo;
		this.errore = errore;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getErrore() {
		return errore;
	}

	public void setErrore(String errore) {
		this.errore = errore;
	}

}
