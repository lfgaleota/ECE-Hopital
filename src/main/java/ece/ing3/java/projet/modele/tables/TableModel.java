package ece.ing3.java.projet.modele.tables;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class TableModel<M extends Model> extends AbstractTableModel {
	List<M> instances;
	protected static String[] fieldNames;

	public TableModel() {
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

	protected abstract Class<? extends Model> getModelClass();

	@Override
	public String getColumnName( int col ) {
		return Strings.get( Constants.MODEL_STRINGS_PREFIX + fieldNames[ col ] );
	}

	@Override
	public int getRowCount() {
		return instances != null ? instances.size() : 0;
	}

	@Override
	public int getColumnCount() {
		return fieldNames.length;
	}
}
