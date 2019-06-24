package com.itrix.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DbUtil {
	public static synchronized Connection getConnection()
	{
		Connection connection=null;		
		try {
			/******************Configure Database************************/
			ResourceBundle rs = ResourceBundle.getBundle("dbConfig");
			String driver = rs.getString("driver");
			String url = rs.getString("url");
			String user = rs.getString("username");
			String password = rs.getString("password");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		//	System.out.println(connection);// driver type
		} catch (ClassNotFoundException e)
		{
			System.out.println("Dao MSG:Excetopn occur at DbConnection class:Class not found exception ");
			e.printStackTrace();
		} catch (SQLException e) {			
			System.out.println("Dao MSG:Excetopn occur at DbConnection class:SQL exception ");
			e.printStackTrace();
		}		
		return connection;
	}
}
