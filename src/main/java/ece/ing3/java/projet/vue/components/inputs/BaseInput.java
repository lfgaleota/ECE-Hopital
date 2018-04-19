package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;

public interface BaseInput {
	String getColumnName();

	Object getValue() throws IllegalArgumentException;

	void setValue( Object value ) throws IllegalArgumentException;

	boolean isFilled();

	default Where getWhere() throws IllegalArgumentException {
		return new Where( getColumnName(), "=", getValue() );
	}
}
