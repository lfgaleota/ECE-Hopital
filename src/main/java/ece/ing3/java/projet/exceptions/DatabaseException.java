package ece.ing3.java.projet.exceptions;

/**
 * Exception thrown when a database operation failed.
 */
public class DatabaseException extends Exception {
	/**
	 * {@link Exception#Exception(String, Throwable)}
	 */
	public DatabaseException( String message, Throwable cause ) {
		super( message, cause );
	}

	/**
	 * {@link Exception#Exception(String)}
	 */
	public DatabaseException( String message ) {
		super( message );
	}

	/**
	 * {@link Exception#Exception(Throwable)}
	 */
	public DatabaseException( Throwable cause ) {
		super( cause );
	}
}
