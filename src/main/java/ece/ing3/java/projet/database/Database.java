package ece.ing3.java.projet.database;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.DbUtils;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

/**
 * Database helper.
 * <p>
 * Offers utility functions to manage a database.
 * Database configuration is loaded automatically.
 */
public class Database {
	private static Connection connection;
	private static BasicDataSource dataSource;

	/**
	 * Init the database and connect to it.
	 *
	 * @throws DatabaseException Database error
	 */
	public static void init() throws DatabaseException {
		try {
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
