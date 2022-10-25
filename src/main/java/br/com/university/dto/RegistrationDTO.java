package br.com.university.dto;

import org.springframework.data.domain.Page;

import br.com.university.db.Registration;

public class RegistrationDTO {

	private Long id;
	private String title;
	private String message;

	public RegistrationDTO(Registration registration) {
		this.id = registration.getId();
		this.title = registration.getTitle();
		this.message = registration.getMessage();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public static Page<RegistrationDTO> convert(Page<Registration> registration) {
		return registration.map(RegistrationDTO::new);
	}

}
