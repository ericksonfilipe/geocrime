package com.ufcg.sig.geocrime.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.ufcg.sig.geocrime.shared.Crime;
import com.ufcg.sig.geocrime.shared.Delegacia;
import com.ufcg.sig.geocrime.shared.Viatura;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("geoCrimeService")
public interface GeoCrimeService extends RemoteService {

	void saveCrime(String tipo, String descricao, String horario, Date data,
			double lat, double longi);
	
	public List<Crime> getCrimes();

	public List<Delegacia> getDelegacias();

	public List<Viatura> getViaturas();

	void saveViatura(int delegacia,String id_radio,String infoadicionais,double lat,double longi);

	void saveDelegacia(String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi);
	
	public List<Crime> consulta2();

	public List<Delegacia> consulta6();
}
