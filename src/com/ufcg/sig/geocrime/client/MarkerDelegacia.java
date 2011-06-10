package com.ufcg.sig.geocrime.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

public class MarkerDelegacia  extends Marker{

	private String unidade;
	private String delegado;
	private Integer contingente;
	private Integer numViaturas;
	private String infoAdd;
	
	
	public MarkerDelegacia(LatLng point, MarkerOptions options) {
		super(point, options);
	}

	
	public MarkerDelegacia(LatLng point) {
		super(point);
	}
	
	public void setDados(String unidade, String delegado, Integer contingente, Integer numViaturas, String infoAdd) {
		this.unidade = unidade;
		this.delegado = delegado;
		this.contingente = contingente;
		this.numViaturas = numViaturas;
		this.infoAdd = infoAdd;
	}
	
	public HTML getHTML() {
		HTML result = new HTML("<b>TODO</b>");
		
		return result;
	}
}
