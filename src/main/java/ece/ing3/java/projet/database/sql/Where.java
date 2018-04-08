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

	/**
	 * Creates a new chainable Where clause.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 */
	public Where( String column, String comparator, Object value ) {
		query = column + " " + comparator + " ?";
		parameters.add( value );
	}

	/**
	 * Chain a new where clause using the boolean AND.
	 *
	 * @param condition Where clause to chain
	 * @return This Where clause helper
	 */
	public Where and( Where condition ) {
		query += " AND " + condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	/**
	 * Chain a new where clause using the boolean OR.
	 *
	 * @param condition Where clause to chain
	 * @return This Where clause helper
	 */
	public Where or( Where condition ) {
		query += " OR " + condition;
		parameters.addAll( condition.parameters );
		return this;
	}

	/**
	 * Chain a new where clause using the boolean AND, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This Where clause helper
	 */
	public Where and( String column, String comparator, Object value ) {
		return this.and( new Where( column, comparator, value ) );
	}

	/**
	 * Chain a new where clause using the boolean OR, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This Where clause helper
	 */
	public Where or( String column, String comparator, Object value ) {
		return this.or( new Where( column, comparator, value ) );
	}

	/**
	 * Gets the corresponding SQL Where clause, for use in a prepared statement.
	 *
	 * @return SQL Order By clause
	 */
	@Override
	public String toString() {
		return "(" + query + ")";
	}

	/**
	 * Gets the parameters to use in the prepared statement.
	 *
	 * @return Statement parameters
	 */
	public List<Object> getParameters() {
		return parameters;
	}
}
