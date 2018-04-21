package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.tables.TableModel;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ModelList<M extends Model> extends JTable {
	private TableModel<M> tableModel;

	public ModelList( TableModel<M> tableModel ) {
		super( tableModel );
		this.tableModel = tableModel;
		setAutoCreateRowSorter( true );
	}

	public M getSelected() {
		if( getSelectedRow() > -1 ) {
			return tableModel.getList().get( getSelectedRow() );
		}

		return null;
	}

	public List<M> getSelecteds() {
		M instance;
		List<M> list = new LinkedList<>();

		for( int row : getSelectedRows() ) {
			instance = tableModel.getList().get( row );

			if( instance != null ) {
				list.add( instance );
			}
		}

		return list;
	}
}
