package com.spring.dao;

import java.util.List;

import com.spring.model.Form;
import com.spring.model.Response;

public interface FormDao {
	public void addForms(Form f);
	public List<Form> getForms(String user);
	public String ViewForm(String user, String code);
	public String ViewFormCode(String code);
	public void addResponse(Response r);
	public void deleteForm(String code);
	public List<Response> viewResponses(String code,String user);
	public List<Response> viewResponseUsers(String code);
	public List<Response> viewResponseQuestions(String code);
	public List<Form> getUserForms(String user);
	public String getUser(String code);
}
