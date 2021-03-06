package ece.ing3.java.projet.database.sql.clauses;

import ece.ing3.java.projet.database.sql.enumerations.Order;

/**
 * Order By clause helper.
 * <p>
 * Provides a convenient way to build Order By clause, reactive-style.
 * Nesting is not allowed.
 * </p>
 * <br>
 * <p>
 * Order By clauses can be chained using {@link OrderBy#and(OrderBy)} or the shortcut method {@link OrderBy#and(String, Order)}, to avoid creating unnecessary Order By clauses.
 * </p>
 * <p>
 * OrderBy supports Reactive-style programming to create more compact code.<br>
 * As such,
 * </p>
 * <pre>
 * OrderBy o = new OrderBy( "col1", Order.DESC );
 * o.and( "col2", Order.ASC );
 * String v = o.toString();
 * </pre>
 * <p>
 * can be written
 * </p>
 * <pre>
 * String v = ( new OrderBy( "col1", Order.DESC ) ).and( "col2", Order.ASC ).toString();
 * </pre>
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class OrderBy {
	private String query;

	/**
	 * Creates a new chainable, empty Order By clause.
	 */
	public OrderBy() {
		query = "";
	}

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
		if( query.length() > 0 ) {
			query += ", ";
		}
		query += condition;
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
