package ece.ing3.java.projet.vue.components.inputs;

public class LongIdInput extends LongInput implements BaseInput {
	public LongIdInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	@Override
	public void setValue( Object value ) throws IllegalArgumentException {
		super.setValue( value );
		setEnabled( false );
	}
}
