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
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelPrincipal extends Composite {

	private MapWidget mapa;
	private VerticalPanel panelPrincipal;
	private Label lbMesagens;
	private Marker localCrime;
	
	public PanelPrincipal() {
		// coisas do mapa
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
		
		
		panelPrincipal = new VerticalPanel();
		panelPrincipal.setSpacing(10);
		panelPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel panelOpcoesEMapa = new HorizontalPanel();
		panelOpcoesEMapa.setSpacing(10);
		
		DecoratorPanel panelMapa = new DecoratorPanel();
		panelMapa.add(mapa);		
				
		DecoratorPanel dPanelMenu = new DecoratorPanel(); 
		dPanelMenu.add(criaPanelMenu());

		DecoratorPanel dPanelOpcao = new DecoratorPanel(); 
		dPanelOpcao.add(criaPanelOpcao());
		
		lbMesagens = new Label("Bem vindo ao GeoCrime!");
		lbMesagens.setStyleName("labelAvisos");                             // colocar css nesse label!
		
		DecoratorPanel dPanelAvisos = new DecoratorPanel();
		dPanelAvisos.add(criaPanelAviso());
		
		panelOpcoesEMapa.add(dPanelOpcao);
		panelOpcoesEMapa.add(panelMapa);
		
		
		
		panelPrincipal.add(dPanelMenu);
		panelPrincipal.add(dPanelAvisos);
		panelPrincipal.add(panelOpcoesEMapa);
		
		initWidget(panelPrincipal);
	}

	
	private VerticalPanel criaPanelAviso() {
		VerticalPanel panelAviso = new VerticalPanel();
		panelAviso.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		panelAviso.add(lbMesagens);
		
		return panelAviso;
	}
	

	private HorizontalPanel criaPanelMenu() {
		HorizontalPanel hPanelMenu = new HorizontalPanel();
		hPanelMenu.setSpacing(20);
		hPanelMenu.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		
		Button bHome = new Button("Principal");
		bHome.setWidth("100px");
				
		Button bSobre = new Button("Sobre");
		bSobre.setWidth("100px");
		
		final DialogBox dialogSobre = createDialogBox("Sobre - GeoCrime", "Este eh o projeto de <b>SIG 2011.1</b>  :D");
		dialogSobre.setGlassEnabled(true);
		dialogSobre.setAnimationEnabled(true);
		
		bSobre.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				dialogSobre.center();
				dialogSobre.show();
			}
		});
		
		Button bEquipe = new Button("Equipe");
		bEquipe.setWidth("100px");
		
		final DialogBox dialogEquipe = createDialogBox("Equipe - GeoCrime", "<br />Andre Aranha<br /> Arnett Ruffino<br /> Erickson Filipe<br /> Jonathan Brilhante<br /> Luan Barbosa<br /><br />");
		dialogEquipe.setGlassEnabled(true);
		dialogEquipe.setAnimationEnabled(true);
		
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

		
		return hPanelMenu;
	}


	private DialogBox createDialogBox(String titulo, String html) {
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
		Button closeButton = new Button(
				"Voltar", new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
					}
				});
		closeButton.setWidth("100px");
		
		dialogContents.add(closeButton);
		
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
				
				mapa.addMapClickHandler(new MapClickHandler() {
				
				@Override
				public void onClick(MapClickEvent event) {
											
					LatLng ponto = event.getLatLng();
					localCrime = new Marker(ponto);
					mapa.addOverlay(localCrime);
					
//					Geocoder geo = new Geocoder();
//					geo.getLocations(ponto, new LocationCallback() {
//						
//						@Override
//						public void onSuccess(JsArray<Placemark> locations) {
//							localCrime = locations.get(0).getAddress();
//						}
//						
//						@Override
//						public void onFailure(int statusCode) {}
//					});
					
					PopupCadastrar popupCadastrar = new PopupCadastrar();
					popupCadastrar.center();
					popupCadastrar.show();
				}
				
			});
			
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
	
}