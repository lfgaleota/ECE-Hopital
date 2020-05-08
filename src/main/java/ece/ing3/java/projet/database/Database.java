package ece.ing3.java.projet.database;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.exceptions.ConfigurationException;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;

/**
 * Database helper.
 * <p>
 * Offers utility functions to manage a database.
 * Database configuration is loaded automatically.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Database {
	private static Connection connection;
	private static BasicDataSource dataSource;

	/**
	 * Connects to the database.
	 *
	 * @throws DatabaseException      Database error
	 * @throws ConfigurationException Invalid configuration
	 */
	public static void connect() throws ConfigurationException, DatabaseException {
		try {
			if(
				Configuration.getString( "database.driver" ) == null ||
				Configuration.getString( "database.username" ) == null ||
				Configuration.getString( "database.password" ) == null ||
				Configuration.getString( "database.url" ) == null
			) {
				throw new ConfigurationException( "Missing configuration value" );
			}

			dataSource = new BasicDataSource();
			dataSource.setDriverClassName( Configuration.getString( "database.driver" ) );
			dataSource.setUsername( Configuration.getString( "database.username" ) );
			dataSource.setPassword( Configuration.getString( "database.password" ) );
			dataSource.setUrl( Configuration.getString( "database.url" ) );
			connection = dataSource.getConnection();
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Validates the database's schema schema.
	 *
	 * @return {@code true} if the schema is correct
	 */
	public static boolean validate() throws DatabaseException {
		try {
			execute( Constants.DB_VALIDATION_QUERY );
			return true;
		} catch( DatabaseException e ) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Initialize the database with required schema.
	 *
	 * @throws DatabaseException      Database error
	 */
	public static void init() throws DatabaseException {
		try {
			String initIdl = Utils.getTextResource( Constants.DB_EMBEDDED_INIT_IDL_FILENAME );
			statement().executeUpdate( initIdl );
		} catch( IOException | SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Fill the database with example data.
	 *
	 * @throws DatabaseException      Database error
	 */
	public static void fillWithExamples() throws DatabaseException {
		try {
			String exampleIdl = Utils.getTextResource( Constants.DB_EMBEDDED_EXAMPLE_IDL_FILENAME );
			statement().executeUpdate( exampleIdl );
		} catch( IOException | SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Gets the data source.
	 *
	 * @return Data source
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * Gets the current connection.
	 *
	 * @return Connection
	 */
	public static Connection get() {
		return connection;
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
			DbUtils.close( connection );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}
}
