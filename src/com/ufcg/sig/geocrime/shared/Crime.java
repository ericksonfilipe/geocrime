package com.ufcg.sig.geocrime.shared;

import java.io.Serializable;

public class Crime implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2363447308297415872L;

	public Crime() {
	}

	public Crime(int id, String tipo, String descricao, String horario,long data,
			double lat, double longi) {
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.horario = horario;
		this.lat = lat;
		this.data = data;
		this.longi = longi;
	}
	public int id;
	public String tipo;
	public String descricao;
	public long data;
	public String horario;
	public double lat;
	public double longi;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	public long getData() {
		return data;
	}
	public void setData(long data) {
		this.data = data;
	}
}
