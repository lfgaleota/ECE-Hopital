package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.enumerations.Order;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
//System.out.println( ( new ece.ing3.java.projet.modele.finders.ServiceFinder() ).findList() );

/**
 * SQL selector helper.
 * <p>
 * Provides a convenient way to build select SQL queries, reactive-style, for a provided model class.
 *
 * @param <M> Model class
 */
public class SQLSelect<M extends Model> extends SQLWhereQuery<SQLSelect> {
	private Class<? extends Model> modelClass;
	private String[] tableNames;
	private String[] selectedColumns;
	private OrderBy orderBy;
	private String[] joinClause;
	private String[] joinCondition;

	/**
	 * Creates a new helper for a model class
	 *
	 * @param modelClass Model class
	 */
	public SQLSelect( Class<? extends Model> modelClass ) {
		this( modelClass, ( String[] ) null );
	}

	/**
	 * Creates a new helper for a model class, retrieving only the provided columns.
	 *
	 * @param modelClass      Model class
	 * @param selectedColumns Columns to retrieve
	 */
	@SuppressWarnings( "unchecked" )
	public SQLSelect( Class<? extends Model> modelClass, String... selectedColumns ) {
		this( new Class[]{ modelClass }, selectedColumns );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN.
	 *
	 * @param modelClasses Model classes
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses ) {
		this( modelClasses, ( String[] ) null );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN, retrieving only the provided columns.
	 *
	 * @param modelClasses    Model classes
	 * @param selectedColumns Columns to retrieve
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String... selectedColumns ) {
		this(
				modelClasses,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "NATURAL JOIN" ).toArray( new String[modelClasses.length - 1] ) : null,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "" ).toArray( new String[modelClasses.length - 1] ) : null,
				selectedColumns
		);
	}

	/**
	 * Creates a new helper for multiple model classes, joining them with the provided join clause and using the provided join condition.
	 *
	 * @param modelClasses  Model classes
	 * @param joinClause    Join clause
	 * @param joinCondition Join condition
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String[] joinClause, String[] joinCondition ) {
		this( modelClasses, joinClause, joinCondition, ( String[] ) null );
	}

	/**
	 * Creates a new helper for multiple model classes, joining them with the provided join clause and using the provided join condition, retrieving only the provided columns.
	 *
	 * @param modelClasses    Model classes
	 * @param joinClause      Join clause
	 * @param joinCondition   Join condition
	 * @param selectedColumns Columns to retrieve
	 * @throws IllegalArgumentException Join clause or condition is malformed
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String[] joinClause, String[] joinCondition, String... selectedColumns ) throws IllegalArgumentException {
		this.modelClass = modelClasses[0];
		tableNames = Arrays.stream( modelClasses ).map( Model::getTableName ).toArray( String[]::new );
		this.orderBy = null;
		this.joinClause = joinClause;
		this.joinCondition = joinCondition;
		this.selectedColumns = selectedColumns;

		if( joinClause != null && joinClause.length != ( tableNames.length - 1 ) ) {
			throw new IllegalArgumentException( "Malformed join clause, expected " + ( tableNames.length - 1 ) + " clauses, got " + joinClause.length );
		}

		if( joinCondition != null && joinCondition.length != ( tableNames.length - 1 ) ) {
			throw new IllegalArgumentException( "Malformed join condition, expected " + ( tableNames.length - 1 ) + " conditions, got " + joinCondition.length );
		}
	}

	@Override
	public Class<? extends Model> getModelClass() {
		return modelClass;
	}

	/**
	 * Sets new selected columns.
	 *
	 * @param selectedColumns New selected columns.
	 * @return This SQL select helper
	 */
	public SQLSelect setSelectedColumns( String... selectedColumns ) {
		this.selectedColumns = selectedColumns;
		return this;
	}

	/**
	 * Init a new Order By clause.
	 *
	 * @param condition Order By clause to use
	 * @return This SQL select helper
	 */
	public SQLSelect orderBy( OrderBy condition ) {
		orderBy = condition;
		return this;
	}

	/**
	 * Chain another Order By condition using the provided values.
	 *
	 * @param condition Order By clause to chain
	 * @return This SQL select helper
	 */
	public SQLSelect andOrderBy( OrderBy condition ) {
		if( orderBy == null ) {
			orderBy = condition;
			return this;
		}

		orderBy.and( condition );
		return this;
	}

	/**
	 * Init a new Order By clause using the provided values.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 * @return This SQL select helper
	 */
	public SQLSelect orderBy( String column, Order order ) {
		return this.orderBy( new OrderBy( column, order ) );
	}

	/**
	 * Chain another Order By condition using the provided values.
	 *
	 * @param column Column to target
	 * @param order  Order to target
	 * @return This SQL select helper
	 */
	public SQLSelect andOrderBy( String column, Order order ) {
		return this.orderBy( new OrderBy( column, order ) );
	}



	/**
	 * Execute the built query and retrieve a unique, directly usable model instance.
	 *
	 * @return Model instance
	 * @throws DatabaseException Database error
	 */
	@SuppressWarnings( "unchecked" )
	public M findUnique() throws DatabaseException {
		QueryRunner run = new QueryRunner();
		ResultSetHandler<M> h = new BeanHandler<>( ( Class<M> ) getModelClass() );
		try {
			if( where != null ) {
				return run.query( Database.get(), toString(), h, where.getParameters() );
			}

			return run.query( Database.get(), toString(), h );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Execute the built query and retrieve a list of model instances.
	 *
	 * @return Model instances
	 * @throws DatabaseException Database error
	 */
	@SuppressWarnings( "unchecked" )
	public List<M> findList() throws DatabaseException {
		QueryRunner run = new QueryRunner();
		BeanListHandler<M> h = new BeanListHandler<>( ( Class<M> ) getModelClass() );

		try {
			if( where != null ) {
				return run.query( Database.get(), toString(), h, where.getParameters() );
			}

			return run.query( Database.get(), toString(), h );
		} catch( SQLException e ) {
			throw new DatabaseException( e );
		}
	}

	/**
	 * Execute the built query and return if there is at least one result.
	 *
	 * @return {@code true} if has at least one result
	 * @throws DatabaseException Database error
	 */
	public boolean hasUnique() throws DatabaseException {
		return findUnique() != null;
	}

	private void appendSelectors( StringBuilder sb ) {
		if( selectedColumns != null && selectedColumns.length > 0 ) {
			for( String selectedColumn : selectedColumns ) {
				sb.append( Model.getColumnName( modelClass, selectedColumn ) );
				sb.append( " AS " );
				sb.append( selectedColumn );
				sb.append( "," );
			}
			sb.deleteCharAt( sb.length() - 1 );
		} else {
			for( Map.Entry<String, String> columnField : Model.getColumnFieldNames( modelClass ).entrySet() ) {
				sb.append( columnField.getKey() );
				sb.append( " AS " );
				sb.append( columnField.getValue() );
				sb.append( "," );
			}
			sb.deleteCharAt( sb.length() - 1 );
		}
	}

	private void appendTableNames( StringBuilder sb ) {
		if( tableNames.length == 1 ) {
			sb.append( tableNames[0] );
			return;
		}

		sb.append( tableNames[0] );

		for( int i = 0; i < tableNames.length - 1; i++ ) {
			sb.append( " " );
			sb.append( joinClause[i] );
			sb.append( " " );
			sb.append( tableNames[i + 1] );
			sb.append( " " );
			sb.append( joinCondition[i] );
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "SELECT " );

		appendSelectors( sb );

		sb.append( " FROM " );

		appendTableNames( sb );

		appendWhereClause( sb );

		if( orderBy != null ) {
			sb.append( " ORDER BY " );
			sb.append( orderBy.toString() );
		}

		sb.append( ";" );
		return sb.toString();
	}
}
