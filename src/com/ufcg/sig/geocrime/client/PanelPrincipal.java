package com.ufcg.sig.geocrime.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelPrincipal extends Composite {

	private MapWidget mapWidget;
	private DecoratorPanel dPanel;
	private VerticalPanel panelPrincipal;
	
	public PanelPrincipal() {
		// coisas do mapa
		mapWidget = new MapWidget(LatLng.newInstance(-7.231188, -35.886669), 13);
		mapWidget.setSize("700px", "600px");
		
		MapUIOptions options = mapWidget.getDefaultUI();
		options.setScrollwheel(true);
		options.setDoubleClick(false);
		options.setLargeMapControl3d(true);
		mapWidget.setUI(options);
		mapWidget.setDoubleClickZoom(false);
		mapWidget.setDraggable(true);
		
		
		panelPrincipal = new VerticalPanel();
		panelPrincipal.setSpacing(10);
		panelPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel panelOpcoesEMapa = new HorizontalPanel();
		panelOpcoesEMapa.setSpacing(10);
		
		DecoratorPanel panelMapa = new DecoratorPanel();
		panelMapa.add(mapWidget);		
		
		
		dPanel = criaPanelOpcao();
		
		panelOpcoesEMapa.add(dPanel);
		panelOpcoesEMapa.add(panelMapa);
		
		panelPrincipal.add(criaDecoratorBanner());
		panelPrincipal.add(panelOpcoesEMapa);
		panelPrincipal.add(criaDecoratorRodape());
		
		initWidget(panelPrincipal);
	}


	private DecoratorPanel criaDecoratorRodape() {
		DecoratorPanel dPanel = new DecoratorPanel();		
		dPanel.add(new Label("Desenvolvido por: Andre Aranha, Arnett Ruffino, Erickson Filipe, Jonathan Brilhante e Luan Barbosa"));
		
		return dPanel;
	}
	
	
	private DecoratorPanel criaPanelOpcao() {
		DecoratorPanel panelOpcao = new DecoratorPanel();
		
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(10);
		
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		Button bCadastrar = new Button("Cadastrar Crime");
		
		bCadastrar.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				PopupCadastrar popupCadastrar = new PopupCadastrar();
				popupCadastrar.center();
				popupCadastrar.show();
			}
			
		});
		
		
		vPanel.add(bCadastrar);
		vPanel.add(new Button("Botao 2"));
		vPanel.add(new Button("Botao 3"));
		vPanel.add(new Button("Botao 4"));
		vPanel.add(new Button("Botao 5"));		
		
		panelOpcao.add(vPanel);

		return panelOpcao;
	}
	
	private VerticalPanel criaDecoratorBanner() {
		Image bannerPrincipal = new Image("http://lad.dsc.ufcg.edu.br/loac/uploads/OAC/UFCG_LOGO_pq.png");
		VerticalPanel panelBanner = new VerticalPanel();
		panelBanner.add(bannerPrincipal);
		return panelBanner;
	}
	
	
}
