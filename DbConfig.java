package org.mess.config;

import java.sql.*;

public class DbConfig {
protected Connection conn;
protected PreparedStatement stmt;
protected ResultSet rs;
	
	public DbConfig() {
		try {
					
			Class.forName(PathHelper.p.getProperty("db.driverClass"));
			conn = DriverManager.getConnection(
					PathHelper.p.getProperty("db.url"),
					PathHelper.p.getProperty("db.username"),
					PathHelper.p.getProperty("db.password") 
					);
			//System.out.println("Database is Connected");
		}
		catch(Exception ex) {
			System.out.println("Error is: " + ex);
		}
	}
	
}
