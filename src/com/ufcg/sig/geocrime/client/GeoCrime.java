package com.ufcg.sig.geocrime.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Tela Inicial
 */
public class GeoCrime implements EntryPoint {
	
	private final GeoCrimeServiceAsync greetingService = GWT
	.create(GeoCrimeService.class);
	
	public void onModuleLoad() {
		RootPanel.get().add(new PanelPrincipal());
	}
}
