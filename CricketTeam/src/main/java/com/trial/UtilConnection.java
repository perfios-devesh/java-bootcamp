package com.trial;

import java.sql.Connection;
import java.sql.DriverManager;

public class UtilConnection {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CricketTeam", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

}
