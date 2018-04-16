package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

/**
 * SQL helper for queries supporting Where clause.
 * Must be used as a base class for a feature-complete SQL helper.
 *
 * @param <T> SQL helper class
 */
abstract class SQLWhereQuery<T> implements SQLRequest {
	Where where = null;

	/**
	 * Init a new where clause.
	 *
	 * @param condition Where clause to use
	 * @return This SQL select helper
	 */
	@SuppressWarnings( "unchecked" )
	public T where( Where condition ) {
		where = condition;
		return (T) this;
	}

	/**
	 * Chain a new Where clause using the boolean AND.
	 *
	 * @param condition Where clause to chain
	 * @return This SQL select helper
	 */
	@SuppressWarnings( "unchecked" )
	public T andWhere( Where condition ) {
		if( where == null ) {
			where = condition;
			return (T) this;
		}

		where.and( condition );
		return (T) this;
	}

	/**
	 * Chain a Where clause using the boolean OR.
	 *
	 * @param condition Where clause to chain
	 * @return This SQL select helper
	 */
	@SuppressWarnings( "unchecked" )
	public T orWhere( Where condition ) {
		if( where == null ) {
			where = condition;
			return (T) this;
		}

		where.or( condition );
		return (T) this;
	}

	/**
	 * Init a new Where clause using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public T where( String column, String comparator, Object value ) {
		return this.where( new Where( column, comparator, value ) );
	}

	/**
	 * Chain a Where clause using the boolean AND, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public T andWhere( String column, String comparator, Object value ) {
		return this.andWhere( new Where( column, comparator, value ) );
	}

	/**
	 * Chain a Where clause using the boolean OR, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public T orWhere( String column, String comparator, Object value ) {
		return this.orWhere( new Where( column, comparator, value ) );
	}

	/**
	 * Append the builded Where clause to a StringBuilder
	 *
	 * @param sb StringBuilder to append to
	 */
	void appendWhereClause( StringBuilder sb ) {
		if( where != null && where.toString().length() > 0 ) {
			sb.append( " WHERE " );
			sb.append( where.toString() );
		}
	}
}
