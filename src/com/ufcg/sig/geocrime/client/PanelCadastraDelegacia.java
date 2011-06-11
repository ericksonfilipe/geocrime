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

public class PanelCadastraDelegacia extends Composite{

	private final GeoCrimeServiceAsync greetingService = GWT
	.create(GeoCrimeService.class);
	
	private PanelAreaRestrita panelPai;
	private VerticalPanel vPrincipal;
	private MapWidget mapa;
	private TextBox unidade;
	private TextBox delegado;
	private TextBox contingente;
	private TextBox numViaturas;
	private TextArea infoAdd;
	private MarkerDelegacia markPosicao = null;
	
	private Label erroLabel;
	private Label unidadeLb;
	private Label delegadoLb; 
	private Label contingenteLb;
	private Label numViaturasLb;
	private Label infoAddLb;

	
	public PanelCadastraDelegacia(PanelAreaRestrita panelPai) {
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
		
		Label tituloLb = new Label("Cadastrar Delegacia");
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
		hPanelBotoes.setSpacing(3);
		
		criaConfigMapa();
		vPanelMapa.add(mapa);
		
		mapa.addMapClickHandler(new MapClickHandler() {
			
			@Override
			public void onClick(MapClickEvent event) {
				
				if (markPosicao == null) {
					markPosicao = new MarkerDelegacia(event.getLatLng());
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
		
		unidadeLb = new Label("Unidade: (ex.: 12DP)");
		unidade = new TextBox();
		
		delegadoLb = new Label("Delegado:");
		delegado = new TextBox();
		
		contingenteLb = new Label("Contingente:");
		contingente = new TextBox();
		
		numViaturasLb = new Label("Numero de Viaturas:");
		numViaturas = new TextBox();
		
		infoAddLb = new Label("Informacoes Adicionais:");
		infoAdd = new TextArea();
		infoAdd.setWidth("100%");
		infoAdd.setHeight("50px");

		
		vPanel.add(unidadeLb);
		vPanel.add(unidade);
		vPanel.add(delegadoLb);
		vPanel.add(delegado);
		vPanel.add(contingenteLb);
		vPanel.add(contingente);
		vPanel.add(numViaturasLb);
		vPanel.add(numViaturas);
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
				if (validaDados())
					finalizarCadastro();
			}
		});
		
		bLimpar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				unidade.setText("");
				delegado.setText("");
				contingente.setText("");
				numViaturas.setText("");
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
		
		String unidadeStr = unidade.getText().trim();
		String delegadoStr = delegado.getText().trim();
		String contingenteStr = contingente.getText().trim();
		String numViaturasStr = numViaturas.getText().trim();
		
		if (unidadeStr.equals("") 
			|| delegadoStr.equals("") 
			|| contingenteStr.equals("") 
			|| !somenteNumeros(contingenteStr) 
			|| numViaturasStr.equals("")
			|| !somenteNumeros(numViaturasStr)
			|| markPosicao == null) {

			erroLabel.setText("Ha campos Incorretos");
			erroLabel.setVisible(true);
			valido = false;
			
			if (unidadeStr.equals(""))
				unidadeLb.setStyleName("campoErro");
			else
				unidadeLb.removeStyleName("campoErro");
			
			if (delegadoStr.equals(""))
				delegadoLb.setStyleName("campoErro");
			else 
				delegadoLb.removeStyleName("campoErro");
			
			if (contingenteStr.equals("") || !somenteNumeros(contingenteStr))
				contingenteLb.setStyleName("campoErro");
			else
				contingenteLb.removeStyleName("campoErro");
			
			if (numViaturasStr.equals("") || !somenteNumeros(numViaturasStr))
				numViaturasLb.setStyleName("campoErro");
			else
				numViaturasLb.removeStyleName("campoErro");
			
			if (markPosicao == null)
				mapa.setStyleName("mapaErro");
			else
				mapa.removeStyleName("mapaErro");
			
			
		}
		
		if (somenteNumeros(numViaturasStr)) {
			if (!numerosValidos(numViaturasStr)) {
				valido = false;
				
				numViaturasLb.setStyleName("campoErro");
			}
		}
		
		if (somenteNumeros(contingenteStr)) {
			if (!numerosValidos(contingenteStr)) {
				valido = false;
				
				contingenteLb.setStyleName("campoErro");
			}
		}
		
		
		
		return valido;
	}
	
	private boolean somenteNumeros(String dado) {
		for( int i = 0; i < dado.length(); i++ )  {
            if( Character.isDigit( dado.charAt( i ) ) == false) {
            	return false;
            }
		}

		return true;
	}
	
	private boolean numerosValidos(String umNumero) {
		
		Integer numero;
		
		if (umNumero.equals("")) {
			return false;
		}
		
		numero = Integer.parseInt(umNumero);
		
		if ( numero >= 0) {
			return true;
		}
		
		return false;
	}
	
	
	private void finalizarCadastro() {
		vPrincipal.clear();
		
		Label avisoCasdastrado = new Label("Delegacia Cadastrada!");
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
    	vPrincipal.add(bOk);
		greetingService.saveDelegacia(markPosicao.unidade,markPosicao.delegado,markPosicao.contingente,markPosicao.infoAdd,lat,longi,teste);
		
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















