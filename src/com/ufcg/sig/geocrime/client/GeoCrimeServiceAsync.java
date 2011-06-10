package com.ufcg.sig.geocrime.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.ufcg.sig.geocrime.shared.Crime;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GeoCrimeServiceAsync {

	void saveCrime(String tipo, String descricao, String horario, Date data,
			double lat, double longi, AsyncCallback<Void> asyncCallback);

	void getCrimes(AsyncCallback<List<Crime>> callback);

	void saveDelegacia(String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi,
			AsyncCallback<Void> callback);

	void saveViatura(int delegacia, String id_radio, String infoadicionais,
			double lat, double longi, AsyncCallback<Void> callback);

}
