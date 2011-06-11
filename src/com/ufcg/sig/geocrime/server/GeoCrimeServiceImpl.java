package com.ufcg.sig.geocrime.server;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufcg.sig.geocrime.client.GeoCrimeService;
import com.ufcg.sig.geocrime.server.persistence.DatabaseControl;
import com.ufcg.sig.geocrime.server.persistence.PersistenceFacade;
import com.ufcg.sig.geocrime.shared.Crime;
import com.ufcg.sig.geocrime.shared.Delegacia;
import com.ufcg.sig.geocrime.shared.Viatura;

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
	};
	
	public void saveViatura(int delegacia, String id_radio,
			String infoadicionais, double lat, double longi) {
		PersistenceFacade.getInstance().saveViatura(delegacia,  id_radio,
			 infoadicionais,  lat,  longi);
		
	}

	@Override
	public void saveDelegacia(String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi) {
		System.out.println("ks;adgj;jg;eja;gjfd;kgsfkdgkdsfjglkjdslkgjhdlfjgldfkjglkdfjglkdjfgdfgdf");
		PersistenceFacade.getInstance().saveDelegacia( unidade,  delegado,  contingente,
				 infoadicionais,  lat, longi);
		
	}

	@Override
	public List<Delegacia> getDelegacias() {
		try {
			return db.selectDelegacias();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Viatura> getViaturas() {
		try {
			return db.selectViaturas();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Crime> consulta2() {
		try {
			return db.consultaDois();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Delegacia> consulta6() {
		try {
			return db.consultaSeis();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
