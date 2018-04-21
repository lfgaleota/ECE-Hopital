package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

public interface BaseInput<T> {
	String getColumnName();

	T getValue() throws IllegalArgumentException;

	T[] getValues() throws IllegalArgumentException;

	void setValue( T value ) throws IllegalArgumentException;

	boolean isFilled();

	default Where getWhere() throws IllegalArgumentException {
		return new Where( getColumnName(), "=", getValue() );
	}

	void addValueChangeListener( ValueChangeListener valueChangeListener );
}
