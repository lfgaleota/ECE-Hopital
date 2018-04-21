package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.InfirmierPanelController;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

public class InfirmierInput extends SearchInput<Infirmier, Long> {
	public InfirmierInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	@Override
	protected ModelPanel<Infirmier> createController() {
		return new InfirmierPanelController().getPanel();
	}

	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	@Override
	protected Long getValueFromModel( Infirmier model ) {
		return model.getNumero();
	}

	@Override
	protected Infirmier createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Infirmier inf = new Infirmier();
		inf.setNumero( value );
		return inf;
	}
}
