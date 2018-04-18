package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.JTextField;

import ece.ing3.java.projet.enums.Rotation;

public class RotationInput extends JTextField implements BaseInput {
	private String columnName;

	public RotationInput( String columnName, boolean isSearch ) {
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
			return Rotation.valueOf( getText() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		//return getText();
		}
	}
}