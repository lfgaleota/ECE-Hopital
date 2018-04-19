package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SQL delete helper.
 * <p>
 * Provides a convenient way to build delete SQL queries, reactive-style, for a provided model class.
 */
public class SQLDelete extends SQLWhereQuery<SQLDelete> {
	private final Class<? extends Model> modelClass;
	private final String tableName;

	/**
	 * Creates a new helper for a model class.
	 *
	 * @param modelClass Model class
	 */
	public SQLDelete( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;
		tableName = Model.getTableName( modelClass );
	}

	@Override
	public Class<? extends Model> getModelClass() {
		return modelClass;
	}

	/**
	 * Deletes the requested lines of a model's table from the database.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws IllegalArgumentException No values where set
	 * @throws DatabaseException        Database error
	 */
	public int delete() throws IllegalArgumentException, DatabaseException {
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
		StringBuilder sb = new StringBuilder( "DELETE FROM " );
		sb.append( this.tableName );

		appendWhereClause( sb );

		sb.append( ";" );

		return sb.toString();
	}
}
