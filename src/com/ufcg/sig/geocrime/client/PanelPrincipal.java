package com.ufcg.sig.geocrime.client;

import java.util.Date;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.maps.client.InfoWindowContent;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.event.MapClickHandler;
import com.google.gwt.maps.client.event.MarkerClickHandler;
import com.google.gwt.maps.client.geocode.DirectionQueryOptions;
import com.google.gwt.maps.client.geocode.DirectionResults;
import com.google.gwt.maps.client.geocode.Directions;
import com.google.gwt.maps.client.geocode.DirectionsCallback;
import com.google.gwt.maps.client.geocode.Geocoder;
import com.google.gwt.maps.client.geocode.LatLngCallback;
import com.google.gwt.maps.client.geocode.LocationCallback;
import com.google.gwt.maps.client.geocode.Placemark;
import com.google.gwt.maps.client.geocode.Waypoint;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ToggleButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.ufcg.sig.geocrime.shared.Crime;
import com.ufcg.sig.geocrime.shared.Delegacia;
import com.ufcg.sig.geocrime.shared.Viatura;

public class PanelPrincipal extends Composite {
	
	private GeoCrimeServiceAsync servidor = GWT.create(GeoCrimeService.class);

	private MapWidget mapa;
	private VerticalPanel panelPrincipal;
	private Label lbMesagens;
	private String enderecoCrime;
	private MapClickHandler clicaMapa;
	private ToggleButton bCadastrar;
	final CheckBox mostrarCrimes;
	final CheckBox mostrarDelegacia;
	final CheckBox mostrarViaturas;
	
	private ToggleButton bRotas;
	protected DirectionResults rota;
	
	public PanelPrincipal() {
		
		criarEConfigurarMapa();
//		carregarCrimes();
		
		panelPrincipal = new VerticalPanel();
		panelPrincipal.setSpacing(10);
		panelPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		panelPrincipal.setWidth("100%");
		
		HorizontalPanel panelOpcoesEMapa = new HorizontalPanel();
		panelOpcoesEMapa.setSpacing(10);

		DecoratorPanel panelMapa = new DecoratorPanel();
		VerticalPanel vPanelMapa = new VerticalPanel();
		
		HorizontalPanel hPanelOpcoes = new HorizontalPanel();
		hPanelOpcoes.setSpacing(5);
		hPanelOpcoes.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);;
		
		Label mostrarLb = new Label("Mostrar: ");
		mostrarCrimes = new CheckBox("Crimes");
		mostrarCrimes.setValue(true);
		
		mostrarDelegacia = new CheckBox("Delegacias");
		mostrarDelegacia.setValue(true);
		
		mostrarViaturas = new CheckBox("Viaturas");
		mostrarViaturas.setValue(true);
				
		ClickHandler handler = new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mapa.clearOverlays();
				if (mostrarCrimes.isChecked()) {
						servidor.getCrimes(new AsyncCallback<List<Crime>>() {
							
							@Override
							public void onSuccess(List<Crime> result) {
								for(Crime crime : result) {
									final MarkerLocalCrime marcador = new MarkerLocalCrime(LatLng.newInstance(crime.getLat(), crime.getLongi()));
									marcador.setDados("", crime.getTipo(), crime.getDescricao(), crime.getHorario(), new Date(crime.getData()));
									mapa.addOverlay(marcador);
									
									marcador.addMarkerClickHandler(new MarkerClickHandler() {
		
										@Override
										public void onClick(MarkerClickEvent event) {
											mapa.getInfoWindow().open(marcador,
													new InfoWindowContent(marcador.getHTML()));
										}
									});
						}
							}
						
						@Override
						public void onFailure(Throwable caught) {
						}
					});
				}
				if (mostrarDelegacia.isChecked()) {
					servidor.getDelegacias(new AsyncCallback<List<Delegacia>>() {
						
						@Override
						public void onSuccess(List<Delegacia> result) {
							for(Delegacia delegacia : result) {
								final MarkerDelegacia marcador = new MarkerDelegacia(LatLng.newInstance(delegacia.getLat(), delegacia.getlongi()));
								marcador.setDados(delegacia.getUnidade(), delegacia.getDelegado(), delegacia.getContingente(), 0, delegacia.getInfoadicionais());
								mapa.addOverlay(marcador);
								
								marcador.addMarkerClickHandler(new MarkerClickHandler() {

									@Override
									public void onClick(MarkerClickEvent event) {
										mapa.getInfoWindow().open(marcador,
												new InfoWindowContent(marcador.getHTML()));
									}
								});
							}
						}
						
						@Override
						public void onFailure(Throwable caught) {
						}
					});
				}
				
