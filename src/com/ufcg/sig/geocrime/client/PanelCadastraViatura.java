package com.ufcg.sig.geocrime.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

	private final GeoCrimeServiceAsync greetingService = GWT
	.create(GeoCrimeService.class);
	
	private VerticalPanel vPrincipal;
	private MapWidget mapa;
	private TextBox radio;
	private TextBox identificador;
	private TextArea infoAdd;
	private MarkerViatura markPosicao;
	
	private Label tituloLb;
	private Label identificadorLb;
	private Label radioLb; 
	private Label infoAddLb;
	private Label erroLabel;
	
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
		
		tituloLb = new Label("Cadastrar Viatura");
		tituloLb.setStyleName("tituloCadastro");

		erroLabel = new Label();
		erroLabel.setVisible(false);
		
		VerticalPanel vPanelTitulo = new VerticalPanel();
		vPanelTitulo.setWidth("800px");
		vPanelTitulo.setHorizontalAlignment(HorizontalPanel.ALIGN_CENTER);
		vPanelTitulo.add(tituloLb);
		vPanelTitulo.add(erroLabel);
		
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
//					Icon icon = Icon.newInstance("imgs/viatura.jpg");
//					icon.setIconSize(Size.newInstance(15, 23));
//
//					MarkerOptions opcoes = MarkerOptions.newInstance();
//					opcoes.setIcon(icon);
//					markPosicao = new Marker(event.getLatLng(), opcoes);

					
					
					markPosicao = new MarkerViatura(event.getLatLng());
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
		
		identificadorLb = new Label("Identificador:");
		identificador = new TextBox();
		
		radioLb = new Label("ID Radio:");
		radio = new TextBox();
		
		infoAddLb = new Label("Informacoes Adicionais:");
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
				if (validaDados()) {
					finalizarCadastro();
				}
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

	
	private boolean validaDados() {
		boolean valido = true;
		
		String identificadorStr = identificador.getText().trim();
		String radioStr = radio.getText().trim();
		
		if (identificadorStr.equals("") || radioStr.equals("")|| markPosicao == null) {

			erroLabel.setText("Ha campos Incorretos");
			erroLabel.setVisible(true);
			valido = false;
			
			if (identificadorStr.equals(""))
				identificadorLb.setStyleName("campoErro");
			else
				identificadorLb.removeStyleName("campoErro");
			
			if (radioStr.equals(""))
				radioLb.setStyleName("campoErro");
			else 
				radioLb.removeStyleName("campoErro");
			
			if (markPosicao == null)
				mapa.setStyleName("mapaErro");
			else
				mapa.removeStyleName("mapaErro");
			
		}	
		
		return valido;
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
		double lat = markPosicao.getLatLng().getLatitude();
		double longi = markPosicao.getLatLng().getLongitude();
		AsyncCallback<Void> teste = new AsyncCallback<Void>() {
			 
            @Override
            public void onFailure(Throwable caught) {
                    System.out.println(caught.getMessage());
            }

            @Override
            public void onSuccess(Void result) {
                  	
            }

    };
		greetingService.saveViatura(Integer.valueOf(markPosicao.identificador),markPosicao.idRadio,markPosicao.infoAdd,lat,longi,teste);
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
