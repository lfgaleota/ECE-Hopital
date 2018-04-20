package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.delete.ModelDeleteDialogController;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.workers.ModelDeleteWorker;
import ece.ing3.java.projet.workers.ModelQueryWorker;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.interfaces.ModelQueryWorkerProvider;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.delete.ModelDeleteDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.workers.ModelUpdateWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ModelPanelController<M extends Model> implements ActionListener, DialogListener, ModelQueryWorkerProvider<M>, ModelWorkerProvider {
	protected ModelPanel<M> panel;
	protected TableModel<M> tableModel;

	protected Where whereClause;
	protected OrderBy orderByClause;

	protected ModelSearchDialog dialogSearch;
	protected ModelDeleteDialog dialogDelete;
	protected ModelUpdateDialog<M> dialogUpdate;

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

	@Override
	public Where queryModifyWhereClause( Where whereClause ) {
		return whereClause;
	}

	@Override
	public SQLSelect<M> queryCreateSelector() {
		return new SQLSelect<>( getModelClass() );
	}

	public ModelPanel<M> getPanel() {
		return panel;
	}

	@Override
	public TableModel<M> getTableModel() {
		return tableModel;
	}

	@Override
	public Where getWhereClause() {
		return whereClause;
	}

	@Override
	public OrderBy getOrderByClause() {
		return orderByClause;
	}

	public abstract ModelSearchDialog createSearchDialog();
	public abstract ModelUpdateDialog<M> createUpdateDialog( M existingModel );

	public ModelDeleteDialog createDeleteDialog() {
		return ModelDeleteDialogController.createDialog( panel.getList().getSelecteds(), this );
	}

	public void update() {
		panel.inUpdate();
		( new ModelQueryWorker<>( this ) ).execute();
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
		} else if( actionEvent.getSource() == panel.getToolbar().getButtonAdd() ) {
			if( dialogUpdate == null ) {
				dialogUpdate = createUpdateDialog( null );
			} else {
				dialogUpdate.toFront();
			}
		} else if( actionEvent.getSource() == panel.getToolbar().getButtonModify() ) {
			if( dialogUpdate == null ) {
				dialogUpdate = createUpdateDialog( getPanel().getList().getSelected() );
			} else {
				dialogUpdate.toFront();
			}
		} else if( actionEvent.getSource() == panel.getToolbar().getButtonRefresh() ) {
			update();
		}
	}

	@Override
	public void onDialogSubmitted( JDialog dialog ) {
		if( dialog == dialogSearch ) {
			if( !dialogSearch.isResetFilters() ) {
				whereClause = dialogSearch.getWhereClause();
			} else {
				whereClause = null;
			}
			dialogSearch = null;
			update();
		} else if( dialog == dialogDelete ) {
			panel.inUpdate();
			( new ModelDeleteWorker( dialogDelete.getSelectedModels(), this ) ).execute();
			dialogDelete = null;
		} else if( dialog == dialogUpdate ) {
			panel.inUpdate();
			( new ModelUpdateWorker( dialogUpdate.buildModel(), this ) ).execute();
			dialogUpdate = null;
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
		if( dialog == dialogUpdate ) {
			dialogUpdate = null;
		}
	}

	@Override
	public void queryOnFinish() {
		panel.outOfUpdate();
	}

	@Override
	public void workerOnFinish() {
		queryOnFinish();
		update();
	}
}
