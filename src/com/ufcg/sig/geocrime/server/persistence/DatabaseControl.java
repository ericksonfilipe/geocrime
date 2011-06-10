package com.ufcg.sig.geocrime.server.persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgis.PGgeometry;

import com.ufcg.sig.geocrime.shared.Crime;
import com.ufcg.sig.geocrime.shared.Delegacia;
import com.ufcg.sig.geocrime.shared.Viatura;

public class DatabaseControl {

	private static DatabaseControl instance;

	public final String driver = "org.postgresql.Driver";
	private final String postgres = "jdbc:postgresql:";

	public static final String INSERT_CRIME = "INSERT INTO Crime(tipo,descricao,horario,data,the_geom) VALUES (?,?,?,?,ST_GeomFromText(?, 29195));";
	public static final String INSERT_DELEGACIA = "INSERT INTO Delegacia(id, unidade, delegado, contingente, infoAdicionais, the_geom) VALUES(DEFAULT,?,?,?,?,ST_GeomFromText(?, 29195));";
	public static final String INSERT_VIATURA = "INSERT INTO Viatura(id, delegacia, id_radio, infoAdicionais, the_geom) VALUES(DEFAULT,?,?,?,ST_GeomFromText(?, 29195));";
	
	public static final String SELECT_ALLCRIME = "SELECT * FROM Crime;";
	public static final String SELECT_ALLDELEGACIA = "SELECT * FROM View_Delegacia;";
	public static final String SELECT_ALLVIATURA = "SELECT * FROM Viatura;";
	
	public static final String SELECT_CRIME = "SELECT * FROM Crime WHERE id = ?;";
	public static final String SELECT_DELEGACIA = "SELECT * FROM View_Delegacia WHERE id = ?;";
	public static final String SELECT_VIATURA = "SELECT * FROM Viatura WHERE id = ?;";
	
	public static final String CONSULTA_UM = "SELECT * FROM Consulta_Um(?, ?);";
	public static final String CONSULTA_DOIS = "SELECT * FROM Consulta_Dois;";
	public static final String CONSULTA_QUATRO = "SELECT * FROM Consulta_Quatro;";
	public static final String CONSULTA_SEIS = "SELECT * FROM Consulta_Seis;";
	
	private String database = "geocrime";
	private String username = "postgres";
	private String password = "sig";
	private String host = "//localhost";
	private String port = "5432";
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

	public static DatabaseControl getInstance() {
		if (instance == null) {
			try {
				instance = new DatabaseControl(null, null, null, null, null);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return instance;
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
	
	public List<Crime> consultaUm(long x, long y) throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.CONSULTA_UM);
		s.setLong(1, x);
		s.setLong(1, y);
		ResultSet rs = s.executeQuery();
		List<Crime> crimes = new ArrayList<Crime>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Date data = (Date) rs.getObject("data");
			String horario = (String) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			crimes.add(new Crime(id, tipo, descricao, horario, data.getTime(), lat, longi));
		}
		
		return crimes;
	}
	
