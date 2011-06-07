package com.ufcg.sig.geocrime.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.maps.client.overlay.Marker;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelCadastraViatura extends Composite {

	private VerticalPanel vPrincipal;
	private MapWidget mapa;
	private TextBox radio;
	private TextBox identificador;
	private TextArea infoAdd;
	private Marker markPosicao;
	
	private PanelAreaRestrita panelPai;
	
	public PanelCadastraViatura(PanelAreaRestrita panelPai) {
		
		this.panelPai = panelPai;
		
		vPrincipal = new VerticalPanel();
		vPrincipal.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		
		DecoratorPanel decPanelCadastro = new DecoratorPanel();
		decPanelCadastro.add(criaPanelCadastro());
		
		vPrincipal.add(decPanelCadastro);
		
		initWidget(vPrincipal);
	}

	
	private VerticalPanel criaPanelCadastro() {

		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setWidth("100%");
		vPanel.setSpacing(10);
		
		Label tituloLb = new Label("Cadastrar Viatura");
		tituloLb.setStyleName("tituloCadastro");
		VerticalPanel vPanelTitulo = new VerticalPanel();
		vPanelTitulo.setWidth("800px");
		vPanelTitulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vPanelTitulo.add(tituloLb);
		
		VerticalPanel vPanelCampos = new VerticalPanel();
		vPanelCampos.setSpacing(5);
		
		VerticalPanel vPanelMapa = new VerticalPanel();
		HorizontalPanel hPanelFormMapa = new HorizontalPanel();
		hPanelFormMapa.setSpacing(1);
		
		HorizontalPanel hPanelBotoes = new HorizontalPanel();
		hPanelBotoes.setSpacing(1);
		
		criaConfigMapa();
		vPanelMapa.add(mapa);
		
		mapa.addMapClickHandler(new MapClickHandler() {
			
			@Override
			public void onClick(MapClickEvent event) {
				
				if (markPosicao == null) {
					
					
// Personalizar Icone    <--------------
//					
//					Icon icon = Icon.newInstance("imgs/xx.ico");
//					icon.setIconSize(Size.newInstance(15, 23));
//
//					MarkerOptions opcoes = MarkerOptions.newInstance();
//					opcoes.setIcon(icon);
//					markPosicao = new Marker(event.getLatLng(), opcoes);

					
					
					markPosicao = new Marker(event.getLatLng());
					mapa.addOverlay(markPosicao);
				}
				else if (event.getLatLng() != null) {
					markPosicao.setLatLng(event.getLatLng());
				}
			}
		});
		
		vPanelCampos.add(criaPanelCampos());
		
		hPanelFormMapa.add(vPanelCampos);
		hPanelFormMapa.add(vPanelMapa);
		
		hPanelBotoes.add(criaPainelBotoes());
		
		vPanel.add(vPanelTitulo);
		vPanel.add(hPanelFormMapa);
		vPanel.add(hPanelBotoes);

		
		return vPanel;
	}
	
	
	private VerticalPanel criaPanelCampos() {
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(10);
		
		Label identificadorLb = new Label("Identificador:");
		identificador = new TextBox();
		
		Label radioLb = new Label("ID Radio:");
		radio = new TextBox();
		
		Label infoAddLb = new Label("Informacoes Adicionais:");
		infoAdd = new TextArea();
		infoAdd.setWidth("100%");
		infoAdd.setHeight("175px");
		
		
		vPanel.add(identificadorLb);
		vPanel.add(identificador);
		vPanel.add(radioLb);
		vPanel.add(radio);
		vPanel.add(infoAddLb);
		vPanel.add(infoAdd);
		
		return vPanel;
	}


	private HorizontalPanel criaPainelBotoes() {
		
		HorizontalPanel hPanel = new HorizontalPanel();
		
		Button bSalvar = new Button("Salvar");
		bSalvar.setWidth("250px");
		Button bLimpar = new Button("Limpar");
		bLimpar.setWidth("250px");
		Button bCancelar = new Button("Cancelar");
		bCancelar.setWidth("250px");
		
		hPanel.add(bSalvar);
		hPanel.add(bLimpar);
		hPanel.add(bCancelar);
		
		
		bSalvar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				finalizarCadastro();
			}
		});
		
		bLimpar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				identificador.setText("");
				radio.setText("");
				infoAdd.setText("");
			}
		});
		
		bCancelar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
				panelPai.criaPanelPadrao();
			}
		});
		
		return hPanel;
	}


	private void finalizarCadastro() {
		vPrincipal.clear();
		
		Label avisoCasdastrado = new Label("Viatura Cadastrada!");
		avisoCasdastrado.setStyleName("cadastrado");
		
		VerticalPanel paneCadastrado = new VerticalPanel();
		paneCadastrado.setWidth("500px");
		paneCadastrado.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		paneCadastrado.add(avisoCasdastrado);
		
		vPrincipal.add(paneCadastrado);
		
		vPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Button bOk = new Button("Ok");
		bOk.setWidth("100px");
		
		bOk.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
				panelPai.criaPanelPadrao();
			}
		});
		
		vPrincipal.add(bOk);
	}
	
	
	private void criaConfigMapa() {
		mapa = new MapWidget(LatLng.newInstance(-7.231188, -35.886669), 13);
		mapa.setSize("560px", "370px");
		mapa.setCenter(LatLng.newInstance(-7.228633,-35.891991), 13);
		
		MapUIOptions options = mapa.getDefaultUI();
		options.setScrollwheel(true);
		options.setDoubleClick(false);
		options.setLargeMapControl3d(true);
		mapa.setUI(options);
		mapa.setDoubleClickZoom(false);
		mapa.setDraggable(true);
	}
	
}
