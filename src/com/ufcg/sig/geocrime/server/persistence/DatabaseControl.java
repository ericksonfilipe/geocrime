package com.ufcg.sig.geocrime.server.persistence;
import java.sql.*;

public class DatabaseControl {

	public final String driver = "org.postgresql.Driver";
	private final String postgres = "jdbc:postgresql:";

	private String database = "GeoCrime";
	private String username = "postgres";
	private String password = "sig";
	private String host = "//localhost";
	private String port = "5433";
	Connection c;
	
	public DatabaseControl(String database,String username,String password,String host,String port) throws SQLException, ClassNotFoundException{
		if(database != null)this.database = database;
		if(username != null)this.username = username;
		if(password != null)this.password = password;
		if(host != null)this.host = host;
		if(port != null)this.port = port;
		Class.forName(driver);
		Connection c = DriverManager.getConnection(this.postgres+this.host+":"+this.port+"/"+this.database, this.username, this.password); 
		c.close();
	}
	
	

	public static void main(String[] args){
		try {
			DatabaseControl c = new DatabaseControl(null,null,null,null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Tenso");
			e.printStackTrace();
		}
	}
}
