package com.spring.dao;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import java.sql.*;

import com.spring.model.User;
import com.spring.mysqlconnection.MySqlConnection;
@Repository
public class UserDaoImpl implements UserDao{

	@Override
	public void addUser(User user) {
		try {
			System.out.println("UserDoa AddUser Hits");
			String sql = "Insert into user(Email,Passwd) values('"+user.getPhoneNo()+"','"+user.getPassword()+"');";
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
			System.out.println("UserDao AddUser ends");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User getUser(User user) {
		System.out.println("UserDao getUser hits");
		User u  = new User();
		String sql = "Select count(*) from user where email ='"+user.getPhoneNo()+"' and passwd = '"+user.getPassword()+"';";
		try {
			Connection con = MySqlConnection.getConnection();
			if(con == null) {
				System.out.println("Database connection error");
			}
			else {
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				int size = 0;
				if(rs != null) {
					rs.last();
					size = rs.getRow();
				}
				if(size == 1) {
					u.setPhoneNo(user.getPhoneNo());
					u.setPassword(user.getPassword());
					
					System.out.println("Executed "+sql);
					con.close();
				}
			}
			System.out.println("UserDao getUser ends");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return u;
	}

}
