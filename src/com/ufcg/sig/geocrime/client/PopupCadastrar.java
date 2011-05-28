package com.ufcg.sig.geocrime.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
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
	private MapWidget mapa;
	private Marker marcador;
	
	private boolean modificouLocal = false;
	
	private static PopupCadastrar popupInstance;
	 
    public static PopupCadastrar getInstance(String endereco, Marker marcador, MapWidget mapa){
          if(popupInstance == null) {
               popupInstance = new PopupCadastrar(endereco, marcador, mapa);
          }
                   
          return popupInstance;
    }
	
	private PopupCadastrar(String endereco, Marker localMk, MapWidget map) {
		super(false);
		mapa = map;
		marcador = localMk;
		
		setGlassEnabled(true);
		setAnimationEnabled(true);
		setTitle("Cadastrar Ocorrencia de Crime");
		
		vCadastrar = new VerticalPanel();
		vCadastrar.setSpacing(20);
		vCadastrar.setWidth("500px");
		
		add(vCadastrar);
	   
	    vCadastrar.add(criaPanelLocal(endereco));
	    vCadastrar.add(criaCorpoPopup());
	    vCadastrar.add(criaPanelBotoes());
	}
	
	private VerticalPanel criaCorpoPopup() {
		
		VerticalPanel vPanelCampos = new VerticalPanel();
		vPanelCampos.setSpacing(7);
		vPanelCampos.setWidth("490px");
		
		Label tipoLabel = new Label("Tipo:");
		tipo = new ListBox(false);

	    
	    tipo.addItem("Assalto");
	    tipo.addItem("Atropelamento");
	    tipo.addItem("Furto");
	    tipo.addItem("Homicidio");
	    tipo.addItem("Roubo de Veiculo");
	    tipo.addItem("Uso/Venda de Drogas");
	    tipo.addItem("Vandalismo");
	    tipo.addItem("Outro");

	    tipo.setVisibleItemCount(1);
		tipo.setWidth("400px");
		
		Label descricaoLabel = new Label("Descricao:");
		descricao = new TextArea();
		descricao.setWidth("395px");
		descricao.setHeight("100px");
		
		Label horarioLabel = new Label("Horario:");
	    horario = new TextBox();
	    
	    Label dataLabel = new Label("Data:");
	    data = new TextBox();
		
	    vPanelCampos.add(tipoLabel);
	    vPanelCampos.add(tipo);
	    vPanelCampos.add(descricaoLabel);
	    vPanelCampos.add(descricao);
	    vPanelCampos.add(horarioLabel);
	    vPanelCampos.add(horario);
	    vPanelCampos.add(dataLabel);
	    vPanelCampos.add(data);
	    
	    
		return vPanelCampos;
	}



	private HorizontalPanel criaPanelLocal(String endereco) {
		
		HorizontalPanel hPanel = new HorizontalPanel();
		hPanel.setSpacing(10);
		hPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanel.setWidth("450px");
		hPanel.setStyleName("localCrime");
				
		Label localLabel = new Label("Local:");
		
		local = new TextBox();
		local.setText(endereco);
		local.setEnabled(false);
		local.setWidth("330px");
		
		Button botaoEditarLocal = new Button("Editar");
		botaoEditarLocal.setWidth("80px");
		botaoEditarLocal.setHeight("25px");
		
		botaoEditarLocal.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				local.setEnabled(true);
				modificouLocal = true;
			}
		});
		
		hPanel.add(localLabel);
		hPanel.add(local);
		hPanel.add(botaoEditarLocal);
		
		return hPanel;
	}



	private HorizontalPanel criaPanelBotoes() {
		HorizontalPanel hPanelBotoes = new HorizontalPanel();
		hPanelBotoes.setSpacing(7);
		hPanelBotoes.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hPanelBotoes.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelBotoes.setWidth("490px");
		hPanelBotoes.setStyleName("botoesCadastrar");
	    
	    Button bSalvar = new Button("Salvar");
	    bSalvar.setWidth("150px");
	    bSalvar.setHeight("25px");
	    
	    Button bLimpar = new Button("Limpar");
	    bLimpar.setWidth("150px");
	    bLimpar.setHeight("25px");
	    
	    Button bCancelar = new Button("Cancelar");
	    bCancelar.setWidth("150px");
	    bCancelar.setHeight("25px");
	    
	    hPanelBotoes.add(bSalvar);
	    hPanelBotoes.add(bLimpar);
	    hPanelBotoes.add(bCancelar);
	    
	    bCancelar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				hide();
				popupInstance = null;
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
				if (modificouLocal) {
					Geocoder geo = new Geocoder();
					
					geo.getLatLng(local.getText(), new LatLngCallback() {
						
						@Override
						public void onSuccess(LatLng point) {
							marcador.setLatLng(point);
						}
						
						@Override
						public void onFailure() {}
					});
					
				}
					
				mapa.addOverlay(marcador);
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
				popupInstance = null;
			}
		});
		
		vCadastrar.add(bOk);
	}

	public void mostrarTela() {
		center();
		show();
	}
}


















