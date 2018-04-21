package ece.ing3.java.projet.vue.components.inputs;

public class LongInput extends NumericInput<Long> {
	private String columnName;

	public LongInput( String columnName, boolean isSearch ) {
		super( isSearch );
		this.columnName = columnName;
	}

	@Override
	public String getColumnName() {
		return columnName;
	}

	@Override
	public Long getValue() throws IllegalArgumentException {
		try {
			return Long.valueOf( getTextValue() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	@Override
	public Long[] getValues() throws IllegalArgumentException {
		return new Long[]{ getValue() };
	}

	@Override
	public void setRawValue( Object value ) throws IllegalArgumentException {
		try {
			setValue( Long.valueOf( String.valueOf( value ) ) );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}
}
