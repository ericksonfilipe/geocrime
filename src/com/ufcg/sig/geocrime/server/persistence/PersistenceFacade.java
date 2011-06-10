package com.ufcg.sig.geocrime.server.persistence;

import java.sql.SQLException;
import java.util.Date;

import com.ufcg.sig.geocrime.server.util.Crime;
import com.ufcg.sig.geocrime.server.util.Delegacia;
import com.ufcg.sig.geocrime.server.util.Viatura;

public class PersistenceFacade {
	DatabaseControl db;
	static PersistenceFacade instance;

	public static PersistenceFacade getInstance() {
		if (instance == null) {
			instance = new PersistenceFacade();
		}
		return instance;
	}
	
	public PersistenceFacade(){
		try {
			db = new DatabaseControl(null,null,null,null,null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// TODO
	public void saveCrime(String tipo, String descricao, String horario,
			Date data, double lat, double longi) {
		Crime c = new Crime(0,tipo,descricao,horario,data,lat,longi);
		try {
			db.insertCrime(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void saveViatura(int delegacia, String id_radio,
			String infoadicionais, double lat, double longi) {
		Viatura v = new Viatura(0,delegacia,id_radio,infoadicionais,lat,longi);
		try {
			db.insertViatura(v);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void saveDelegacia(String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi) {
		Delegacia d = new Delegacia(0,unidade,delegado,contingente,infoadicionais,lat,longi);
		try {
			db.insertDelegacia(d);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
