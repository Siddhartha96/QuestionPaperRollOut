package com.infy.model;

public class Login {
	private String username;
	private String password;
	private String category;
	private String newPassword;
	private String confirmPassword;
	private String sQuestion;
	private String sAnswer;
	private String message;
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getsQuestion() {
		return sQuestion;
	}
	public void setsQuestion(String sQuestion) {
		this.sQuestion = sQuestion;
	}
	public String getsAnswer() {
		return sAnswer;
	}
	public void setsAnswer(String sAnswer) {
		this.sAnswer = sAnswer;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
