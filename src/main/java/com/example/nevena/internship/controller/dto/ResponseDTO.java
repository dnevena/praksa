package com.example.nevena.internship.controller.dto;

import com.example.nevena.internship.domain.User;

public class ResponseDTO {

	private String message;
	private User user;
	
	public ResponseDTO() {
		super();
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
		
}
