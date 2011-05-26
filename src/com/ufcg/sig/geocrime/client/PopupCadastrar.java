package com.ufcg.sig.geocrime.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PopupCadastrar extends PopupPanel {
	
	private VerticalPanel vCadastrar;
	private ListBox tipo;
	private TextArea descricao;
	private TextBox horario;
	private TextBox data;
	private TextBox local;
	
	private boolean salvou = true;
	
	public PopupCadastrar(String endereco) {
		super(false);
		
		setGlassEnabled(true);
		setAnimationEnabled(true);
		setTitle("Cadastrar Ocorrencia de Crime");
		
		vCadastrar = new VerticalPanel();
		vCadastrar.setSpacing(20);
		vCadastrar.setWidth("500px");
		
		add(vCadastrar);
		
		Label localLabel = new Label("Local");
		local = new TextBox();
		local.setWidth("450px");
		local.setText(endereco);
		
		Label tipoLabel = new Label("Tipo");
		tipo = new ListBox(false);
	    
	    tipo.addItem("Arrombamento");
	    tipo.addItem("Roubo de Carro");
	    tipo.addItem("Assassinato");
	    tipo.addItem("Assalto");
	    tipo.addItem("...");

	    tipo.setVisibleItemCount(3);
		tipo.setWidth("450px");
		
		Label descricaoLabel = new Label("Descricao:");
		descricao = new TextArea();
		descricao.setWidth("450px");
		descricao.setHeight("100px");
		
		Label horarioLabel = new Label("Horario:");
	    horario = new TextBox();
	    
	    Label dataLabel = new Label("Data:");
	    data = new TextBox();

	    vCadastrar.add(localLabel);
	    vCadastrar.add(local);
	    vCadastrar.add(tipoLabel);
		vCadastrar.add(tipo);
		vCadastrar.add(descricaoLabel);
		vCadastrar.add(descricao);
		vCadastrar.add(horarioLabel);
		vCadastrar.add(horario);
		vCadastrar.add(dataLabel);
		vCadastrar.add(data);
		vCadastrar.add(criaPanelBotoes());
	}

	
	public boolean salvou() {
		return salvou;
	}
	

	private HorizontalPanel criaPanelBotoes() {
		HorizontalPanel hPanelBotoes = new HorizontalPanel();
		hPanelBotoes.setSpacing(10);
	    
	    Button bSalvar = new Button("Salvar");
	    bSalvar.setWidth("100px");
	    
	    Button bLimpar = new Button("Limpar");
	    bLimpar.setWidth("100px");
	    
	    Button bCancelar = new Button("Cancelar");
	    bCancelar.setWidth("100px");
	    
	    hPanelBotoes.add(bSalvar);
	    hPanelBotoes.add(bLimpar);
	    hPanelBotoes.add(bCancelar);
	    
	    bCancelar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				salvou = false;
				hide();
			}
		});
	    
	    bLimpar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				 tipo.setItemSelected(0, true);
				 descricao.setText("");
				 horario.setText("");
				 data.setText("");
			}
		});
	    
	    bSalvar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				salvou = true;
				finalizaCadastro();
			}
		});
	    
		return hPanelBotoes;
	}
	
	private void finalizaCadastro() {
		vCadastrar.clear();
		vCadastrar.add(new Label("Crime Cadastrado!"));
		
		vCadastrar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Button bOk = new Button("Ok");
		bOk.setWidth("100px");
		
		bOk.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
			}
		});
		
		vCadastrar.add(bOk);
	}
}


