				if (mostrarViaturas.isChecked()) {
					servidor.getViaturas(new AsyncCallback<List<Viatura>>() {
						
						@Override
						public void onSuccess(List<Viatura> result) {
							for(Viatura viatura : result) {
								
								final MarkerViatura marcador = new MarkerViatura(LatLng.newInstance(viatura.getLat(), viatura.getlongi()));
								marcador.setDados(viatura.getId() + "", viatura.getId_radio(), viatura.getInfoadicionais());
								mapa.addOverlay(marcador);
								
								marcador.addMarkerClickHandler(new MarkerClickHandler() {

									@Override
									public void onClick(MarkerClickEvent event) {
										mapa.getInfoWindow().open(marcador, new InfoWindowContent(marcador.getHTML()));
									}
								});
							}
						}
						
						@Override
						public void onFailure(Throwable caught) {
						}
					});
				}
				
				
			}
		};
		
		mostrarDelegacia.addClickHandler(handler);
		mostrarViaturas.addClickHandler(handler);
		mostrarCrimes.addClickHandler(handler);
		
		hPanelOpcoes.add(mostrarLb);
		hPanelOpcoes.add(mostrarCrimes);
		hPanelOpcoes.add(mostrarDelegacia);
		hPanelOpcoes.add(mostrarViaturas);
		
		vPanelMapa.add(hPanelOpcoes);
		vPanelMapa.add(mapa);
		
		panelMapa.add(vPanelMapa);
		
		lbMesagens = new Label("Bem vindo ao GeoCrime!");

		panelOpcoesEMapa.add(criaPanelOpcao());
		panelOpcoesEMapa.add(panelMapa);
		
		panelPrincipal.add(criaPanelMenu());
		panelPrincipal.add(criaPanelAviso());
		panelPrincipal.add(criaPanelConsultar());
		panelPrincipal.add(panelOpcoesEMapa);
		
		initWidget(panelPrincipal);
		
	}

	private void criarEConfigurarMapa() {
		mapa = new MapWidget(LatLng.newInstance(-7.231188, -35.886669), 13);
		mapa.setSize("850px", "600px");
		mapa.setCenter(LatLng.newInstance(-7.228633,-35.891991), 13);
		
		MapUIOptions options = mapa.getDefaultUI();
		options.setScrollwheel(true);
		options.setDoubleClick(false);
		options.setLargeMapControl3d(true);
		mapa.setUI(options);
		mapa.setDoubleClickZoom(true);
		mapa.setDraggable(true);
		
		servidor.getCrimes(new AsyncCallback<List<Crime>>() {
			
			@Override
			public void onSuccess(List<Crime> result) {
				for(Crime crime : result) {
					final MarkerLocalCrime marcador = new MarkerLocalCrime(LatLng.newInstance(crime.getLat(), crime.getLongi()));
					marcador.setDados("", crime.getTipo(), crime.getDescricao(), crime.getHorario(), new Date(crime.getData()));
					mapa.addOverlay(marcador);
					
					marcador.addMarkerClickHandler(new MarkerClickHandler() {

						@Override
						public void onClick(MarkerClickEvent event) {
							mapa.getInfoWindow().open(marcador,
									new InfoWindowContent(marcador.getHTML()));
						}
					});
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
		servidor.getDelegacias(new AsyncCallback<List<Delegacia>>() {
			
			@Override
			public void onSuccess(List<Delegacia> result) {
				for(Delegacia delegacia : result) {
					final MarkerDelegacia marcador = new MarkerDelegacia(LatLng.newInstance(delegacia.getLat(), delegacia.getlongi()));
					marcador.setDados(delegacia.getUnidade(), delegacia.getDelegado(), delegacia.getContingente(), 0, delegacia.getInfoadicionais());
					mapa.addOverlay(marcador);
					
					marcador.addMarkerClickHandler(new MarkerClickHandler() {

						@Override
						public void onClick(MarkerClickEvent event) {
							mapa.getInfoWindow().open(marcador,
									new InfoWindowContent(marcador.getHTML()));
						}
					});
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
		
		servidor.getViaturas(new AsyncCallback<List<Viatura>>() {
			
			@Override
			public void onSuccess(List<Viatura> result) {
				for(Viatura viatura : result) {
					
					final MarkerViatura marcador = new MarkerViatura(LatLng.newInstance(viatura.getLat(), viatura.getlongi()));
					marcador.setDados(viatura.getId() + "", viatura.getId_radio(), viatura.getInfoadicionais());
					mapa.addOverlay(marcador);
					
					marcador.addMarkerClickHandler(new MarkerClickHandler() {

						@Override
						public void onClick(MarkerClickEvent event) {
							mapa.getInfoWindow().open(marcador, new InfoWindowContent(marcador.getHTML()));
						}
					});
				}
			}
			
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}


	private HorizontalPanel criaPanelConsultar() {
		
		HorizontalPanel hPanelConsultar = new HorizontalPanel();
		hPanelConsultar.setWidth("680px");
		hPanelConsultar.setSpacing(7);
		hPanelConsultar.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		hPanelConsultar.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Label labelPesquisar = new Label("Pesquisar:");
		final TextBox campoPesquisa = new TextBox();
		campoPesquisa.setWidth("500px");
		Button botaoPesquisar = new Button("Pesquisar");
		
		
		campoPesquisa.addKeyPressHandler(new KeyPressHandler() {
			
			@Override
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER) {
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
			}
		});
		
		
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
		
		Button bHome = new Button("Principal");              // esse botao deve recarregar o mapa e as informacoes do bd?
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
		
		VerticalPanel vPanelComponentes = new VerticalPanel();
		vPanelComponentes.setSpacing(10);
		dialogBox.setWidget(vPanelComponentes);
		
		HorizontalPanel hPanelBotoes = new HorizontalPanel();
		hPanelBotoes.setSpacing(10);
		
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
				if (login.getText().equals("") || senha.getText().equals("")) {            // <------------ descomentar para ativar senha
					dialogBox.setText("Ha campos vazios!!!!!");
				}
				else if (login.getText().equals("admin") && senha.getText().equals("sig")){  // <------------- modificar para o BD
					dialogBox.hide();
					RootPanel.get().add(new PanelAreaRestrita(panelPrincipal));
					setVisible(false);
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
		
		
		return dialogBox;
	}
	
	
	private DialogBox criarDialogBox(String titulo, String html) {
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText(titulo);
		
		VerticalPanel dialogContents = new VerticalPanel();
		dialogContents.setSpacing(6);
		dialogBox.setWidget(dialogContents);
		
		HTML details = new HTML(html);
		dialogContents.add(details);
		dialogContents.setCellHorizontalAlignment(
				details, HasHorizontalAlignment.ALIGN_CENTER);
				
		Button bFechar = new Button(
				"Voltar", new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
					}
				});
		bFechar.setWidth("100px");
		
		dialogContents.add(bFechar);
		
		return dialogBox;
	}
	
	
	private VerticalPanel criaPanelOpcao() {
	
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(10);
		vPanel.setWidth("150px");
		
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		bCadastrar = new ToggleButton("Cadastrar Crime [Desabilitado]", "Cadastrar Crime [Habilitado]");
		bCadastrar.setWidth("130px");
		
		
		bCadastrar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				if (bCadastrar.isDown()) {
					
					mapa.setDoubleClickZoom(false);
					lbMesagens.setText("-->  Marque no mapa onde ocorreu o crime!  <--");
					
					clicaMapa = new MapClickHandler() {
						
						@Override
						public void onClick(MapClickEvent event) {
							
							final LatLng ponto = event.getLatLng();
							
							Geocoder geo = new Geocoder();
							
							
							if (ponto != null) {
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
							
						}
						
					};
					
					mapa.addMapClickHandler(clicaMapa);
					
				}
				else {
					lbMesagens.setText("Bem vindo ao GeoCrimes!");
					
					mapa.setDoubleClickZoom(true);
					mapa.removeMapClickHandler(clicaMapa);
				}
					
			}
				
			
		});
		
		
		vPanel.add(bCadastrar);

		Button consulta2bt = new Button("Crimes de Fevereiro de 2011");
		consulta2bt.setWidth("152px");
		consulta2bt.setHeight("42px");
		consulta2bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mapa.clearOverlays();
				mostrarCrimes.setValue(false);
				mostrarDelegacia.setValue(false);
				mostrarViaturas.setValue(false);
				servidor.consulta2(new AsyncCallback<List<Crime>>() {

					@Override
					public void onFailure(Throwable caught) {
					}

					@Override
					public void onSuccess(List<Crime> result) {
						for(Crime crime : result) {
							final MarkerLocalCrime marcador = new MarkerLocalCrime(LatLng.newInstance(crime.getLat(), crime.getLongi()));
							marcador.setDados("", crime.getTipo(), crime.getDescricao(), crime.getHorario(), new Date(crime.getData()));
							mapa.addOverlay(marcador);

							marcador.addMarkerClickHandler(new MarkerClickHandler() {

								@Override
								public void onClick(MarkerClickEvent event) {
									mapa.getInfoWindow().open(marcador,
											new InfoWindowContent(marcador.getHTML()));
								}
							});
						}
					}
				});
				
			}
		});
		vPanel.add(consulta2bt);
		Button consulta3bt = new Button("Delegacia com mais criminalidade(raio 1km)");
		consulta3bt.setWidth("152px");
		consulta3bt.setHeight("42px");
		consulta3bt.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				mapa.clearOverlays();
				mostrarCrimes.setValue(false);
				mostrarDelegacia.setValue(false);
				mostrarViaturas.setValue(false);
				servidor.consulta6(new AsyncCallback<List<Delegacia>>() {
					
					@Override
					public void onSuccess(List<Delegacia> result) {
						if (!result.isEmpty()) {
							Delegacia delegacia = result.get(0);
							final MarkerDelegacia marcador = new MarkerDelegacia(LatLng.newInstance(result.get(0).getLat(), result.get(0).getlongi()));
							marcador.setDados(delegacia.getUnidade(), delegacia.getDelegado(), delegacia.getContingente(), 0, delegacia.getInfoadicionais());
							mapa.addOverlay(marcador);
							
							marcador.addMarkerClickHandler(new MarkerClickHandler() {

								@Override
								public void onClick(MarkerClickEvent event) {
									mapa.getInfoWindow().open(marcador,
											new InfoWindowContent(marcador.getHTML()));
								}
							});
						}
					}
					
					@Override
					public void onFailure(Throwable caught) {}
				});
			}
		});
		vPanel.add(consulta3bt);

		bRotas = new ToggleButton("Rota de delegacia para crime",
				"Rota de delegacia para crime");
		bRotas.setWidth("152px");
		bRotas.setHeight("42px");
		bRotas.addClickHandler(new ClickHandler() {

			private MapClickHandler listenerRotas;

			@Override
			public void onClick(ClickEvent event) {
				if (rota != null) {
					mapa.removeOverlay(rota.getPolyline());
					mapa.removeOverlay(rota.getMarkers().get(0));
					mapa.removeOverlay(rota.getMarkers().get(1));
				}
				if (bRotas.isDown()) {
					listenerRotas = new MapClickHandler() {

						private Waypoint[] pontosDaRota = new Waypoint[2];
						private int contador = 0;

						@Override
						public void onClick(MapClickEvent event) {
							
							pontosDaRota[contador] = new Waypoint(event
									.getLatLng());
							if (contador == 1) {
								rota = carregarRota(pontosDaRota, listenerRotas);
							}
							contador++;
						}
					};
					mapa.addMapClickHandler(listenerRotas);
				} else {
					lbMesagens.setText("");

					mapa.setDoubleClickZoom(true);
					mapa.removeMapClickHandler(listenerRotas);
				}
			}
		});
		vPanel.add(bRotas);

		return vPanel;
	}


	private DirectionResults carregarRota(Waypoint[] pontosDaRota, MapClickHandler listenerRotas) {
		DirectionQueryOptions dqo = new DirectionQueryOptions(
				mapa);
		dqo.setRetrievePolyline(true);
		Directions.loadFromWaypoints(pontosDaRota, dqo,
				new DirectionsCallback() {

					@Override
					public void onSuccess(
							DirectionResults result) {
						rota = result;
						mapa.addOverlay(rota.getPolyline());
						bRotas.setDown(false);
					}

					@Override
					public void onFailure(int statusCode) {
					}
				});
		mapa.removeMapClickHandler(listenerRotas);
		return rota;
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





