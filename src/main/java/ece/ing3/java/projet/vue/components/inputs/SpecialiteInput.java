package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.enums.Specialite;

public class SpecialiteInput extends EnumInput<Specialite> {
	public SpecialiteInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	@Override
	protected Specialite[] getEnumValues() {
		return Specialite.values();
	}

	@Override
	protected Specialite getEnumValueOf( String value ) {
		return Specialite.valueOf( value );
	}
}
