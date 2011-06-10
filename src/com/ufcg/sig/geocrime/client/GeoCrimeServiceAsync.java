package com.ufcg.sig.geocrime.client;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GeoCrimeServiceAsync {

	void saveCrime(String tipo, String descricao, String horario, Date data,
			double lat, double longi, AsyncCallback<Void> asyncCallback);

}
