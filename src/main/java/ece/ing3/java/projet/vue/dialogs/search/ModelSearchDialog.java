package ece.ing3.java.projet.vue.dialogs.search;

import javax.swing.*;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

import java.awt.event.ActionListener;

public abstract class ModelSearchDialog extends BaseModelInputDialog {
	private JButton reset;

	private boolean isResetFilters;

	public ModelSearchDialog() {
		super();
		this.isResetFilters = false;
		this.reset = new JButton( "Tout" );
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

	public JButton getReset() {
		return reset;
	}

	@Override
	public void addActionListener( ActionListener actionListener ) {
		super.addActionListener( actionListener );
		reset.addActionListener( actionListener );
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

	public boolean isResetFilters() {
		return isResetFilters;
	}

	public void setResetFilters( boolean resetFilters ) {
		isResetFilters = resetFilters;
	}
}
