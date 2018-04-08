package ece.ing3.java.projet.database.sql;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

/**
 * SQL selector helper.
 * <p>
 * Provides a convenient way to build select SQL queries, reactive-style, for a provided model class.
 */
public class SQLSelect {
	private String[] tableNames;
	private String[] selectedColumns;
	private Where where;
	private OrderBy orderBy;
	private String[] joinClause;
	private String[] joinCondition;

	/**
	 * Creates a new helper for a model class
	 *
	 * @param modelClass Model class
	 */
	public SQLSelect( Class<? extends Model> modelClass ) {
		this( modelClass, (String[]) null );
	}

	/**
	 * Creates a new helper for a model class, retrieving only the provided columns.
	 *
	 * @param modelClass      Model class
	 * @param selectedColumns Columns to retrieve
	 */
	@SuppressWarnings( "unchecked" )
	public SQLSelect( Class<? extends Model> modelClass, String... selectedColumns ) {
		this( new Class[]{ modelClass }, selectedColumns );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN.
	 *
	 * @param modelClasses Model classes
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses ) {
		this( modelClasses, (String[]) null );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN, retrieving only the provided columns.
	 *
	 * @param modelClasses    Model classes
	 * @param selectedColumns Columns to retrieve
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String... selectedColumns ) {
		this(
				modelClasses,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "NATURAL JOIN" ).toArray( new String[ modelClasses.length - 1 ] ) : null,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "" ).toArray( new String[ modelClasses.length - 1 ] ) : null,
				selectedColumns
		);
	}

	/**
	 * Creates a new helper for multiple model classes, joining them with the provided join clause and using the provided join condition.
	 *
	 * @param modelClasses  Model classes
	 * @param joinClause    Join clause
	 * @param joinCondition Join condition
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String[] joinClause, String[] joinCondition ) {
		this( modelClasses, joinClause, joinCondition, (String[]) null );
	}

	/**
	 * Creates a new helper for multiple model classes, joining them with the provided join clause and using the provided join condition, retrieving only the provided columns.
	 *
	 * @param modelClasses    Model classes
	 * @param joinClause      Join clause
	 * @param joinCondition   Join condition
	 * @param selectedColumns Columns to retrieve
	 * @throws IllegalArgumentException Join clause or condition is malformed
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String[] joinClause, String[] joinCondition, String... selectedColumns ) throws IllegalArgumentException {
		tableNames = Arrays.stream( modelClasses ).map( Model::getTableName ).toArray( String[]::new );
		this.where = null;
		this.orderBy = null;
		this.joinClause = joinClause;
		this.joinCondition = joinCondition;
		this.selectedColumns = selectedColumns;

		if( joinClause != null && joinClause.length != ( tableNames.length - 1 ) ) {
			throw new IllegalArgumentException( "Malformed join clause, expected " + ( tableNames.length - 1 ) + " clauses, got " + joinClause.length );
		}

		if( joinCondition != null && joinCondition.length != ( tableNames.length - 1 ) ) {
			throw new IllegalArgumentException( "Malformed join condition, expected " + ( tableNames.length - 1 ) + " conditions, got " + joinCondition.length );
		}
	}

	/**
	 * Init a new where clause.
	 *
	 * @param condition Where clause to use
	 * @return This SQL select helper
	 */
	public SQLSelect where( Where condition ) {
		where = condition;
		return this;
	}

	/**
	 * Chain a new Where clause using the boolean AND.
	 *
	 * @param condition Where clause to chain
	 * @return This SQL select helper
	 */
	public SQLSelect andWhere( Where condition ) {
		if( where == null ) {
			where = condition;
			return this;
		}

		where.and( condition );
		return this;
	}

	/**
	 * Chain a Where clause using the boolean OR.
	 *
	 * @param condition Where clause to chain
	 * @return This SQL select helper
	 */
	public SQLSelect orWhere( Where condition ) {
		if( where == null ) {
			where = condition;
			return this;
		}

		where.or( condition );
		return this;
	}

	/**
	 * Init a new Where clause using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public SQLSelect where( String column, String comparator, Object value ) {
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
	public SQLSelect andWhere( String column, String comparator, Object value ) {
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
	public SQLSelect orWhere( String column, String comparator, Object value ) {
		return this.orWhere( new Where( column, comparator, value ) );
	}

	/**
	 * Init a new Order By clause.
	 *
	 * @param condition Order By clause to use
	 * @return This SQL select helper
	 */
	public SQLSelect orderBy( OrderBy condition ) {
		orderBy = condition;
		return this;
	}

	/**
	 * Chain another Order By condition using the provided values.
	 *
	 * @param condition Order By clause to chain
	 * @return This SQL select helper
	 */
	public SQLSelect andOrderBy( OrderBy condition ) {
		if( orderBy == null ) {
			orderBy = condition;
			return this;
		}

		orderBy.and( condition );
		return this;
	}

	/**
	 * Init a new Order By clause using the provided values.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 * @return This SQL select helper
	 */
	public SQLSelect orderBy( String column, Order order ) {
		return this.orderBy( new OrderBy( column, order ) );
	}

	/**
	 * Chain another Order By condition using the provided values.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 * @return This SQL select helper
	 */
	public SQLSelect andOrderBy( String column, Order order ) {
		return this.orderBy( new OrderBy( column, order ) );
	}

	private ResultSet find( String query ) throws DatabaseException {
		if( where != null ) {
			try {
				PreparedStatement ps = Database.preparedStatement( query );

				for( int i = 0; i < where.getParameters().size(); i++ ) {
					ps.setObject( i + 1, where.getParameters().get( i ) );
				}

				return ps.executeQuery();
			} catch( SQLException e ) {
				throw new DatabaseException( e );
			}
		}

		return Database.execute( query );
	}

	/**
	 * Execute the builded query and retrieve a unique, directly usable {@link ResultSet}.
	 *
	 * @return Query result
	 * @throws DatabaseException Database error
	 */
	public ResultSet findUnique() throws DatabaseException {
		String query = toString();
		ResultSet rs = find( query.substring( 0, query.length() - 1 ) + " LIMIT 1;" );
		try {
			if( rs.next() ) {
				return rs;
			}
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}

		return null;
	}

	/**
	 * Execute the builded query and retrieve its result. This {@link ResultSet} can be fetched using {@link ResultSet#next()}
	 *
	 * @return Query result
	 * @throws DatabaseException Database error
	 */
	public ResultSet find() throws DatabaseException {
		return find( toString() );
	}

	private void appendTableNames( StringBuilder sb ) {
		if( tableNames.length == 1 ) {
			sb.append( tableNames[ 0 ] );
			return;
		}

		sb.append( tableNames[ 0 ] );

		for( int i = 0; i < tableNames.length - 1; i++ ) {
			sb.append( " " );
			sb.append( joinClause[ i ] );
			sb.append( " " );
			sb.append( tableNames[ i + 1 ] );
			sb.append( " " );
			sb.append( joinCondition[ i ] );
		}
	}

	/**
	 * Generate the SQL query for this select helper.
	 *
	 * @return Generated SQL query
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "SELECT " );
		if( selectedColumns != null ) {
			sb.append( String.join( ",", selectedColumns ) );
		} else {
			sb.append( "*" );
		}
		sb.append( " FROM " );

		appendTableNames( sb );

		if( where != null ) {
			sb.append( " WHERE " );
			sb.append( where.toString() );
		}

		if( orderBy != null ) {
			sb.append( " ORDER BY " );
			sb.append( orderBy.toString() );
		}

		sb.append( ";" );
		return sb.toString();
	}
}
