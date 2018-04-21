package ece.ing3.java.projet.vue.components.inputs;

public class IntegerInput extends NumericInput<Integer> {
	private String columnName;

	public IntegerInput( String columnName, boolean isSearch ) {
		super( isSearch );
		this.columnName = columnName;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public Integer getValue() throws IllegalArgumentException {
		try {
			return Integer.valueOf( getTextValue() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	@Override
	public Integer[] getValues() throws IllegalArgumentException {
		return new Integer[]{ getValue() };
	}

	@Override
	public void setRawValue( Object value ) throws IllegalArgumentException {
		try {
			setValue( Integer.valueOf( String.valueOf( value ) ) );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}
}
