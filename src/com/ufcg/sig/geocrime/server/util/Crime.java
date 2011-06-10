package com.ufcg.sig.geocrime.server.util;

import java.sql.Timestamp;
import java.util.Date;

public class Crime {

	
	public Crime(int id, String tipo, String descricao, String horario,Date data,
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
	public Date data;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
}
