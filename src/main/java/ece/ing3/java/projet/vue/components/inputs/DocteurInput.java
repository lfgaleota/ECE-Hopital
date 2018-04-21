package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.DocteurPanelController;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

public class DocteurInput extends SearchInput<Docteur, Long> {
	public DocteurInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	@Override
	protected ModelPanel<Docteur> createController() {
		return new DocteurPanelController().getPanel();
	}

	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	@Override
	protected Long getValueFromModel( Docteur model ) {
		return model.getNumero();
	}

	@Override
	protected Docteur createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Docteur dct = new Docteur();
		dct.setNumero( value );
		return dct;
	}
}
