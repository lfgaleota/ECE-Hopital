package ece.ing3.java.projet.database.sql.queries;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
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

/**
 * SQL selector helper.
 * <p>
 * Provides a convenient way to build select SQL queries, reactive-style, for a provided model class.
 * </p>
 * <br>
 * <p>
 * SQL Select provides multiple ways to create SQL queries.<br>
 * Table name is directly inferred the from model class and column names are either gathered as defined by the model inputs.
 * </p>
 * <br>
 * <p>
 * SQLSelect supports conditioning using where clauses.<br>
 * Nesting where clauses is directly supported as a byproduct of using {@link Where} under the hood.<br>
 * For this, several methods are provided.
 * </p>
 * <p>
 * Where should be started by calling {@link SQLSelect#where(Where)}. While {@link SQLSelect#andWhere(Where)} and {@link SQLSelect#orWhere(Where)} can be used to start, <b>it is strongly discouraged and not supported</b>.
 * </p>
 * <p>
 * Where clauses can then be chained using either {@link SQLSelect#andWhere(Where)} and {@link SQLSelect#orWhere(Where)}, depending on the desired operator.
 * </p>
 * <p>
 * Each {@link SQLSelect#where(Where)}, {@link SQLSelect#andWhere(Where)} and {@link SQLSelect#orWhere(Where)} have shortcut methods, respectively {@link SQLSelect#where(String, String, Object)}, {@link SQLSelect#andWhere(String, String, Object)} and {@link SQLSelect#orWhere(String, String, Object)}, to avoid creating new unnecessary {@link Where} clauses.
 * </p>
 * <br>
 * <p>
 * Similarly, SQLSelect supports order by clauses.<br>
 * Again, several methods are provided.
 * </p>
 * <p>
 * Order By should be started by calling {@link SQLSelect#orderBy(OrderBy)}. While {@link SQLSelect#andOrderBy(OrderBy)} can be used to start, <b>it is strongly discouraged and not supported</b>.
 * </p>
 * <p>
 * Order By clauses can then be chained using {@link SQLSelect#andOrderBy(OrderBy)}.
 * </p>
 * <p>
 * {@link SQLSelect#orderBy(String, Order)} and {@link SQLSelect#andOrderBy(OrderBy)} have shortcut methods, respectively {@link SQLSelect#orderBy(String, Order)} and {@link SQLSelect#andOrderBy(String, Order)}, to avoid creating new unnecessary {@link OrderBy} clauses.
 * </p>
 * <br>
 * <p>
 * SQLSelect supports Reactive-style programming to create more compact code.<br>
 * Instead of creating a new object and calling methods on it repeatedly, line by line, calls can be chained directly, eliminating the need to ceate a temprary object and significantly shorting the code.
 * </p>
 * <pre>
 * SQLSelect{@literal <}Model{@literal >} s = new SQLSelect{@literal <}{@literal >}( Model.class );
 * s.where( "col1", "=", "val1" );
 * s.andWhere( "col2", "{@literal <}", "56" );
 * s.orderBy( "col3", Order.DESC );
 * Model m = s.findUnique();
 * </pre>
 * <p>
 * is strictly equivalent to
 * </p>
 * <pre>
 * Model m = ( new SQLSelect{@literal <}Model{@literal >}( Model.class ) ).where( "col1", "=", "val1" ).andWhere( "col2", "{@literal <}", "56" ).orderBy( "col3", Order.DESC ).findUnique();
 * </pre>
 * <br>
 * <p>
 * Let us see some example usage.<br>
 * For this, let us consider the following models:
 * </p>
 * <pre>
 * public class ExampleModel extends Model {
 *     {@literal @}Id
 *     {@literal @}Column( name = 'id' )
 *     private Long exampleId;
 *
 *     public ExampleModel() {}
 *
 *     public Long getExampleId() {
 *         return exampleId;
 *     }
 *
 *     public void setExampleId( Long v ) {
 *         exampleId = v;
 *     }
 * }
 * </pre>
 * <pre>
 * public class InheritedExampleModel extends ExampleModel {
 *      {@literal @}Column( name = 'num' )
 *      private Long number;
 *
 *     public InheritedExampleModel() {}
 *
 *     public Long getNumber() {
 *         return number;
 *     }
 *
 *     public void setNumber( Long v ) {
 *         number = v;
 *     }
 * }
 * </pre>
 * <p>
 * The simplest query is built by just passing a model class.<br>
 * {@code SQLSelect<ExampleModel> s = new SQLSelect<>( ExampleModel.class );}<br>
 * will generate : {@code SELECT id as exampleId FROM examplemodel;}<br>
 * Column names are automatically retrieved and mapped accordingly.
 * </p>
 * <p>
 * Multiple classes can be passed to create a {@code NATURAL JOIN} request between them. The most complete model should be passed as generic type.<br>
 * {@code SQLSelect<InheritedExampleModel> s = new SQLSelect<>( new Class[]{ InheritedExampleModel.class, ExampleModel.class } );}<br>
 * will generate : {@code SELECT id as exampleId, num as number FROM inheritedexamplemodel NATURAL JOIN examplemodel;}
 * </p>
 * <p>
 * If {@code NATURAL JOIN} is not the desired join clause, custom join clauses and conditions can be passed as parameters.<br>
 * There should be <b>exactly one less</b> join clauses and conditions than passed model classes.<br>
 * {@code SQLSelect<InheritedExampleModel> s = new SQLSelect<>( new Class[]{ InheritedExampleModel.class, ExampleModel.class }, new String[]{ 'LEFT OUTER JOIN' }, new String[]{ 'ON inheritedexamplemodel.id = examplemodel.id' } );}<br>
 * will generate : {@code SELECT id as exampleId, num as number FROM inheritedexamplemodel LEFT INNER JOIN examplemodel ON inheritedexamplemodel.id = examplemodel.id;}
 * </p>
 * <p>
 * For each possible combinations, it is possible to pass <b>field names</b> as last parameters or a column name String array to select specific columns.<br>
 * {@code SQLSelect<ExampleModel> s = new SQLSelect<>( ExampleModel.class, "exampleId" );}<br>
 * will generate : {@code SELECT id as exampleId FROM examplemodel;}<br>
 * {@code SQLSelect<InheritedExampleModel> s = new SQLSelect<>( new Class[]{ InheritedExampleModel.class, ExampleModel.class }, "number" );}<br>
 * will generate : {@code SELECT num as number FROM inheritedexamplemodel NATURAL JOIN examplemodel;}
 * </p>
 *
 * @param <M> Model class
 */
