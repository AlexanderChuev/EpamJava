package com.chuyeu.training.myapp.services.logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {

	private static String URI = "jdbc:postgresql://localhost:5432/postgres";
	private static String USER = "postgres";
	private static String PASSWORD = "pass";

	public static Connection getDatabaseConnection() throws SQLException {
		return DriverManager.getConnection(URI, USER, PASSWORD);
	}
}