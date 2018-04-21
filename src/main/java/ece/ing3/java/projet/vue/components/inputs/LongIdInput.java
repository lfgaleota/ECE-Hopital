package ece.ing3.java.projet.vue.components.inputs;

public class LongIdInput extends LongInput {
	public LongIdInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	@Override
	public void setValue( Long value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( selector != null ) {
			selector.setEnabled( false );
		}
	}
}
