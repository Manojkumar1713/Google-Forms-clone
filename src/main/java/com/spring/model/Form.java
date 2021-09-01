package com.spring.model;

public class Form {
	public String form;
	public String id;
	public String user;
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Form [form=" + form + ", id=" + id + ", user=" + user + "]";
	}
}
