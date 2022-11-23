package com.example.miniprajet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MaConnexion {
	public static Connection connecter()
	{
		Connection con= null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			 con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/medicale","root","");
			 System.out.println("connected...");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("erreur conxion");
		}
		return con;
		
	}

	public static void main(String[] args) {
		connecter();

	}

}
