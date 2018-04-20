package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import org.apache.commons.beanutils.PropertyUtils;

import javax.swing.table.AbstractTableModel;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class TableModel<M extends Model> extends AbstractTableModel {
	private Class<? extends Model> modelClass;
	private List<M> instances;
	private String[] fieldNames;

	public TableModel( Class<? extends Model> modelClass ) {
		this.modelClass = modelClass;
		fieldNames = Model.getFieldNames( getModelClass() );
	}

	public List<M> getList() {
		return instances;
	}

	public void setList( List<M> instances ) {
		this.instances = instances;
		fireTableStructureChanged();
		fireTableDataChanged();
	}

	@Override
	public String getColumnName( int col ) {
		return Strings.get( Constants.MODEL_STRINGS_PREFIX + fieldNames[ col ] );
	}

	@Override
	public Class<?> getColumnClass( int columnIndex ) {
		PropertyDescriptor desc = Model.getPropertyDescriptor( getModelClass(), fieldNames[ columnIndex ] );
		return ( desc != null ? desc.getPropertyType() : Object.class );
	}

	@Override
	public int getRowCount() {
		return instances != null ? instances.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return fieldNames.length;
	}

	@Override
	public Object getValueAt( int row, int col ) {
		M s = instances.get( row );

		try {
			return PropertyUtils.getSimpleProperty( s, fieldNames[ col ] );
		} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			e.printStackTrace();
		}

		return null;
	}

	public Class<? extends Model> getModelClass() {
		return modelClass;
	}
}
