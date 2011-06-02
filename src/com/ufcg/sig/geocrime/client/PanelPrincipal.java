package com.ufcg.sig.geocrime.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelPrincipal extends Composite {

	private MapWidget mapa;
	private VerticalPanel panelPrincipal;
	private Label lbMesagens;
	private String enderecoCrime;
	private MapClickHandler 	clicaMapa;
	
	public PanelPrincipal() {
		
		criarEConfigurarMapa();
		
		panelPrincipal = new VerticalPanel();
		panelPrincipal.setSpacing(10);
		panelPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panelPrincipal.setWidth("100%");
		
		HorizontalPanel panelOpcoesEMapa = new HorizontalPanel();
		panelOpcoesEMapa.setSpacing(10);

		DecoratorPanel panelMapa = new DecoratorPanel();
		panelMapa.add(mapa);	
		
		lbMesagens = new Label("Bem vindo ao GeoCrime!");
		lbMesagens.setStyleName("labelAvisos");

		panelOpcoesEMapa.add(criaPanelOpcao());
		panelOpcoesEMapa.add(panelMapa);
		
		panelPrincipal.add(criaPanelMenu());
		panelPrincipal.add(criaPanelAviso());
		panelPrincipal.add(criaPanelConsultaEBairro());
		panelPrincipal.add(panelOpcoesEMapa);
		
		initWidget(panelPrincipal);
	}

	
	private void criarEConfigurarMapa() {
		mapa = new MapWidget(LatLng.newInstance(-7.231188, -35.886669), 13);
		mapa.setSize("900px", "600px");
		mapa.setCenter(LatLng.newInstance(-7.228633,-35.891991), 13);
		
		MapUIOptions options = mapa.getDefaultUI();
		options.setScrollwheel(true);
		options.setDoubleClick(false);
		options.setLargeMapControl3d(true);
		mapa.setUI(options);
		mapa.setDoubleClickZoom(false);
		mapa.setDraggable(true);
	}


	private HorizontalPanel criaPanelConsultaEBairro() {
		HorizontalPanel hPanelConsultaEBairro = new HorizontalPanel();
		
		hPanelConsultaEBairro.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanelConsultaEBairro.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
				
		CheckBox mostrarBairro = new CheckBox("Mostrar Bairros");
		mostrarBairro.setValue(true);

		hPanelConsultaEBairro.add(criaPanelConsultar());
		hPanelConsultaEBairro.add(mostrarBairro);
		return hPanelConsultaEBairro;
	}


	private HorizontalPanel criaPanelConsultar() {
		
		HorizontalPanel hPanelConsultar = new HorizontalPanel();
		hPanelConsultar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanelConsultar.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label labelPesquisar = new Label("Pesquisar:");
		final TextBox campoPesquisa = new TextBox();
		Button botaoPesquisar = new Button("Pesquisar");
		
		botaoPesquisar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				Geocoder geo = new Geocoder();
				geo.getLatLng(campoPesquisa.getText() + " - Campina Grande - Paraiba", new LatLngCallback() {
					
					@Override
					public void onSuccess(LatLng point) {
						mapa.setCenter(point, 17);				
					}
					
					@Override
					public void onFailure() {}
				});
			}
		});
		
		hPanelConsultar.add(labelPesquisar);
		hPanelConsultar.add(campoPesquisa);
		hPanelConsultar.add(botaoPesquisar);
		
		return hPanelConsultar;
	}


	private VerticalPanel criaPanelAviso() {
		VerticalPanel panelAviso = new VerticalPanel();
		panelAviso.setStyleName("avisos");
		panelAviso.add(lbMesagens);
		
		return panelAviso;
	}
	

	private HorizontalPanel criaPanelMenu() {
		HorizontalPanel hPanelMenu = new HorizontalPanel();
		
		Button bHome = new Button("Principal");
		bHome.setWidth("265px");
				
		Button bSobre = new Button("Sobre");
		bSobre.setWidth("265px");

		Button bEquipe = new Button("Equipe");
		bEquipe.setWidth("265px");
		
		Button bLogin = new Button("Area Restrita");
		bLogin.setWidth("265px");
		
		
		
		final DialogBox dialogSobre = criarDialogBox("Sobre - GeoCrime", "Este eh o projeto de <b>SIG 2011.1</b>  :D");
		dialogSobre.setGlassEnabled(true);
		dialogSobre.setAnimationEnabled(true);
		
		final DialogBox dialogEquipe = criarDialogBox("Equipe - GeoCrime", "<br />Andre Aranha<br /> Arnett Ruffino<br /> Erickson Filipe<br /> Jonathan Brilhante<br /> Luan Barbosa<br /><br />");
		dialogEquipe.setGlassEnabled(true);
		dialogEquipe.setAnimationEnabled(true);

		
		
		bLogin.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {

				final DialogBox dialogLogin = criarDialogLogin();
				dialogLogin.setGlassEnabled(true);
				dialogLogin.setAnimationEnabled(true);
				dialogLogin.center();
				dialogLogin.show();
				
			}
		});
		
		bSobre.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dialogSobre.center();
				dialogSobre.show();
			}
		});
		
		bEquipe.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dialogEquipe.center();
				dialogEquipe.show();
			}
		});
		
		hPanelMenu.add(bHome);
		hPanelMenu.add(bSobre);
		hPanelMenu.add(bEquipe);
		hPanelMenu.add(bLogin);

		
		return hPanelMenu;
	}


	private DialogBox criarDialogLogin() {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Login - Area Restrita");
		
		// Create a table to layout the content
		VerticalPanel vPanelComponentes = new VerticalPanel();
		vPanelComponentes.setSpacing(10);
		dialogBox.setWidget(vPanelComponentes);
		
		HorizontalPanel hPanelBotoes = new HorizontalPanel();
		hPanelBotoes.setSpacing(10);
		
		// Add a close button at the bottom of the dialog
		Button bCancelar = new Button(
				"Cancelar", new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
						dialogBox.removeFromParent();
					}
				});
		bCancelar.setWidth("100px");

		
		Label loginlb = new Label("Login:");
		final TextBox login = new TextBox();
		
		Label senhalb = new Label("Senha:");
		final PasswordTextBox senha = new PasswordTextBox();
		
		Button bLogar = new Button("Logar");
		bLogar.setWidth("100px");
		
		bLogar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if (login.getText().equals("") || senha.getText().equals("")) {
					dialogBox.setText("Ha campos vazios!!!!!");
				}
				else if (login.getText().equals("eu") && senha.getText().equals("123")){  // <------------- modificar para o BD
					dialogBox.setText("Logoooooooooooooou!!!!!");
					// abrir painel pra cadastro de viaturas e tal...
				}
			}
		});
		
		hPanelBotoes.add(bLogar);
		hPanelBotoes.add(bCancelar);
		
		
		vPanelComponentes.add(loginlb);
		vPanelComponentes.add(login);
		vPanelComponentes.add(senhalb);
		vPanelComponentes.add(senha);
		vPanelComponentes.add(hPanelBotoes);
		
		
		// Return the dialog box
		return dialogBox;
	}
	
	
	private DialogBox criarDialogBox(String titulo, String html) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText(titulo);
		
		// Create a table to layout the content
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(6);
		dialogBox.setWidget(dialogContents);
		
		// Add some text to the top of the dialog
		HTML details = new HTML(html);
		dialogContents.add(details);
		dialogContents.setCellHorizontalAlignment(
				details, HasHorizontalAlignment.ALIGN_CENTER);
				
		// Add a close button at the bottom of the dialog
		Button bFechar = new Button(
				"Voltar", new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
					}
				});
		bFechar.setWidth("100px");
		
		dialogContents.add(bFechar);
		
		// Return the dialog box
		return dialogBox;
	}
	
	
	private VerticalPanel criaPanelOpcao() {
	
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(10);
		vPanel.setWidth("150px");
		
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Button bCadastrar = new Button("Cadastrar Crime");
		bCadastrar.setWidth("130px");
		
		bCadastrar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				lbMesagens.setText("Marque no mapa onde ocorreu o crime!");
				
				clicaMapa = new MapClickHandler() {
					
					@Override
					public void onClick(MapClickEvent event) {

						final LatLng ponto = event.getLatLng();

						Geocoder geo = new Geocoder();
						
						geo.getLocations(ponto, new LocationCallback() {
							
							@Override
							public void onSuccess(JsArray<Placemark> locations) {
								
								enderecoCrime = locations.get(0).getAddress();
								
								PopupCadastrar popupCadastrar = PopupCadastrar.getInstance(enderecoCrime, ponto, mapa);
								popupCadastrar.mostrarTela();
							}
							
							@Override
							public void onFailure(int statusCode) {}
							
						});	
					
					}
				
				};
				
				mapa.addMapClickHandler(clicaMapa);
			
			}
			
		});
		
		
		vPanel.add(bCadastrar);
		
		Button consulta1bt = new Button("Consulta 1");
		consulta1bt.setWidth("130px");
		vPanel.add(consulta1bt);
		
		Button consulta2bt = new Button("Consulta 2");
		consulta2bt.setWidth("130px");
		vPanel.add(consulta2bt);
		
		Button consulta3bt = new Button("Consulta 3");
		consulta3bt.setWidth("130px");
		vPanel.add(consulta3bt);
		
		Button consulta4bt = new Button("Consulta 4");
		consulta4bt.setWidth("130px");
		vPanel.add(consulta4bt);
		
		Button consulta5bt = new Button("Consulta 5");
		consulta5bt.setWidth("130px");
		vPanel.add(consulta5bt);
		

		return vPanel;
	}

	
	public void consultarRua(String rua) {
		Geocoder geo = new Geocoder();
		
		geo.getLatLng(rua + " - Campina Grande - Paraiba", new LatLngCallback() {
			
			@Override
			public void onSuccess(LatLng point) {
				mapa.setCenter(point, 16);				
			}
			
			@Override
			public void onFailure() {				
			}
		});
	}
	
}





