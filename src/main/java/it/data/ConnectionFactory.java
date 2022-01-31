package it.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static final String URL = "jdbc:postgresql://localhost:5432/bancadb";
	private static final String USER = "postgres";
	private static final String PASS = "1107";
	
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Connessione effettuata");
			
		}
		catch (SQLException ex) {
			System.out.println("Connessione non riuscita");
			ex.printStackTrace();
		}
		
		return conn;
	}

}

