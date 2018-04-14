package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.sql.Model;

/**
 * Base SQL request interface.
 */
public interface SQLRequest {
	Class<? extends Model> getModelClass();

	/**
	 * Generate the SQL query for this request helper.
	 *
	 * @return Generated SQL query
	 */
	String toString();
}
