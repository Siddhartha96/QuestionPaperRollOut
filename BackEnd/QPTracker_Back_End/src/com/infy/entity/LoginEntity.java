package com.infy.entity;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Login")
public class LoginEntity {
	
	@Id
	private String username;
	private String password;
	private String category;
	private String sQuestion;
	private String sAnswer;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
