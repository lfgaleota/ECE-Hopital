package ece.ing3.java.projet.vue.components.inputs;

import javax.swing.JTextField;

public class FloatInput extends JTextField implements BaseInput {
	private String columnName;

	public FloatInput( String columnName, boolean isSearch ) {
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
			return Float.valueOf( getText() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		setText( String.valueOf( value ) );
	}
}
