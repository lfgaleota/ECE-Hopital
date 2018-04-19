package ece.ing3.java.projet.vue.dialogs.search;

import javax.swing.*;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

public abstract class ModelSearchDialog extends BaseModelInputDialog {
	private JButton reset;

	public ModelSearchDialog() {
		super();
		this.reset = new JButton( "Annuler La recherche" );
		bottom.add( reset );
		pack();
	}

	@Override
	public String getTitle() {
		return "Recherche";
	}

	@Override
	protected String getSubmitLabel() {
		return "Rechercher";
	}

	public Where getWhereClause() {
		Where where = new Where();
		for( BaseInput input : inputList.getInputs().values() ) {
			if( input.isFilled() ) {
				where.and( input.getWhere() );
			}
		}
		return where;
	}

	@Override
	public void validateInput() throws IllegalArgumentException {
		getWhereClause();
	}
}
