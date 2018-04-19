package ece.ing3.java.projet.vue.components.inputs;

public class FloatInput extends NumericInput {
	private String columnName;

	public FloatInput( String columnName, boolean isSearch ) {
		super( isSearch );
		this.columnName = columnName;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public Object getValue() throws IllegalArgumentException {
		try {
			return Float.valueOf( getTextValue() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}
}
