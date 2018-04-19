package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.delete.ModelDeleteDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.delete.ModelDeleteDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public abstract class ModelPanelController<M extends Model> implements ActionListener, DialogListener {
	protected ModelPanel<M> panel;
	protected TableModel<M> tableModel;
	protected Where whereClause;
	protected OrderBy orderByClause;

	protected ModelSearchDialog dialogSearch;
	protected ModelDeleteDialog dialogDelete;

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

	protected Where modifyWhereClause( Where whereClause ) {
		return whereClause;
	}

	protected SQLSelect<M> createSelector() {
		return new SQLSelect<>( getModelClass() );
	}

	private SwingWorker<List<M>, Object> createUpdateWorker() {
		return new SwingWorker<List<M>, Object>() {
			@Override
			protected List<M> doInBackground() {
				System.out.println( "Update started" );
				try {
					SQLSelect<M> sql = createSelector();
					whereClause = modifyWhereClause( whereClause );
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

	private SwingWorker<Boolean, Object> createDeleteWorker( final List<? extends Model> selectedModels ) {
		return new SwingWorker<Boolean, Object>() {
			@Override
			protected Boolean doInBackground() throws Exception {
				boolean success = true;
				for( Model model : selectedModels ) {
					try {
						model.delete();
					} catch( DatabaseException e ) {
						success = false;
						e.printStackTrace();
					}
				}
				return success;
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

	public ModelDeleteDialog createDeleteDialog() {
		return ModelDeleteDialogController.createDialog( panel.getList().getSelecteds(), this );
	}

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
		} else if( actionEvent.getSource() == panel.getToolbar().getButtonRemove() ) {
			if( dialogDelete == null ) {
				dialogDelete = createDeleteDialog();
			} else {
				dialogDelete.toFront();
			}
		}
	}

	@Override
	public void onDialogSubmitted( JDialog dialog ) {
		if( dialog == dialogSearch ) {
			whereClause = dialogSearch.getWhereClause();
			System.out.println( "Where clause : " + whereClause );
			dialogSearch = null;
			update();
		} else if( dialog == dialogDelete ) {
			panel.inUpdate();
			createDeleteWorker( dialogDelete.getSelectedModels() ).execute();
			dialogDelete = null;
			update();
		}
	}

	@Override
	public void onDialogCancelled( JDialog dialog ) {
		if( dialog == dialogSearch ) {
			dialogSearch = null;
		}
		if( dialog == dialogDelete ) {
			dialogDelete = null;
		}
	}
}
