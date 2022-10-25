package br.com.university.dto;

import br.com.university.db.Reply;

public class ReplyDTO {

	private Long id;
	private String message;
	private String user;

	public ReplyDTO(Reply resposta) {
		this.id = resposta.getId();
		this.message = resposta.getMessage();
		this.user = resposta.getUser().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getUser() {
		return user;
	}

}
