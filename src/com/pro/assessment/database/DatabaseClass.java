package com.pro.assessment.database;

import java.sql.*;

public class DatabaseClass {
	public Connection getConnection(){
	
	      Connection con = null;
	      
	      try {
	         Class.forName("org.sqlite.JDBC");
	         con = DriverManager.getConnection("jdbc:sqlite:Northwind_large.sqlite");
	      } catch ( Exception e ) {
	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	         System.exit(0);
	      }
	      return con;
	   }
}