package br.com.university.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.university.db.Registration;
import br.com.university.repository.RegistrationRepository;

public class UpdateTopicForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String title;

	@NotNull
	@NotEmpty
	@Length(min = 10)
	private String message;

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Registration update(Long id, RegistrationRepository repository) {
		Registration registration = repository.getOne(id);

		registration.setTitle(this.title);
		registration.setMessage(this.message);

		return registration;
	}

}
