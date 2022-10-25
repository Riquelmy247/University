package br.com.university.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.university.db.Registration;
import br.com.university.db.RegistrationStatus;

public class DetailsRegistrationDto {

	private Long id;
	private String title;
	private String message;
	private String user;
	private RegistrationStatus status;
	private List<ReplyDTO> reply;

	public DetailsRegistrationDto(Registration registration) {
		this.id = registration.getId();
		this.title = registration.getTitle();
		this.message = registration.getMessage();
		this.user = registration.getUser().getName();
		this.status = registration.getStatus();
		this.reply = new ArrayList<>();
		this.reply.addAll(registration.getRespostas().stream().map(ReplyDTO::new).collect(Collectors.toList()));
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

	public String getUser() {
		return user;
	}

	public RegistrationStatus getStatus() {
		return status;
	}

	public List<ReplyDTO> getReply() {
		return reply;
	}

}
