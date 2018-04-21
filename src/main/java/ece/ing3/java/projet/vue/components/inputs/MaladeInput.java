package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.panels.MaladePanelController;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

public class MaladeInput extends SearchInput<Malade, Long> {
	public MaladeInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	@Override
	protected ModelPanel<Malade> createController() {
		return new MaladePanelController().getPanel();
	}

	@Override
	protected Long[] getEmptyArray() {
		return new Long[ 0 ];
	}

	@Override
	protected Long getValueFromModel( Malade model ) {
		return model.getNumero();
	}

	@Override
	protected Malade createInstanceFromValue( Long value ) throws IllegalArgumentException {
		Malade inf = new Malade();
		inf.setNumero( value );
		return inf;
	}
}
