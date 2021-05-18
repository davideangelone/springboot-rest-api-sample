package it.test.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRequest {

	@NotBlank(message = "Username obbligatorio")
	private String username;
	
	@Email(message = "Email non valida")
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
