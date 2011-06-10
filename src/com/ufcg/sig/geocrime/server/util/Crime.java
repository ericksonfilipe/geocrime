package com.ufcg.sig.geocrime.server.util;

import java.util.Date;

public class Crime {
	public Crime(int id, String tipo, String descricao, Date horario) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.horario = horario;
	}
	public int id;
	public String tipo;
	public String descricao;
	public Date horario;
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
	public Date getHorario() {
		return horario;
	}
	public void setHorario(Date horario) {
		this.horario = horario;
	}
}
