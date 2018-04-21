package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.ServicePanelController;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

public class ServiceInput extends SearchInput<Service, String> {
	public ServiceInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	@Override
	protected ModelPanel<Service> createController() {
		return new ServicePanelController().getPanel();
	}

	@Override
	protected String[] getEmptyArray() {
		return new String[ 0 ];
	}

	@Override
	protected String getValueFromModel( Service model ) {
		return model.getCode();
	}

	@Override
	protected Service createInstanceFromValue( String value ) throws IllegalArgumentException {
		Service srv = new Service();
		srv.setCode( String.valueOf( value ) );
		return srv;
	}
}
