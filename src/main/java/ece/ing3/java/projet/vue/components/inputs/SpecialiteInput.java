package ece.ing3.java.projet.vue.components.inputs;
import ece.ing3.java.projet.enums.Specialite;

import javax.swing.JTextField;

public class SpecialiteInput extends JTextField implements BaseInput {
	private String columnName;

	public SpecialiteInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public boolean isFilled() {
		return getText().length() > 0;
	}

	@Override
	public Object getValue() {
		try {
			return Specialite.valueOf( getText() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}		//return getText();
	}
}