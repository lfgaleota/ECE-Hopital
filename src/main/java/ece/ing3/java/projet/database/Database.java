package ece.ing3.java.projet.database;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.sql.*;

/**
 * Database helper.
 * <p>
 * Offers utility functions to manage a database.
 * Database configuration is loaded automatically.
 */
public class Database {
	private static Connection connection;

	/**
	 * Init the database and connect to it.
	 *
	 * @throws DatabaseException Database error
	 */
	public static void init() throws DatabaseException {
		try {
			// The newInstance() call is a work around for some broken Java implementations
			Class.forName( Configuration.getString( "database.driver" ) ).newInstance();
		} catch( Exception ex ) {
			throw new DatabaseException( "JDBC connector not available. Verify if the application is installed and configured correctly." );
		}

		try {
			connection = DriverManager.getConnection( Configuration.getString( "database.url" ), Configuration.getString( "database.username" ), Configuration.getString( "database.password" ) );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Create a new statement on the opened database.
	 *
	 * @return Created blank statement
	 * @throws DatabaseException Database error
	 */
	public static Statement statement() throws DatabaseException {
		try {
			return connection.createStatement();
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Create a new prepared statement on the opened database, using the provided SQL query.
	 *
	 * @param query SQL query
	 * @return Prepared statement
	 * @throws DatabaseException Database error
	 */
	public static PreparedStatement preparedStatement( String query ) throws DatabaseException {
		try {
			return connection.prepareStatement( query );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Execute a SQL query on the database.
	 *
	 * @param query SQL query to execute
	 * @return Query result
	 * @throws DatabaseException Database error
	 */
	public static ResultSet execute( String query ) throws DatabaseException {
		try {
			return connection.createStatement().executeQuery( query );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Closes the database.
	 *
	 * @throws DatabaseException Database error
	 */
	public static void close() throws DatabaseException {
		try {
			connection.close();
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}
}
