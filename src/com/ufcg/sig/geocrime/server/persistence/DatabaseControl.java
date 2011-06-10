package com.ufcg.sig.geocrime.server.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.postgis.PGgeometry;

import com.ufcg.sig.geocrime.server.util.Crime;
import com.ufcg.sig.geocrime.server.util.Delegacia;
import com.ufcg.sig.geocrime.server.util.Viatura;

public class DatabaseControl {
	public final String driver = "org.postgresql.Driver";
	private final String postgres = "jdbc:postgresql:";

	public static final String INSERT_CRIME = "INSERT INTO Crime(tipo,descricao,horario,data,the_geom) VALUES (?,?,?,?,ST_GeomFromText(?, 29195));";
	public static final String SELECT_CRIME = "SELECT * FROM Crime WHERE id = ?";
	public static final String SELECT_ALLCRIME = "SELECT * FROM Crime";
	public static final String INSERT_DELEGACIA = "INSERT INTO Delegacia(unidade,delegado,contingente,infoAdicionais,the_geom) VALUES (?,?,?,?,ST_GeomFromText(?,29195));";
	private String database = "geocrime";
	private String username = "postgres";
	private String password = "sig";
	private String host = "//localhost";
	private String port = "5433";
	Connection con;

	public DatabaseControl(String database, String username, String password,
			String host, String port) throws SQLException,
			ClassNotFoundException {
		if (database != null)
			this.database = database;
		if (username != null)
			this.username = username;
		if (password != null)
			this.password = password;
		if (host != null)
			this.host = host;
		if (port != null)
			this.port = port;
		Class.forName(driver);
		con = DriverManager
				.getConnection(this.postgres + this.host + ":" + this.port
						+ "/" + this.database, this.username, this.password);
	}

	public ArrayList<Crime> getCrimes(String condition) {
		return null;
	}

	public ArrayList<Delegacia> getDelegacias(String condition) {
		return null;
	}

	public ArrayList<Viatura> getViatura(String condition) {
		return null;
	}

	public void selectCrime() throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.SELECT_ALLCRIME);
		ResultSet rs = s.executeQuery();
		while (rs.next()) {
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Timestamp horario = (Timestamp) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
		}
	}

//	public Crime selectCrime(int id) throws SQLException {
//		PreparedStatement s = con
//				.prepareStatement(DatabaseControl.INSERT_CRIME);
//		s.setInt(1, id);
//		ResultSet rs = s.executeQuery();
//		String tipo = (String) rs.getObject("tipo");
//		String descricao = (String) rs.getObject("descricao");
//		Timestamp horario = (Timestamp) rs.getObject("horario");
//		PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
//		String point = the_geom.getGeometry().getValue();
//		point = point.replace("(", "");
//		point = point.replace(")", "");
//		String[] ponto = point.split(" ");
//		double lat = Double.valueOf(ponto[0]);
//		double longi = Double.valueOf(ponto[1]);
//		return new Crime(id, tipo, descricao, horario, lat, longi);
//	}

	public void insertCrime(Crime c) throws SQLException {

		PreparedStatement s = con
				.prepareStatement(DatabaseControl.INSERT_CRIME);
		s.setString(1, c.getTipo());
		s.setString(2, c.getDescricao());
		s.setString(3, c.getHorario());
		Date sql = Date.valueOf(c.getData().toString());
		s.setDate(4, sql);
		String lat = String.valueOf(c.getLat());
		lat = lat.replace(",", ".");
		String longe = String.valueOf(c.getLongi());
		longe = longe.replace(",", ".");
		String ponto = "POINT(" + lat + " " + longe + ")";
		s.setString(5, ponto);
		s.execute();
		s.close();

	}

	public void insertDelegacia(Delegacia d) throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.INSERT_DELEGACIA);
		
		s.setString(1, d.getUnidade());
		s.setString(2, d.getDelegado());
		s.setInt(3, d.getContingente());
		s.setString(4, d.getInfoadicionais());
		String lat = String.valueOf(d.getLat());
		lat = lat.replace(",", ".");
		String longe = String.valueOf(d.getlongi());
		longe = longe.replace(",", ".");
		String ponto = "POINT(" + lat + " " + longe + ")";
		s.setString(5, ponto);
		s.execute();
		s.close();
	}

	public void close() throws SQLException {
		con.close();
	}

	public static void main(String[] args) {
		try {
			DatabaseControl c = new DatabaseControl(null, null, null, null,
					null);
			Date t = new Date(0);
			Crime crime = new Crime(1, "assalto", "no buxo","12:30",t, 5, 5);
			//Delegacia delegacia = new Delegacia(1,"unidade da paz","neguim",30,"lol",0,1);
			c.insertCrime(crime);
			// c.insertCrime(crime);
			//crime = c.selectCrime(1);
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Tenso");
			e.printStackTrace();
		}
	}
}
