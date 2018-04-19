package ece.ing3.java.projet.database.sql;

import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.ExcludedField;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLDelete;
import ece.ing3.java.projet.database.sql.queries.SQLInsert;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.database.sql.queries.SQLUpdate;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Abstract database model helper.
 * <p>
 * Models should inherit from this class to be able to use other helpers.
 * </p>
 * <p>
 * A model is an object that represent a database entry.<br>
 * Table name is the model's lowercase class name.<br>
 * Each field is automatically mapped to a column, if it is not excluded with the {@link ExcludedField} annotation.<br>
 * A field's corresponding column name is either described by a {@link Column} annotation or by using directly the lowercase field name.<br>
 * A field can be described as being used as primary key in database using the {@link Id} annotation.
 * </p>
 * <p>
 * Each model must have <b>a default constructor</b>, <b>getters and setters for each field</b>.
 * Failing in following this rule <b>will lead to undefined behavior</b>.
 * </p>
 * <br>
 * <p>
 * One example of model can be :
 * </p>
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
 */
public abstract class Model {
	private static Map<Class<? extends Model>, Map<String, String>> columnFieldNames = new HashMap<>();
	private static Map<Class<? extends Model>, Map<String, String>> fieldColumnNames = new HashMap<>();
	private static Map<Class<? extends Model>, String[]> fieldNames = new HashMap<>();

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
	public static String getFieldNameFromColumnName( Class<? extends Model> modelClass, String columnName ) {
		if( !columnFieldNames.containsKey( modelClass ) )
			buildNames( modelClass );
		return columnFieldNames.get( modelClass ).get( columnName );
	}

	/**
	 * Get the corresponding field name of a column for a defined model class.
	 *
	 * @param modelClass Model class
	 * @param fieldName Field name
	 * @return Field's column name or {@code null} if field should be excluded
	 */
	public static String getColumnNameFromFieldName( Class<? extends Model> modelClass, String fieldName ) {
		if( !fieldColumnNames.containsKey( modelClass ) )
			buildNames( modelClass );
		return fieldColumnNames.get( modelClass ).get( fieldName );
	}

	/**
	 * Get all the column/field name pairs for a defined model class.
	 *
	 * @param modelClass Model class
	 * @return Column/field name pairs
	 */
	public static Map<String, String> getColumnFieldNames( Class<? extends Model> modelClass ) {
		if( !columnFieldNames.containsKey( modelClass ) )
			buildNames( modelClass );
		return columnFieldNames.get( modelClass );
	}

	/**
	 * Get all the field names for a defined model class.
	 *
	 * @param modelClass Model class
	 * @return Column/field name pairs
	 */
	public static String[] getFieldNames( Class<? extends Model> modelClass ) {
		if( !fieldNames.containsKey( modelClass ) )
			buildNames( modelClass );
		return fieldNames.get( modelClass );
	}

	private static void processFields( Class<? extends Model> modelClass, FieldProcessor fieldProcessor, boolean silenceIllegalAccess, boolean onlyClassField ) throws IllegalAccessException {
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
			if( onlyClassField ) {
				break;
			}
			currentClass = currentClass.getSuperclass();
		}
	}

	private static void buildNames( Class<? extends Model> modelClass ) {
		Map<String, String> columnMap = new HashMap<>();
		Map<String, String> fieldMap = new HashMap<>();
		List<String> fieldNameList = new ArrayList<>();

		try {
			processFields( modelClass, field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					columnMap.put( getColumnName( field ), field.getName() );
					fieldMap.put( field.getName(), getColumnName( field ) );
					fieldNameList.add( field.getName() );
				}
			}, true, false );
		} catch( IllegalAccessException e ) {
			e.printStackTrace();
		}

		columnFieldNames.put( modelClass, columnMap );
		fieldColumnNames.put( modelClass, fieldMap );
		fieldNames.put( modelClass, fieldNameList.toArray( new String[ 0 ] ) );
	}

	/**
	 * Updates a Where clause to select the current model.
	 *
	 * @param whereClause Where clause to update
	 * @return {@code true} Where clause updated with success
	 * @throws NullPointerException Where clause is null
	 */
	public boolean whereByIds( Where whereClause ) throws NullPointerException {
		if( whereClause == null ) {
			throw new NullPointerException( "Where clause is null." );
		}

		try {
			processFields( getClass(), field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) && field.isAnnotationPresent( Id.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					whereClause.and( getColumnNameFromFieldName( getClass(), field.getName() ), "=", field.get( this ) );
				}
			}, false, false );

			return true;
		} catch( IllegalAccessException e ) {
			e.printStackTrace();
		}

		return false;
	}

	private Model createSuperInstance() {
		try {
			Model model = (Model) getClass().getSuperclass().newInstance();
			BeanUtils.copyProperties( model, this );
			return model;
		} catch( InstantiationException | IllegalAccessException | InvocationTargetException e ) {
			e.printStackTrace();
		}
		return null;
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
			}, false, true );
		} catch( IllegalAccessException e ) {
			throw new DatabaseException( "Unable to access model field.", e );
		} catch( NullPointerException e ) {
			throw new DatabaseException( e );
		}

		return req.insert();
	}

	private int update( Where whereClause ) throws DatabaseException {
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
			}, false, true );
		} catch( IllegalAccessException e ) {
			throw new DatabaseException( "Unable to access model field.", e );
		}

		return req.where( whereClause ).update();
	}

	/**
	 * Saves a model instance to database, using the model's attribute values.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws DatabaseException Error occurred while saving to database
	 */
	public int save() throws DatabaseException {
		if( getClass().getSuperclass() != Model.class ) {
			Model model = createSuperInstance();
			if( model != null ) {
				model.save();
			}
		}

		SQLSelect selectHelper = new SQLSelect( getClass() );

		Where whereClause = new Where();
		if( !whereByIds( whereClause ) ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}
		selectHelper.where( whereClause );

		if( selectHelper.hasAtLeastOne() ) {
			return update( whereClause );
		}

		return insert();
	}

	/**
	 * Removes the model's instance from database.
	 *
	 * @return Either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
	 * @throws DatabaseException Error occurred while deleting from database
	 */
	public int delete() throws DatabaseException {
		Where whereClause = new Where();

		if( !whereByIds( whereClause ) ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}

		if( whereClause.toString().length() == 0 ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}

		return ( new SQLDelete( getClass() ) ).where( whereClause ).delete();
	}
}
