package com.ufcg.sig.geocrime.server;

import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufcg.sig.geocrime.client.GeoCrimeService;
import com.ufcg.sig.geocrime.server.persistence.PersistenceFacade;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GeoCrimeServiceImpl extends RemoteServiceServlet implements
		GeoCrimeService {

	public void saveCrime(String tipo, String descricao, String horario,
			Date data, double lat, double longi) {
		PersistenceFacade.getInstance().saveCrime(tipo, descricao, horario,
				data, lat, longi);
	}

	@Override
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
