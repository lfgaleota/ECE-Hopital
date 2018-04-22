package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.*;

/**
 * SQL insert helper.
 * <p>
 * Provides a convenient way to build insert SQL queries, reactive-style, for a provided model class.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class SQLInsert implements SQLRequest {
	final Class<? extends Model> modelClass;
	final private String tableName;
	private Map<Integer, String> columns;
	private Map<Integer, Object> values;

	/**
	 * Creates a new helper for a model class.
	 *
	 * @param modelClass Model class
	 */
	public SQLInsert( Class<? extends Model> modelClass ) {
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
	 * Add a new value to the request.
	 *
	 * @param column Column to target
	 * @param value  Value to add
	 * @return This SQL insert helper
	 */
	public SQLInsert add( String column, Object value ) {
		if( column == null || value == null ) {
			throw new NullPointerException( "Column or value is null" );
		}
		int index = columns.size();
		if( columns.containsValue( column ) ) {
			for( Map.Entry<Integer, String> columnEntry : columns.entrySet() ) {
				if( columnEntry.getValue().equals( column ) ) {
					index = columnEntry.getKey();
				}
			}
		} else {
			columns.put( index, column );
		}
		if( value.getClass().isEnum() ) {
			values.put( index, value.toString() );
			return this;
		}
		values.put( index, value );
		return this;
	}

	/**
	 * Inserts the built row in the database.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws IllegalArgumentException No values where added
	 * @throws DatabaseException        Database error
	 */
	public int insert() throws IllegalArgumentException, DatabaseException {
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

		StringBuilder sb = new StringBuilder( "INSERT INTO " );
		sb.append( this.tableName );
		sb.append( " (" );

		for( int i = 0; i < columns.size() - 1; i++ ) {
			sb.append( columns.get( i ) );
			sb.append( ", " );
		}
		sb.append( columns.get( columns.size() - 1 ) );

		sb.append( ") VALUES (" );
		for( int i = 0; i < values.size() - 1; i++ ) {
			sb.append( "?, " );
		}
		sb.append( "?);" );

		return sb.toString();
	}

	@Override
	public List<Object> getParameters() {
		return new ArrayList<>( values.values() );
	}
}
