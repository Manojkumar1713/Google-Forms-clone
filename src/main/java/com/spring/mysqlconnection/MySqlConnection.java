package com.spring.mysqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	static String driverName = "com.mysql.jdbc.Driver";
	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName(driverName);

		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simple","MM0193","manasarvmk");
		return con;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = MySqlConnection.getConnection();
		if(con == null) {
			System.out.println("err");
		}
		else {
			System.out.println("done");
		}
	}
}
