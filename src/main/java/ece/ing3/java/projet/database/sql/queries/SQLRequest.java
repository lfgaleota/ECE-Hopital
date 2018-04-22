package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.sql.Model;

import java.util.List;

/**
 * Base SQL request interface.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public interface SQLRequest {
	Class<? extends Model> getModelClass();

	/**
	 * Generate the SQL query for this request helper.
	 *
	 * @return Generated SQL query
	 */
	String toString();

	/**
	 * Gets all the request's parameters
	 *
	 * @return Request parameters
	 */
	List<Object> getParameters();
}
