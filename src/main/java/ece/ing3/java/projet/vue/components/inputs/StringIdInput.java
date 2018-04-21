package ece.ing3.java.projet.vue.components.inputs;

public class StringIdInput extends StringInput {
	public StringIdInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	@Override
	public void setValue( String value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( selector != null ) {
			selector.setEnabled( false );
		}
	}
}
