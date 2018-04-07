package ece.ing3.java.projet.database.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Where clause helper.
 * <p>
 * Provides a convenient way to build Where clause, reactive-style.
 * Supports where clause nesting.
 */
public class Where {
	private String query;
	private List<Object> parameters = new ArrayList<>();

	public Where( String column, String comparator, Object value ) {
		query = column + " " + comparator + " ?";
		parameters.add( value );
	}

	public Where and( Where condition ) {
		query += " AND " + condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	public Where or( Where condition ) {
		query += " OR " + condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	public Where and( String column, String comparator, Object value ) {
		return this.and( new Where( column, comparator, value ) );
	}

	public Where or( String column, String comparator, Object value ) {
		return this.or( new Where( column, comparator, value ) );
	}

	@Override
	public String toString() {
		return "(" + query + ")";
	}

	public List<Object> getParameters() {
		return parameters;
	}
}
