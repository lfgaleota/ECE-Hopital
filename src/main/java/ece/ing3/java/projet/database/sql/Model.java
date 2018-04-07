package ece.ing3.java.projet.database.sql;

/**
 * Abstract database model helper.
 * <p>
 * Models should inherit from this class to be able to use other helpers.
 */
public abstract class Model {
	/**
	 * Builds a table name from a model class
	 *
	 * @param modelClass Model class
	 * @return Model's table name
	 */
	public static String getTableName( Class<? extends Model> modelClass ) {
		String[] packages = modelClass.getName().toLowerCase().split( "\\." ); // Java's split uses RegExp so we need to escape the dot
		return packages[ packages.length - 1 ];
	}
}
