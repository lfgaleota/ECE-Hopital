package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.enums.Rotation;

public class RotationInput extends EnumInput<Rotation> {
	public RotationInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	@Override
	protected Rotation[] getEnumValues() {
		return Rotation.values();
	}

	@Override
	protected Rotation getEnumValueOf( String value ) {
		return Rotation.valueOf( value );
	}
}
