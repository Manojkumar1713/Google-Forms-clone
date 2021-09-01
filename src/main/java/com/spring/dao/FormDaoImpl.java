package com.spring.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.spring.model.Form;
import com.spring.model.Response;
import com.spring.model.User;
import com.spring.mysqlconnection.MySqlConnection;

public class FormDaoImpl implements FormDao{

	@Override
	public void addForms(Form f) {
		try {
			System.out.println("UserDoa AddUser Hits");
			String sql = "Insert into form(userid,form,form_key) values('"+f.getUser()+"',"+"\"" +f.getForm()+" \" ,'"+f.getId()+"');";
			System.out.println(sql);
			Connection con = (Connection) MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				System.out.println("Executed "+sql);
				con.close();
			}
			System.out.println("FormDao AddForm ends");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Form> getForms(String u) {
		List<Form> form = new ArrayList<>();
		System.out.println("UserDao getUser hits");
		String sql = "select * from form where userid='"+u+"';";
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					Form f  = new Form();
					String user = rs.getString(2);
					String form_inp = rs.getString(3);
					String form_key = rs.getString(4);
					f.setForm(form_inp);
					f.setId(form_key);
					f.setUser(user);
					form.add(f);
				}
				
			}
			System.out.println("FormDao getForm ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;
	}

	@Override
	public String ViewForm(String user, String code) {
		String form_inp = "";
		String sql = "Select form from form where form_key ='"+code+"' and userid = '"+user+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					form_inp = rs.getString(1);
				}
			}
			System.out.println("viewForm getUser ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form_inp;
	}

	@Override
	public String ViewFormCode(String code) {
		String form_inp = "";
		String sql = "Select form from form where form_key ='"+code+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					form_inp = rs.getString(1);
				}
			}
			System.out.println("viewForm getUser ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form_inp;
	}

	@Override
	public void addResponse(Response r) {
		try {
			System.out.println("UserDoa AddUser Hits");
			String sql = "Insert into Responses(formId,Question,answer,userId) values('"+r.getCode()+"',"+"'"+r.getQuestion()+"','"+r.getAnswer()+"','"+r.getUser()+"');";
			System.out.println(sql);
			Connection con = (Connection) MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				st.executeUpdate(sql);
				System.out.println("Executed "+sql);
				con.close();
			}
			System.out.println("AddResponse ends");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteForm(String code) {

		String sql = "delete from form where form_key ='"+code+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				st.execute(sql);
				
			}
			System.out.println("delete form getUser ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Response> viewResponses(String code,String user) {
		List<Response> response = new ArrayList<>();
		String sql = "Select answer from Responses where formId ='"+code+"' and userid ='"+user+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					Response r = new Response();	
					r.setAnswer(rs.getString(1));
					response.add(r);
				}
			}
			System.out.println("View Response ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public List<Response> viewResponseUsers(String code) {
		List<Response> responseUser = new ArrayList<>();
		String sql = "Select distinct userId from Responses where formId ='"+code+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					Response r = new Response();
					r.setUser(rs.getString(1));
					responseUser.add(r);
				}
			}
			System.out.println("View Response ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseUser;
	}
	
	@Override
	public List<Response> viewResponseQuestions(String code) {
		List<Response> responseQues = new ArrayList<>();
		String sql = "Select distinct question from Responses where formId ='"+code+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {
					Response r = new Response();
					r.setQuestion(rs.getString(1));
					responseQues.add(r);
				}
			}
			System.out.println("View Response ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseQues;
	}

	@Override
	public List<Form> getUserForms(String user) {
		List<Form> form = new ArrayList<>();
		System.out.println("view submitted  hits");
		String sql = "select distinct formId from Responses where userid='"+user+"';";
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				
				while(rs.next()) {
					Form f  = new Form();
					String fr = rs.getString(1);
					f.setId(fr);
					form.add(f);
				}
				
			}
			System.out.println("FormDao getForm ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return form;
	}

	@Override
	public String getUser(String code) {
		String user = "";
		System.out.println("view submitted  hits");
		String sql = "select userId from form where form_key='"+code+"';";
		System.out.println(sql);
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				while(rs.next()) {	
					user = rs.getString(1);
				}
			}
			System.out.println("FormDao getForm ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
