package com.ufcg.sig.geocrime.server.util;

public class Viatura {
	  public Viatura(int id, int delegacia, int id_radio, String infoadicionais,
			double lat, double longi) {
		this.id = id;
		this.delegacia = delegacia;
		this.id_radio = id_radio;
		this.infoadicionais = infoadicionais;
		this.lat = lat;
		this.longi = longi;
	}
	public int id;
	  public int delegacia;
	  public int id_radio;
	  public String infoadicionais;
	  public double lat;
	  public double longi;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDelegacia() {
		return delegacia;
	}
	public void setDelegacia(int delegacia) {
		this.delegacia = delegacia;
	}
	public int getId_radio() {
		return id_radio;
	}
	public void setId_radio(int id_radio) {
		this.id_radio = id_radio;
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
