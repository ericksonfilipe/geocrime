package com.ufcg.sig.geocrime.client;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PanelAreaRestrita extends Composite{
	
	private VerticalPanel panelPai;
	private VerticalPanel vPanelPrincipal;
	private VerticalPanel vPanelMenu;
	private VerticalPanel vPanelCadastros;
	
	private PanelAreaRestrita gambiVoltarParaPanelPrincipal;
	
	public PanelAreaRestrita(VerticalPanel panelPai) {
		
		gambiVoltarParaPanelPrincipal = this;
		
		this.panelPai = panelPai;
		
		
		vPanelCadastros = new VerticalPanel();
		criaPanelPadrao();
		
		vPanelMenu = new VerticalPanel();
		vPanelMenu.setSpacing(20);
		vPanelMenu.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanelMenu.setWidth("1200px");
		vPanelMenu.add(criaPanelMenu());
		
		vPanelPrincipal = new VerticalPanel();
		vPanelPrincipal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		vPanelPrincipal.add(vPanelMenu);
		vPanelPrincipal.add(vPanelCadastros);

		initWidget(vPanelPrincipal);
	}
	
	
	
	public void criaPanelPadrao() {
		vPanelCadastros.clear();
		
		VerticalPanel vPanelCadPadrao = new VerticalPanel();
		vPanelCadPadrao.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanelCadPadrao.setWidth("100%");
		
		vPanelCadPadrao.add(new Image("imgs/policial.jpg"));
		
		vPanelCadastros.add(vPanelCadPadrao);
	}
	
	
	private HorizontalPanel criaPanelMenu() {
		
		HorizontalPanel hPanelMenu = new HorizontalPanel();
		hPanelMenu.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		Button bHome = new Button("Principal");
		bHome.setWidth("200px");
		bHome.setHeight("26px");
		
		Button bCadViatura = new Button("Cadastrar Viatura");
		bCadViatura.setWidth("200px");
		bCadViatura.setHeight("26px");
		
		Button bCadDelegacia = new Button("Cadastrar Delegacia");
		bCadDelegacia.setWidth("200px");
		bCadDelegacia.setHeight("26px");
		
		Button bLogoff = new Button("Logoff");
		bLogoff.setWidth("200px");
		bLogoff.setHeight("26px");
				
		
		bHome.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vPanelCadastros.clear();
				criaPanelPadrao();
			}
		});
		
		
		bCadViatura.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vPanelCadastros.clear();
				vPanelCadastros.add(new PanelCadastraViatura(gambiVoltarParaPanelPrincipal));
			}
		});
		
		
		
		bCadDelegacia.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				vPanelCadastros.clear();
				vPanelCadastros.add(new PanelCadastraDelegacia(gambiVoltarParaPanelPrincipal));
			}
		});
		
		
		
		bLogoff.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				setVisible(false);
				panelPai.setVisible(true);
			}
		});
		
		
		hPanelMenu.add(bHome);
		hPanelMenu.add(bCadViatura);
		hPanelMenu.add(bCadDelegacia);
		hPanelMenu.add(bLogoff);
		
		
		return hPanelMenu;
	}
	
	
}














