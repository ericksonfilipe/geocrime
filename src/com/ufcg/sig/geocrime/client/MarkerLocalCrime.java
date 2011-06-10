package com.ufcg.sig.geocrime.client;

import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.maps.client.overlay.MarkerOptions;
import com.google.gwt.user.client.ui.HTML;

public class MarkerLocalCrime extends Marker{

	private String local;
	private String tipo;
	private String descricao;
	private String horario;
	private String data;

	
	
	public MarkerLocalCrime(LatLng point, MarkerOptions options) {
		super(point, options);
	}

	public MarkerLocalCrime(LatLng point) {
		super(point);
	}

	
	

	public void setDados(String local, String tipo, String descricao, String horario, String data) {
		this.local = local;
		this.tipo = tipo;
		this.descricao = descricao;
		this.horario = horario;
		this.data = data;
	}
	
	
	public HTML getHTML() {
		HTML result = new HTML("<b>Local:</b> "+local+"<br /> <b>Tipo:</b> "+tipo+"<br /> <b>Descricao:</b> "+descricao+"<br /> <b>Horario:</b> "+horario+"<br /> <b>Data</b>: "+data);
		
		return result;
	}
	
	
}
