package com.ufcg.sig.geocrime.client;

import com.google.gwt.maps.client.MapUIOptions;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.geom.LatLng;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelPrincipal extends Composite{

private MapWidget mapWidget;
	
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
		
		
		// painel do mapa
		VerticalPanel panelCompleto = new VerticalPanel();
		panelCompleto.setSpacing(10);
		panelCompleto.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		HorizontalPanel panelMapaEFiltros = new HorizontalPanel();
		panelMapaEFiltros.setSpacing(10);
		
		DecoratorPanel decoratorPanelMapa = new DecoratorPanel();
		decoratorPanelMapa.add(mapWidget);		
		
		panelMapaEFiltros.add(decoratorPanelMapa);
		
		panelCompleto.add(panelMapaEFiltros);
		
		initWidget(panelCompleto);
	}
	
}
