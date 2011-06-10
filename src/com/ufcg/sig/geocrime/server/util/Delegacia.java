package com.ufcg.sig.geocrime.server.util;

public class Delegacia {
	  public Delegacia(int id, String unidade, String delegado, int contingente,
			String infoadicionais, double lat, double longi) {
		this.id = id;
		this.unidade = unidade;
		this.delegado = delegado;
		this.contingente = contingente;
		this.infoadicionais = infoadicionais;
		this.lat = lat;
		this.longi = longi;
	}
	public int id;
	  public String unidade;
	  public String delegado;
	  public int contingente;
	  public String infoadicionais;
	  public double lat;
	  public double longi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getDelegado() {
		return delegado;
	}
	public void setDelegado(String delegado) {
		this.delegado = delegado;
	}
	public int getContingente() {
		return contingente;
	}
	public void setContingente(int contingente) {
		this.contingente = contingente;
	}
	public String getInfoadicionais() {
		return infoadicionais;
	}
	public void setInfoadicionais(String infoadicionais) {
		this.infoadicionais = infoadicionais;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getlongi() {
		return longi;
	}
	public void setlongi(double longi) {
		this.longi = longi;
	}
}
