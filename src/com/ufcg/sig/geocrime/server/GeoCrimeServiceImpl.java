package com.ufcg.sig.geocrime.server;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufcg.sig.geocrime.client.GeoCrimeService;
import com.ufcg.sig.geocrime.server.persistence.DatabaseControl;
import com.ufcg.sig.geocrime.server.persistence.PersistenceFacade;
import com.ufcg.sig.geocrime.shared.Crime;

/**
 * The server side implementation of the RPC service.
 */
public class GeoCrimeServiceImpl extends RemoteServiceServlet implements
		GeoCrimeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DatabaseControl db = DatabaseControl.getInstance();
	
	public void saveCrime(String tipo, String descricao, String horario,
			Date data, double lat, double longi) {
		PersistenceFacade.getInstance().saveCrime(tipo, descricao, horario,
				data, lat, longi);
	}

	@Override
	public List<Crime> getCrimes() {
		try {
			return db.selectCrimes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	public void saveViatura(int delegacia, String id_radio,
			String infoadicionais, double lat, double longi) {
		PersistenceFacade.getInstance().saveViatura(delegacia,  id_radio,
			 infoadicionais,  lat,  longi);
		
	}

	@Override
	public void saveDelegacia(String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi) {
		PersistenceFacade.getInstance().saveDelegacia( unidade,  delegado,  contingente,
				 infoadicionais,  lat, longi);
		
	}
}
