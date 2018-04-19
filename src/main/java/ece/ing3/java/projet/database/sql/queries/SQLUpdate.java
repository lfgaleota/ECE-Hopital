package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL update helper.
 * <p>
 * Provides a convenient way to build update SQL queries, reactive-style, for a provided model class.
 */
public class SQLUpdate extends SQLWhereQuery<SQLUpdate> {
	final Class<? extends Model> modelClass;
	final private String tableName;
	private Map<Integer, String> columns;
	private Map<Integer, Object> values;

	/**
	 * Creates a new helper for a model class.
	 *
	 * @param modelClass Model class
	 */
	public SQLUpdate( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;
		tableName = Model.getTableName( modelClass );
		columns = new HashMap<>();
		values = new HashMap<>();
	}

	@Override
	public Class<? extends Model> getModelClass() {
		return modelClass;
	}

	/**
	 * Sets a new value for a defined column.
	 *
	 * @param column Column to target
	 * @param value  New column value
	 * @return This SQL insert helper
	 * @throws NullPointerException Column or value is {@code null}
	 */
	public SQLUpdate set( String column, Object value ) throws NullPointerException {
		if( column == null || value == null ) {
			throw new NullPointerException( "Column or value is null" );
		}
		columns.put( columns.size(), column );
		values.put( values.size(), value );
		return this;
	}

	/**
	 * Updates the database according to the provided values and conditions.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws IllegalArgumentException No values where set
	 * @throws DatabaseException        Database error
	 */
	public int update() throws IllegalArgumentException, DatabaseException {
		QueryRunner run = new QueryRunner();

		try {
			return run.update( Database.get(), toString(), getParameters().toArray() );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Generate the SQL query for this insert helper.
	 *
	 * @return Generated SQL query
	 * @throws IllegalArgumentException No values where added
	 */
	@Override
	public String toString() throws IllegalArgumentException {
		if( values.size() == 0 ) {
			throw new IllegalArgumentException( "No values added." );
		}

		StringBuilder sb = new StringBuilder( "UPDATE " );
		sb.append( this.tableName );
		sb.append( " SET " );

		for( int i = 0; i < columns.size() - 1; i++ ) {
			sb.append( columns.get( i ) );
			sb.append( " = ?, " );
		}
		sb.append( columns.get( columns.size() - 1 ) );
		sb.append( " = ? " );

		appendWhereClause( sb );

		sb.append( ";" );

		return sb.toString();
	}

	@Override
	public List<Object> getParameters() {
		List<Object> parameters = new ArrayList<>( values.values() );
		if( super.getParameters() != null ) {
			parameters.addAll( super.getParameters() );
		}
		return parameters;
	}
}
