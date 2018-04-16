package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class ModelPanelController<M extends Model> implements ActionListener {
	protected ModelPanel<M> panel;
	protected TableModel<M> tableModel;
	protected Where whereClause;
	protected OrderBy orderByClause;

	protected ModelSearchDialog dialogSearch;

	protected ModelPanelController() {
		this.whereClause = null;
		this.orderByClause = null;
		this.tableModel = buildTableModel();
		this.panel = buildModelPanel( this.tableModel );
		this.panel.getToolbar().addActionListener( this );
		this.update();
	}

	protected abstract Class<? extends Model> getModelClass();
	protected abstract TableModel<M> buildTableModel();
	protected abstract ModelPanel<M> buildModelPanel( TableModel<M> tableModel );

	private SwingWorker<List<M>, Object> createUpdateWorker() {
		return new SwingWorker<List<M>, Object>() {
			@Override
			protected List<M> doInBackground() {
				System.out.println( "Update started" );
				try {
					SQLSelect<M> sql = ( new SQLSelect<>( getModelClass() ) );
					if( whereClause != null ) {
						sql.where( whereClause );
					}
					if( orderByClause != null ) {
						sql.orderBy( orderByClause );
					}
					List<M> list = sql.findList();
					System.out.println( "Update got : " + list );
					return list;
				} catch( DatabaseException e ) {
					e.printStackTrace();
				}
				System.out.println( "Update failed" );
				return null;
			}

			@Override
			protected void done() {
				try {
					System.out.println( "Set list in model : " + get() );
					if( get() != null ) {
						tableModel.setList( get() );
					} else {
						Utils.error( "Erreur de récupération des données." );
					}
				} catch( InterruptedException | ExecutionException e ) {
					e.printStackTrace();
					Utils.error( "Erreur de récupération des données." );
				}
				panel.outOfUpdate();
			}
		};
	}

	public ModelPanel<M> getPanel() {
		return panel;
	}

	public TableModel<M> getTableModel() {
		return tableModel;
	}

	public abstract ModelSearchDialog createSearchDialog();

	public void update() {
		panel.inUpdate();
		SwingWorker<List<M>, Object> worker = createUpdateWorker();
		worker.execute();
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == panel.getToolbar().getButtonSearch() ) {
			if( dialogSearch == null ) {
				dialogSearch = createSearchDialog();
			} else {
				dialogSearch.toFront();
			}
		}
	}
}