	public List<Crime> consultaDois() throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.CONSULTA_DOIS);
		ResultSet rs = s.executeQuery();
		List<Crime> crimes = new ArrayList<Crime>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Date data = (Date) rs.getObject("data");
			String horario = (String) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			crimes.add(new Crime(id, tipo, descricao, horario, data.getTime(), lat, longi));
		}
		
		return crimes;
	}
	
	public List<Crime> consultaQuatro() throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.CONSULTA_QUATRO);
		ResultSet rs = s.executeQuery();
		List<Crime> crimes = new ArrayList<Crime>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Date data = (Date) rs.getObject("data");
			String horario = (String) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			crimes.add(new Crime(id, tipo, descricao, horario, data.getTime(), lat, longi));
		}
		
		return crimes;
	}
	
	public List<Delegacia> consultaSeis() throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.CONSULTA_SEIS);
		ResultSet rs = s.executeQuery();
		List<Delegacia> delegacias = new ArrayList<Delegacia>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String unidade = (String) rs.getObject("unidade");
			String delegado = (String) rs.getObject("delegado");
			Integer contingente = (Integer) rs.getObject("contingente");
			String infoAdicionais = (String) rs.getObject("infoAdicionais");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			delegacias.add(new Delegacia(id, unidade, delegado, contingente, infoAdicionais, lat, longi));
		}
		
		return delegacias;
	}
	
	public List<Crime> selectCrimes() throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.SELECT_ALLCRIME);
		ResultSet rs = s.executeQuery();
		List<Crime> crimes = new ArrayList<Crime>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Date data = (Date) rs.getObject("data");
			String horario = (String) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			crimes.add(new Crime(id, tipo, descricao, horario, data.getTime(), lat, longi));
		}
		
		return crimes;
	}
	
	public List<Delegacia> selectDelegacias() throws SQLException {
		PreparedStatement s = con.prepareStatement(DatabaseControl.SELECT_ALLDELEGACIA);
		ResultSet rs = s.executeQuery();
		List<Delegacia> delegacias = new ArrayList<Delegacia>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			String unidade = (String) rs.getObject("unidade");
			String delegado = (String) rs.getObject("delegado");
			Integer contingente = (Integer) rs.getObject("contingente");
			String infoAdicionais = (String) rs.getObject("infoAdicionais");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			delegacias.add(new Delegacia(id, unidade, delegado, contingente, infoAdicionais, lat, longi));
		}
		
		return delegacias;
	}
	
	public List<Viatura> selectViaturas() throws SQLException {
		PreparedStatement s = con.prepareStatement(DatabaseControl.SELECT_ALLVIATURA);
		ResultSet rs = s.executeQuery();
		List<Viatura> viaturas = new ArrayList<Viatura>();
		while (rs.next()) {
			Integer id = (Integer) rs.getObject("id");
			Integer delegacia = (Integer) rs.getObject("delegacia");
			String id_radio = (String) rs.getObject("id_radio");
			String infoAdicionais = (String) rs.getObject("infoAdicionais");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			viaturas.add(new Viatura(id, delegacia, id_radio, infoAdicionais, lat, longi));
		}
		
		return viaturas;
	}

	public Crime selectCrime(int id) throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.SELECT_CRIME);
		s.setInt(1, id);
		ResultSet rs = s.executeQuery();
		Crime crime = null;
		while (rs.next()) {
			String tipo = (String) rs.getObject("tipo");
			String descricao = (String) rs.getObject("descricao");
			Date data = (Date) rs.getObject("data");
			String horario = (String) rs.getObject("horario");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
		
			crime = new Crime(id, tipo, descricao, horario, data.getTime(), lat, longi);
		}
		
		return crime;
	}
	
	public Delegacia selectDelegacia(int id) throws SQLException {
		PreparedStatement s = con.prepareStatement(DatabaseControl.SELECT_DELEGACIA);
		ResultSet rs = s.executeQuery();
		s.setInt(1, id);
		Delegacia delegacia = null;
		while (rs.next()) {
			String unidade = (String) rs.getObject("unidade");
			String delegado = (String) rs.getObject("delegado");
			Integer contingente = (Integer) rs.getObject("contingente");
			String infoAdicionais = (String) rs.getObject("infoAdicionais");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			delegacia = new Delegacia(id, unidade, delegado, contingente, infoAdicionais, lat, longi);
		}
		
		return delegacia;
	}
	
	public Viatura selectViatura(int id) throws SQLException {
		PreparedStatement s = con.prepareStatement(DatabaseControl.SELECT_ALLVIATURA);
		ResultSet rs = s.executeQuery();
		s.setInt(1, id);
		Viatura viatura = null;
		while (rs.next()) {
			Integer delegacia = (Integer) rs.getObject("delegacia");
			String id_radio = (String) rs.getObject("id_radio");
			String infoAdicionais = (String) rs.getObject("infoAdicionais");
			PGgeometry the_geom = (PGgeometry) rs.getObject("the_geom");
			String point = the_geom.getGeometry().getValue();
			point = point.replace("(", "");
			point = point.replace(")", "");
			String[] ponto = point.split(" ");
			double lat = Double.valueOf(ponto[0]);
			double longi = Double.valueOf(ponto[1]);
			
			viatura = new Viatura(id, delegacia, id_radio, infoAdicionais, lat, longi);
		}
		
		return viatura;
	}
	

	public void insertCrime(Crime c) throws SQLException {
		System.out.println("AQUI");
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.INSERT_CRIME);
		System.out.println("AQUI");
		s.setString(1, c.getTipo());
		s.setString(2, c.getDescricao());
		s.setString(3, c.getHorario());
		Date sql = new Date(c.getData());
		s.setDate(4, sql);
		String lat = String.valueOf(c.getLat());
		lat = lat.replace(",", ".");
		String longe = String.valueOf(c.getLongi());
		longe = longe.replace(",", ".");
		String ponto = "POINT(" + lat + " " + longe + ")";
		s.setString(5, ponto);
		s.execute();
		s.close();
		System.out.println("AQUI");

	}

	public void insertDelegacia(Delegacia d) throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.INSERT_DELEGACIA);
		System.out.println("AQUI");
		s.setString(1, d.getUnidade());
		s.setString(2, d.getDelegado());
		s.setInt(3, d.getContingente());
		System.out.println("AQUI");
		s.setString(4, d.getInfoadicionais());
		String lat = String.valueOf(d.getLat());
		lat = lat.replace(",", ".");
		String longe = String.valueOf(d.getlongi());
		longe = longe.replace(",", ".");
		String ponto = "POINT(" + lat + " " + longe + ")";
		s.setString(5, ponto);
		s.execute();
		s.close();
		System.out.println("AQUI");
	}
	
	public void insertViatura(Viatura v) throws SQLException {
		PreparedStatement s = con
				.prepareStatement(DatabaseControl.INSERT_VIATURA);
		
		s.setInt(1, v.getDelegacia());
		s.setString(2, v.getId_radio());
		s.setString(3, v.getInfoadicionais());
		String lat = String.valueOf(v.getLat());
		lat = lat.replace(",", ".");
		String longe = String.valueOf(v.getlongi());
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
			Crime crime = new Crime(1, "assalto", "no buxo","12:30",t.getTime(), 5, 5);
			Delegacia delegacia = new Delegacia(1,"unidade da paz","neguim",30,"lol",0,1);
			c.insertDelegacia(delegacia);
	//		c.insertCrime(crime);
		//	Crime crime2 = c.selectCrime(1);
	//		System.out.println(crime2.tipo);
	//		List<Crime> x = c.selectCrimes();
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
