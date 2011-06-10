package com.ufcg.sig.geocrime.server;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.ufcg.sig.geocrime.client.GeoCrimeService;
import com.ufcg.sig.geocrime.server.persistence.PersistenceFacade;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GeoCrimeServiceImpl extends RemoteServiceServlet implements GeoCrimeService {
	
	public String greetServer(String input) throws IllegalArgumentException {
		return "Hello!";
	}

	@Override
	public void greetServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveCrime(String tipo, String descricao, String horario,
			Date data, double lat, double longi) {
		PersistenceFacade.getInstance().saveCrime( tipo,  descricao,  horario,
				 data,  lat,  longi);
		
	}
}
