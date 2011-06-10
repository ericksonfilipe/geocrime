package com.ufcg.sig.geocrime.client;


import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.google.gwt.user.datepicker.client.DateBox;

public class PopupCadastrar extends PopupPanel {
	
	private VerticalPanel vCadastrar;
	private ListBox tipo;
	private TextBox outro;
	private TextArea descricao;
	private TextBox hora;
	private TextBox minuto;
	private DateBox data;
	private TextBox localCrime;
	private MapWidget mapa;
	private MarkerLocalCrime marcador;
	
	
	private Label labelErro;
	private Label outroLabel;
	private Label tipoLabel;
	private Label descricaoLabel;
	private Label horarioLabel;
	private Label dataLabel;

	private boolean modificouLocal = false;
	private static PopupCadastrar popupInstance;
	
	
	
    public static PopupCadastrar getInstance(String endereco, LatLng ponto, MapWidget mapa){
          if(popupInstance == null) {
               popupInstance = new PopupCadastrar(endereco, ponto, mapa);
          }
                   
          return popupInstance;
    }
	
	private PopupCadastrar(String endereco, LatLng ponto, MapWidget map) {
		super(false);
		mapa = map;
		marcador = new MarkerLocalCrime(ponto);
		
		setGlassEnabled(true);
		setAnimationEnabled(true);
		
		vCadastrar = new VerticalPanel();
		vCadastrar.setSpacing(20);
		vCadastrar.setWidth("500px");
		
		add(vCadastrar);
	   
		Label tituloPopup = new Label("Cadastro de Crime");
		tituloPopup.setStyleName("tituloCadastro");
		
		labelErro = new Label();
		labelErro.setStyleName("erroCadastro");
		labelErro.setVisible(false);
		
		VerticalPanel paneTitulo = new VerticalPanel();
		paneTitulo.setWidth("500px");
		paneTitulo.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		paneTitulo.add(tituloPopup);
		paneTitulo.add(labelErro);
		
		vCadastrar.add(paneTitulo);
	    vCadastrar.add(criaPanelLocal(endereco));
	    vCadastrar.add(criaCorpoPopup());
	    vCadastrar.add(criaPanelBotoes());
	}
	
	
	private VerticalPanel criaCorpoPopup() {
		
		VerticalPanel vPanelCampos = new VerticalPanel();
		vPanelCampos.setSpacing(7);
		vPanelCampos.setWidth("490px");
		
		outroLabel = new Label("Outro:");
		outro = new TextBox();
		outro.setWidth("395px");
		outroLabel.setVisible(false);
		outro.setVisible(false);

		tipoLabel = new Label("Tipo:");
		tipo = new ListBox(false);

	    tipo.addItem("Assalto");
	    tipo.addItem("Atropelamento");
	    tipo.addItem("Furto");
	    tipo.addItem("Homicidio");
	    tipo.addItem("Roubo de Veiculo");
	    tipo.addItem("Uso/Venda de Drogas");
	    tipo.addItem("Vandalismo");
	    tipo.addItem("Outro...");

	    
	    tipo.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				
				if (tipo.getItemText(tipo.getSelectedIndex()).equals("Outro...")) {
					outro.setVisible(true);
					outroLabel.setVisible(true);
				}
				else {
					outro.setVisible(false);
					outroLabel.setVisible(false);
				}
				
			}
		});
	    
	    tipo.setVisibleItemCount(1);
		tipo.setWidth("400px");
		
		descricaoLabel = new Label("Descricao:");
		descricao = new TextArea();
		descricao.setWidth("395px");
		descricao.setHeight("100px");
		
		horarioLabel = new Label("Horario: (use apenas numeros)");
		
		
		HorizontalPanel hPanelHorario = new HorizontalPanel();
		hPanelHorario.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		hPanelHorario.setStyleName("panelHorario");
		hPanelHorario.setSpacing(7);
		Label horaLabel = new Label("Hora: ");
		hora = new TextBox();
		hora.setWidth("50px");
		Label minutoLabel = new Label("Minuto: ");
	    minuto = new TextBox();
	    minuto.setWidth("50px");
	    
	    hPanelHorario.add(horaLabel);
	    hPanelHorario.add(hora);
	    hPanelHorario.add(minutoLabel);
	    hPanelHorario.add(minuto);
	    
	    
	    
	    dataLabel = new Label("Data:");
	    data = new DateBox();
		
	    vPanelCampos.add(tipoLabel);
	    vPanelCampos.add(tipo);
	    vPanelCampos.add(outroLabel);
	    vPanelCampos.add(outro);
	    vPanelCampos.add(descricaoLabel);
	    vPanelCampos.add(descricao);
	    vPanelCampos.add(horarioLabel);
	    vPanelCampos.add(hPanelHorario);
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
		
		localCrime = new TextBox();
		localCrime.setText(endereco);
		localCrime.setEnabled(false);
		localCrime.setWidth("330px");
		
		Button botaoEditarLocal = new Button("Editar");
		botaoEditarLocal.setWidth("80px");
		botaoEditarLocal.setHeight("25px");
		
		botaoEditarLocal.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				localCrime.setEnabled(true);
				modificouLocal = true;
			}
		});
		
		hPanel.add(localLabel);
		hPanel.add(localCrime);
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
				 descricao.setText("");
				 hora.setText("");
				 minuto.setText("");
				 outro.setText("");
			}
		});
	    
	    bSalvar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if (validaDados()) {
					salvarDados();
				}
			}

		});
	    
		return hPanelBotoes;
	}

	
	private void salvarDados() {
		
		if (outro.isVisible()) {
			marcador.setDados(localCrime.getText(), outro.getText(), descricao.getText(), hora.getText() +":"+ minuto.getText(), data.getValue());
		}
		else {
			marcador.setDados(localCrime.getText(), tipo.getItemText(tipo.getSelectedIndex()), descricao.getText(), hora.getText() +":"+ minuto.getText(), data.getValue());
		}
		
		
		if (modificouLocal) {
			Geocoder geo = new Geocoder();
			
			geo.getLatLng(localCrime.getText(), new LatLngCallback() {
				
				@Override
				public void onSuccess(LatLng point) {
					marcador.setLatLng(point);
				}
				
				@Override
				public void onFailure() {}
			});
			
		}
		
		mapa.addOverlay(marcador);
		
		marcador.addMarkerClickHandler(new MarkerClickHandler() {
			
			@Override
			public void onClick(MarkerClickEvent event) {
				mapa.getInfoWindow().open(marcador, new InfoWindowContent(marcador.getHTML()));
			}
		});
		
		
		finalizaCadastro();

	}
	
	
	private boolean validaDados() {
		boolean valido = true;
		
		String outroStr = outro.getText().trim();
		String descricaoStr = descricao.getText().trim();
		String horaStr = hora.getText().trim();
		String minutoStr = minuto.getText().trim();
			
		
		if (outro.isVisible() && outroStr.equals("") || descricaoStr.equals("") 
		    || horaStr.equals("") || !somenteNumeros(horaStr) || !somenteNumeros(minutoStr) || data.getValue() == null) {

			valido = false;
			labelErro.setVisible(true);
			
			if (outro.isVisible() && outroStr.equals(""))
				outroLabel.setStyleName("campoErro");
			else
				outroLabel.removeStyleName("campoErro");
			
			if (descricaoStr.equals(""))
				descricaoLabel.setStyleName("campoErro");
			else 
				descricaoLabel.removeStyleName("campoErro");

			if (horaStr.equals("") || !somenteNumeros(horaStr) || !somenteNumeros(minutoStr))
				horarioLabel.setStyleName("campoErro");
			else if (somenteNumeros(horaStr))
				horarioLabel.removeStyleName("campoErro");
			
			
			if (data.getValue() == null)
				dataLabel.setStyleName("campoErro");
			else
				dataLabel.removeStyleName("campoErro");

		}	
		
		if (somenteNumeros(horaStr) && somenteNumeros(minutoStr)) {

			if (!horarioValido(hora.getText().trim(), minuto.getText().trim())) {
				valido = false;
				
				horarioLabel.setStyleName("campoErro");
			}
		}

		if (!valido) {
			labelErro.setText("Ha campos Incorretos");
			labelErro.setVisible(true);
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
	
	
	private boolean horarioValido(String hora, String minuto) {
		
		Integer horaCrime;
		Integer minutoCrime;
		
		if (hora.equals("") || minuto.equals("")) {
			return false;
		}
		
		horaCrime = Integer.parseInt(hora);
		minutoCrime = Integer.parseInt(minuto);
		
		if (( horaCrime >= 0 & horaCrime <= 23 ) && ( minutoCrime >= 0 & minutoCrime <= 59)) {
			return true;
		}
		
		return false;
	}
	
	
	private void finalizaCadastro() {
		vCadastrar.clear();
		
		Label avisoCasdastrado = new Label("Crime Cadastrado!");
		avisoCasdastrado.setStyleName("cadastrado");
		
		VerticalPanel paneCadastrado = new VerticalPanel();
		paneCadastrado.setWidth("500px");
		paneCadastrado.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		paneCadastrado.add(avisoCasdastrado);
		
		vCadastrar.add(paneCadastrado);
		
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
		double lat = marcador.getLatLng().getLatitude();
		double longi = marcador.getLatLng().getLongitude();
	}

	
	public void mostrarTela() {
		center();
		show();
	}
}


