public class SQLSelect<M extends Model> extends SQLWhereQuery<SQLSelect> {
	private Class<? extends Model> modelClass;
	private String[] tableNames;
	private String[] selectedFields;
	private OrderBy orderBy;
	private String[] joinClause;
	private String[] joinCondition;

	/**
	 * Creates a new helper for a model class
	 *
	 * @param modelClass Model class
	 */
	public SQLSelect( Class<? extends Model> modelClass ) {
		this( modelClass, (String[]) null );
	}

	/**
	 * Creates a new helper for a model class, retrieving only the provided columns.
	 *
	 * @param modelClass      Model class
	 * @param selectedFields Fields to retrieve
	 */
	@SuppressWarnings( "unchecked" )
	public SQLSelect( Class<? extends Model> modelClass, String... selectedFields ) {
		this( new Class[]{ modelClass }, selectedFields );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN.
	 *
	 * @param modelClasses Model classes
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses ) {
		this( modelClasses, (String[]) null );
	}

	/**
	 * Creates a new helper for multiple model classes, assuming they are joined by NATURAL JOIN, retrieving only the provided columns.
	 *
	 * @param modelClasses    Model classes
	 * @param selectedFields Fields to retrieve
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String... selectedFields ) {
		this(
				modelClasses,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "NATURAL JOIN" ).toArray( new String[ modelClasses.length - 1 ] ) : null,
				modelClasses.length > 1 ? Collections.nCopies( modelClasses.length - 1, "" ).toArray( new String[ modelClasses.length - 1 ] ) : null,
				selectedFields
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
		this( modelClasses, joinClause, joinCondition, (String[]) null );
	}

	/**
	 * Creates a new helper for multiple model classes, joining them with the provided join clause and using the provided join condition, retrieving only the provided columns.
	 *
	 * @param modelClasses   Model classes
	 * @param joinClause     Join clause
	 * @param joinCondition  Join condition
	 * @param selectedFields Fields to retrieve
	 * @throws IllegalArgumentException Join clause or condition is malformed
	 */
	public SQLSelect( Class<? extends Model>[] modelClasses, String[] joinClause, String[] joinCondition, String... selectedFields ) throws IllegalArgumentException {
		this.modelClass = modelClasses[ 0 ];
		tableNames = Arrays.stream( modelClasses ).map( Model::getTableName ).toArray( String[]::new );
		this.orderBy = null;
		this.joinClause = joinClause;
		this.joinCondition = joinCondition;
		this.selectedFields = selectedFields;

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
	 * @param selectedFields New selected columns.
	 * @return This SQL select helper
	 */
	public SQLSelect setSelectedFields( String... selectedFields ) {
		this.selectedFields = selectedFields;
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
		ResultSetHandler<M> h = new BeanHandler<>( (Class<M>) getModelClass() );

		try {
			if( where != null ) {
				System.out.println( "Executing find unique with : " + toString() );
				System.out.println( "Parameters : " + where.getParameters() );
				return run.query( Database.get(), toString(), h, where.getParameters() );
			}

			System.out.println( "Executing find unique with : " + toString() );
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
		BeanListHandler<M> h = new BeanListHandler<>( (Class<M>) getModelClass() );

		try {
			if( where != null ) {
				System.out.println( "Executing find list with : " + toString() );
				System.out.println( "Parameters : " + where.getParameters() );
				return run.query( Database.get(), toString(), h, where.getParameters() );
			}

			System.out.println( "Executing find list with : " + toString() );
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
	public boolean hasAtLeastOne() throws DatabaseException {
		return findUnique() != null;
	}

	private void appendSelectors( StringBuilder sb ) {
		if( selectedFields != null && selectedFields.length > 0 ) {
			for( String selectedColumn : selectedFields ) {
				sb.append( Model.getFieldNameFromColumnName( modelClass, selectedColumn ) );
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
			sb.append( tableNames[ 0 ] );
			return;
		}

		sb.append( tableNames[ 0 ] );

		for( int i = 0; i < tableNames.length - 1; i++ ) {
			sb.append( " " );
			sb.append( joinClause[ i ] );
			sb.append( " " );
			sb.append( tableNames[ i + 1 ] );
			sb.append( " " );
			sb.append( joinCondition[ i ] );
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder( "SELECT " );

		appendSelectors( sb );

		sb.append( " FROM " );

		appendTableNames( sb );

		appendWhereClause( sb );

		if( orderBy != null && orderBy.toString().length() > 0 ) {
			sb.append( " ORDER BY " );
			sb.append( orderBy.toString() );
		}

		sb.append( ";" );
		return sb.toString();
	}
}
