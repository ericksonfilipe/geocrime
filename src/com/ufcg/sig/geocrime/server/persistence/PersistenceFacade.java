package com.ufcg.sig.geocrime.server.persistence;

import java.sql.SQLException;
import java.util.Date;

import com.ufcg.sig.geocrime.server.util.Crime;

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
}
