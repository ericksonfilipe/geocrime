package com.ufcg.sig.geocrime.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

public class MarkerViatura extends Marker{

	String identificador;
	String idRadio;
	String infoAdd;
	
	public MarkerViatura(LatLng point, MarkerOptions options) {
		super(point, options);
	}
	
	public MarkerViatura(LatLng point) {
		super(point);
	}
	
	public void setDados(String identificador, String idRadio, String infoAdd) {
		this.identificador = identificador;
		this.idRadio = idRadio;
		this.infoAdd = infoAdd;
	}
	
	public HTML getHTML() {
		HTML result = new HTML("<b>VIATURA</b><br /><b>Identificador:</b> "+identificador);
		
		return result;
	}
}
