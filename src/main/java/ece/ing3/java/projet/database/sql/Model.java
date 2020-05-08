package ece.ing3.java.projet.database.sql;

import ece.ing3.java.projet.database.sql.annotations.Column;
import ece.ing3.java.projet.database.sql.annotations.ExcludedField;
import ece.ing3.java.projet.database.sql.annotations.Id;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.enumerations.Order;
import ece.ing3.java.projet.database.sql.queries.SQLDelete;
import ece.ing3.java.projet.database.sql.queries.SQLInsert;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.database.sql.queries.SQLUpdate;
import ece.ing3.java.projet.exceptions.DatabaseException;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class Model {
	private static Map<Class<? extends Model>, Map<String, String>> columnFieldNames = new HashMap<>();
	private static Map<Class<? extends Model>, Map<String, String>> fieldColumnNames = new HashMap<>();
	private static Map<Class<? extends Model>, String[]> fieldNames = new HashMap<>();
	private static Map<Class<? extends Model>, String[]> idFieldNames = new HashMap<>();
	private static Map<Class<? extends Model>, Map<String, PropertyDescriptor>> propertyDescriptors = new HashMap<>();
	private static Map<Class<? extends Model>, OrderBy> orderByIDsMap = new HashMap<>();

	private interface FieldProcessor {
		void process( Field field ) throws IllegalAccessException, NullPointerException;
	}

	/**
	 * Builds a table name from a model class.
	 *
	 * @param modelClass Model class
	 * @return Model's table name
	 */
	public static String getTableName( Class<?> modelClass ) {
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
		if( modelField.isAnnotationPresent( ExcludedField.class ) ) {
			return null;
		}

		String columnName;

		Annotation annotation = modelField.getAnnotation( Column.class );
		if( annotation != null ) {
			columnName = ( (Column) annotation ).name();
		} else {
			columnName = modelField.getName().toLowerCase();
		}

		// Append table name to column name
		columnName = getTableName( modelField.getDeclaringClass() ) + "." + columnName;

		return columnName;
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
	 * @param fieldName  Field name
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

	/**
	 * Get a field's property descriptor for a defined model class.
	 *
	 * @param modelClass Model class
	 * @param fieldName  Field to use
	 * @return Field's property descriptor
	 */
	public static PropertyDescriptor getPropertyDescriptor( Class<? extends Model> modelClass, String fieldName ) {
		if( !propertyDescriptors.containsKey( modelClass ) )
			buildPropertyDescriptors( modelClass );
		return propertyDescriptors.get( modelClass ).get( fieldName );
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

	private static void buildPropertyDescriptors( Class<? extends Model> modelClass ) {
		Map<String, PropertyDescriptor> descriptors = new HashMap<>();

		for( PropertyDescriptor descriptor : PropertyUtils.getPropertyDescriptors( modelClass ) ) {
			descriptors.put( descriptor.getName(), descriptor );
		}

		propertyDescriptors.put( modelClass, descriptors );
	}

	private static void buildNames( Class<? extends Model> modelClass ) {
		Map<String, String> columnMap = new HashMap<>();
		Map<String, String> fieldMap = new HashMap<>();
		List<String> fieldNameList = new ArrayList<>();
		List<String> idNameList = new ArrayList<>();

		try {
			processFields( modelClass, field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					columnMap.put( getColumnName( field ), field.getName() );
					fieldMap.put( field.getName(), getColumnName( field ) );
					fieldNameList.add( field.getName() );
					if( field.isAnnotationPresent( Id.class ) ) {
						idNameList.add( field.getName() );
					}
				}
			}, true, false );
		} catch( IllegalAccessException e ) {
			throw new RuntimeException( "Unable to process model " + modelClass.getName() + " fields to build field names.", e );
		}

		columnFieldNames.put( modelClass, columnMap );
		fieldColumnNames.put( modelClass, fieldMap );
		fieldNames.put( modelClass, fieldNameList.toArray( new String[ 0 ] ) );
		idFieldNames.put( modelClass, idNameList.toArray( new String[ 0 ] ) );
	}

	private static void buildOrderByIDs( Class<? extends Model> modelClass ) throws NullPointerException {
		OrderBy orderByClause = new OrderBy();

		try {
			processFields( modelClass, field -> {
				if( !field.isAnnotationPresent( ExcludedField.class ) && field.isAnnotationPresent( Id.class ) ) {
					if( !field.isAccessible() ) {
						field.setAccessible( true );
					}

					orderByClause.and( getColumnNameFromFieldName( modelClass, field.getName() ), Order.ASC );
				}
			}, false, false );

			orderByIDsMap.put( modelClass, orderByClause );
		} catch( IllegalAccessException e ) {
			throw new RuntimeException( "Unable to process model " + modelClass.getName() + " fields to get order by clause.", e );
		}
	}

	/**
	 * Get an Order By clause to order by a model's IDs in ascending order.
	 *
	 * @param modelClass Model class
	 * @return Order By clause
	 */
	public static OrderBy orderByIDs( Class<? extends Model> modelClass ) {
		if( !orderByIDsMap.containsKey( modelClass ) ) {
			buildOrderByIDs( modelClass );
		}

		return orderByIDsMap.get( modelClass );
	}

	/**
	 * Returns a list of all the ID fields's names for a defined model class.
	 *
	 * @param modelClass Model class
	 * @return List of ID fields's names
	 */
	public static String[] getIdFieldNames( Class<? extends Model> modelClass ) {
		if( !idFieldNames.containsKey( modelClass ) ) {
			buildNames( modelClass );
		}

		return idFieldNames.get( modelClass );
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

	@SuppressWarnings( "unchecked" )
	private SQLSelect createGenericIdSelector() {
		return ( new SQLSelect( getClass() ) ).setSelectedFields( getIdFieldNames( getClass() ) );
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

		for( String idFieldName : getIdFieldNames( getClass() ) ) {
			try {
				req.add( getColumnNameFromFieldName( getClass(), idFieldName ), PropertyUtils.getSimpleProperty( this, idFieldName ) );
			} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
				throw new DatabaseException( "Cannot get ID field " + idFieldName + " value in model " + getClass().getName(), e );
			}
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
			}, false, true );
		} catch( IllegalAccessException e ) {
			throw new DatabaseException( "Unable to access model field.", e );
		}

		Where whereClause = new Where();
		if( !whereByIds( whereClause ) ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
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
			Model superModel = createSuperInstance();
			if( superModel == null ) {
				throw new DatabaseException( "Cannot create super instance of " + getClass().getName() );
			}
			superModel.save();
		}

		if( exists( false ) ) {
			return update();
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
		Model superModel = null;
		if( getClass().getSuperclass() != Model.class ) {
			superModel = createSuperInstance();
			if( superModel == null ) {
				throw new DatabaseException( "Cannot create super instance of " + getClass().getName() );
			}
		}

		Where whereClause = new Where();

		if( !whereByIds( whereClause ) ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}

		if( whereClause.toString().length() == 0 ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}

		int total = ( new SQLDelete( getClass() ) ).where( whereClause ).delete();

		if( superModel != null ) {
			return total + superModel.delete();
		}

		return total;
	}

	/**
	 * Tells if the model instance already exists in the database.
	 * Returns {@code true} if a superclass instance exists.
	 *
	 * @return {@code true} Model instance exist in database.
	 */
	public boolean exists() throws DatabaseException {
		return exists( true );
	}

	/**
	 * Tells if the model instance already exists in the database.
	 *
	 * @param recursive if {@code true}, check existence for superclasses
	 * @return {@code true} Model instance exist in database. If recursive is {@code true}, returns {@code true} if existence is asserted for at least one superclass or the class itself.
	 */
	private boolean exists( boolean recursive ) throws DatabaseException {
		if( recursive && getClass().getSuperclass() != Model.class ) {
			Model superModel = createSuperInstance();
			if( superModel == null ) {
				throw new DatabaseException( "Cannot create super instance of " + getClass().getName() );
			}
			if( superModel.exists() ) {
				return true;
			}
		}

		SQLSelect selectHelper = createGenericIdSelector();

		Where whereClause = new Where();
		if( !whereByIds( whereClause ) ) {
			throw new DatabaseException( "Cannot select model by its IDs." );
		}
		selectHelper.where( whereClause );

		return selectHelper.hasAtLeastOne();
	}
}
