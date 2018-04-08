package ece.ing3.java.projet.database.sql;

/**
 * Order By clause helper.
 * <p>
 * Provides a convenient way to build Order By clause, reactive-style.
 * Nesting is not allowed.
 */
public class OrderBy {
	private String query;

	/**
	 * Creates a new chainable Order By clause.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 */
	public OrderBy( String column, Order order ) {
		query = column + " " + order;
	}

	/**
	 * Chain another Order By condition.
	 *
	 * @param condition Condition to chain
	 * @return This Order By clause helper
	 */
	public OrderBy and( OrderBy condition ) {
		query += ", " + condition;
		return this;
	}

	/**
	 * Chain another Order By condition using the provided values.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 * @return This Order By clause helper
	 */
	public OrderBy and( String column, Order order ) {
		return this.and( new OrderBy( column, order ) );
	}

	/**
	 * Gets the corresponding SQL Order By clause.
	 *
	 * @return SQL Order By clause
	 */
	@Override
	public String toString() {
		return query;
	}
}
