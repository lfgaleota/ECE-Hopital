package ece.ing3.java.projet.database.sql;

import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.ExcludedField;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.database.sql.queries.SQLInsert;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.database.sql.queries.SQLUpdate;
import ece.ing3.java.projet.exceptions.DatabaseException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Abstract database model helper.
 * <p>
 * Models should inherit from this class to be able to use other helpers.
 * </p>
 * <p>
 * A model is an object that represent a database entry.<br />
 * Table name is the model's lowercase class name.<br />
 * Each field is automatically mapped to a column, if it is not excluded with the {@link ExcludedField} annotation.<br />
 * A field's corresponding column name is either described by a {@link Column} annotation or by using directly the lowercase field name.<br />
 * A field can be described as being used as primary key in database using the {@link Id} annotation.
 * </p>
 * <p>
 * Each model must have <b>a default constructor</b>, <b>getters and setters for each field</b>.
 * Failing in following this rule <b>will lead to undefined behavior</b>.
 * </p>
 * <br />
 * <p>
 * One example of model can be :
 * <pre>
 * public class ExampleModel extends Model {
 *     {@literal @}Id
 *     {@literal @}Column( name = 'id' )
 *     private Long exampleId; // Field being a database ID, named exampleId and mapped to column 'id'
 *
 *     private String exampleString; // Field name exampleString, mapped to column 'examplestring'
 *
 *     {@literal @}ExcludedField
 *     private String excludedString; // Not used as a database column
 *
 *     public ExampleModel() {}
 *
 *     // ... getters and setters for exampleId and exampleString ...
 * }
 * </pre>
 * </p>
 */
public abstract class Model {
	private static Map<Class<? extends Model>, Map<String, String>> columnFieldNames = new HashMap<>();

	private interface FieldProcessor {
		void process( Field field ) throws IllegalAccessException, NullPointerException;
	}

	/**
	 * Builds a table name from a model class.
	 *
	 * @param modelClass Model class
	 * @return Model's table name
	 */
	public static String getTableName( Class<? extends Model> modelClass ) {
		String[] packages = modelClass.getName().toLowerCase().split( "\\." ); // Java's split uses RegExp so we need to escape the dot
		return packages[ packages.length - 1 ];
	}

	/**
	 * Builds a column name from a model field.
	 *
	 * @param modelField Model field
	 * @return Field's column name or {@code null} if field should be excluded
	 */
	public static String getColumnName( Field modelField ) {
		if( modelField.isAnnotationPresent( ExcludedField.class ) )
			return null;

		Annotation annotation = modelField.getAnnotation( Column.class );
		if( annotation != null ) {
			return ( (Column) annotation ).name();
		}

		return modelField.getName().toLowerCase();
	}

	/**
	 * Get the corresponding field name of a column for a defined model class.
	 *
	 * @param modelClass Model class
	 * @param columnName Column name
	 * @return Column's name field name or {@code null} if field should be excluded
	 */
	public static String getColumnName( Class<? extends Model> modelClass, String columnName ) {
		if( !columnFieldNames.containsKey( modelClass ) )
			buildColumnNames( modelClass );
		return columnFieldNames.get( modelClass ).get( columnName );
	}

	/**
	 * Get all the column/field name pairs for a defined model class.
	 *
	 * @param modelClass Model class
	 * @return Column/field name pairs
	 */
	public static Map<String, String> getColumnFieldNames( Class<? extends Model> modelClass ) {
		if( !columnFieldNames.containsKey( modelClass ) )
			buildColumnNames( modelClass );
		return columnFieldNames.get( modelClass );
	}

	private static void processFields( Class<? extends Model> modelClass, FieldProcessor fieldProcessor, boolean silenceIllegalAccess ) throws IllegalAccessException {
		Class currentClass = modelClass;
		while( currentClass != Model.class ) {
			for( Field field : currentClass.getDeclaredFields() ) {
				try {
					fieldProcessor.process( field );
				} catch( IllegalAccessException e ) {
					if( !silenceIllegalAccess ) {
						throw e;
					} else {
						e.printStackTrace();
					}
				}
			}
			currentClass = currentClass.getSuperclass();
		}
	}

	private static void buildColumnNames( Class<? extends Model> modelClass ) {
		Map<String, String> map = new HashMap<>();

		try {
			processFields( modelClass, field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					map.put( getColumnName( field ), field.getName() );
				}
			}, true );
		} catch( IllegalAccessException e ) {
			e.printStackTrace();
		}

		columnFieldNames.put( modelClass, map );
	}

	private void selectByIds( SQLSelect selectHelper ) {
		List<String> idColumnNames = new LinkedList<>();

		try {
			processFields( getClass(), field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) && field.isAnnotationPresent( Id.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					idColumnNames.add( field.getName() );
				}
			}, true );
		} catch( IllegalAccessException e ) {
			e.printStackTrace();
		}

		selectHelper.setSelectedFields( idColumnNames.toArray( new String[ idColumnNames.size() ] ) );
	}

	private int insert() throws DatabaseException {
		SQLInsert req = new SQLInsert( getClass() );

		try {
			processFields( getClass(), field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					Object val = field.get( this );
					if( val == null ) {
						throw new NullPointerException( "Model instance has a field as null" );
					}

					req.add( getColumnName( field ), val );
				}
			}, false );
		} catch( IllegalAccessException e ) {
			throw new DatabaseException( "Unable to access model field.", e );
		} catch( NullPointerException e ) {
			throw new DatabaseException( e );
		}

		return req.insert();
	}

	private int update() throws DatabaseException {
		SQLUpdate req = new SQLUpdate( getClass() );

		try {
			processFields( getClass(), field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					Object value = field.get( this );

					if( value != null ) {
						req.set( getColumnName( field ), value );
					}
				}
			}, false );
		} catch( IllegalAccessException e ) {
			throw new DatabaseException( "Unable to access model field.", e );
		}

		return req.update();
	}

	/**
	 * Saves a model instance to database, using the model's attribute values.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws DatabaseException Error occurred while saving to database
	 */
	public int save() throws DatabaseException {
		SQLSelect selectHelper = new SQLSelect( getClass() );

		selectByIds( selectHelper );

		if( selectHelper.hasAtLeastOne() ) {
			return insert();
		}

		return update();
	}
}
