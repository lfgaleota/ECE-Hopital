package ece.ing3.java.projet.database.sql;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * SQL selector helper.
 * <p>
 * Provides a convenient way to build select SQL queries, reactive-style, for a provided model class.
 */
public class SQLSelect {
	private String tableName;
	private String[] selectedColumns;
	private Where where;
	private String clauseOrderBy;

	/**
	 * Creates a new helper for a model class
	 *
	 * @param modelClass Model class
	 */
	public SQLSelect( Class<? extends Model> modelClass ) {
		tableName = Model.getTableName( modelClass );
		this.clauseOrderBy = "";
		this.where = null;
		this.selectedColumns = null;
	}

	/**
	 * Creates a new helper for a model class, retrieving only the provided columns.
	 *
	 * @param modelClass      Model class
	 * @param selectedColumns Columns to retrieve
	 */
	public SQLSelect( Class<? extends Model> modelClass, String... selectedColumns ) {
		this( modelClass );
		this.selectedColumns = selectedColumns;
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
	 * Chain a new where clause using the boolean AND.
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
	 * Chain a where clause using the boolean OR.
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
		where = new Where( column, comparator, value );
		return this;
	}

	/**
	 * Chaine a where clause using the boolean AND, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public SQLSelect andWhere( String column, String comparator, Object value ) {
		Where newCondition = new Where( column, comparator, value );

		if( where == null ) {
			where = newCondition;
			return this;
		}

		where.and( newCondition );
		return this;
	}

	/**
	 * Chaine a where clause using the boolean OR, using the provided values.
	 *
	 * @param column     Column to target
	 * @param comparator Comparator to use
	 * @param value      Comparison value
	 * @return This SQL select helper
	 */
	public SQLSelect orWhere( String column, String comparator, Object value ) {
		Where newCondition = new Where( column, comparator, value );

		if( where == null ) {
			where = newCondition;
			return this;
		}

		where.or( newCondition );
		return this;
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
		sb.append( tableName );
		if( where != null ) {
			sb.append( " WHERE " );
			sb.append( where.toString() );
		}
		if( clauseOrderBy.length() > 0 ) {
			sb.append( " ORDER BY " );
			sb.append( clauseOrderBy );
		}
		sb.append( ";" );
		return sb.toString();
	}
}
