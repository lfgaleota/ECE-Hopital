package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.JTextField;

public class IntegerInput extends JTextField implements BaseInput {
	private String columnName;

	public IntegerInput( String columnName, boolean isSearch ) {
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
	public Object getValue() throws IllegalArgumentException {
		try {
			return Integer.valueOf( getText() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}
}