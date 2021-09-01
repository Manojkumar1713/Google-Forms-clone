package com.spring.model;

public class Response {
	public String question;
	public String answer;
	public String user;
	public String code;
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Response [question=" + question + ", answer=" + answer + ", user=" + user + ", code=" + code + "]";
	}
	
	
}
