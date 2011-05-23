package com.ufcg.sig.geocrime.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PopupCadastrar extends PopupPanel {
	
	public PopupCadastrar() {
		super(false);
		
		setGlassEnabled(true);
		setTitle("Cadastrar Ocorrencia de Crime");
		
		VerticalPanel vCadastrar = new VerticalPanel();
		vCadastrar.setSpacing(20);
		vCadastrar.setWidth("500px");
		
		add(vCadastrar);

		Label tipoLabel = new Label("Tipo");
		ListBox tipo = new ListBox(false);
	    
	    tipo.addItem("Arrombamento");
	    tipo.addItem("Roubo de Carro");
	    tipo.addItem("Assassinato");
	    tipo.addItem("Assalto");
	    tipo.addItem("...");

	    tipo.setVisibleItemCount(3);
		tipo.setWidth("450px");
		
		Label descricaoLabel = new Label("Descricao:");
		TextArea descricao = new TextArea();
		descricao.setWidth("450px");
		descricao.setHeight("100px");
		
		Label horarioLabel = new Label("Horario:");
	    TextBox horario = new TextBox();
	    
	    Label dataLabel = new Label("Data:");
	    TextBox data = new TextBox();
	    
	    Button bFechar = new Button("Fechar");

	    vCadastrar.add(tipoLabel);
		vCadastrar.add(tipo);
		vCadastrar.add(descricaoLabel);
		vCadastrar.add(descricao);
		vCadastrar.add(horarioLabel);
		vCadastrar.add(horario);
		vCadastrar.add(dataLabel);
		vCadastrar.add(data);
		vCadastrar.add(bFechar);
		
	
		bFechar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		
	}
}
