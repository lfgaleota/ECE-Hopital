package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.delete.ModelDeleteDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.OrderBy;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.interfaces.ModelQueryWorkerProvider;
import ece.ing3.java.projet.interfaces.ModelWorkerProvider;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.delete.ModelDeleteDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.workers.ModelDeleteWorker;
import ece.ing3.java.projet.workers.ModelQueryWorker;
import ece.ing3.java.projet.workers.ModelUpdateWorker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Base de contrôleur de panneau principal de modèle générique
 *
 * @param <M> Type de modèle à utiliser
 */
public abstract class ModelPanelController<M extends Model> implements ActionListener, DialogListener, ModelQueryWorkerProvider<M>, ModelWorkerProvider {
	/**
	 * Panneau associé
	 */
	protected ModelPanel<M> panel;
	/**
	 * Modèle de table associé
	 */
	protected TableModel<M> tableModel;

	/**
	 * Clause Where de sélection à utiliser
	 */
	protected Where whereClause;
	/**
	 * Clause OrderBy de sélection à utiliser
	 */
	protected OrderBy orderByClause;

	/**
	 * Boîte de dialogue de recherche associée
	 */
	protected ModelSearchDialog dialogSearch;
	/**
	 * Boîte de dialogue de suppression associée
	 */
	protected ModelDeleteDialog dialogDelete;
	/**
	 * Boîte de dialogue de mise à jour associée
	 */
	protected ModelUpdateDialog<M> dialogUpdate;

	/**
	 * Créer un nouveau contrôleur de panneau
	 */
	protected ModelPanelController() {
		this.whereClause = null;
		this.orderByClause = null;
		this.tableModel = buildTableModel();
		this.panel = buildModelPanel( this.tableModel );
		this.panel.getToolbar().addActionListener( this );
		this.update();
	}

	/**
	 * Récupère la classe associée au modèle utilisé
	 *
	 * @return Classe du modèle
	 */
	protected abstract Class<? extends Model> getModelClass();

	/**
	 * Construit un nouveau modèle de table pour le modèle utilisé
	 *
	 * @return Modèle de table construit associé
	 */
	protected TableModel<M> buildTableModel() {
		return new TableModel<>( getModelClass() );
	}

	/**
	 * Construit un nouveau panneau principal associé au modèle, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	protected abstract ModelPanel<M> buildModelPanel( TableModel<M> tableModel );

	/**
	 * Modifie la clause Where avant exécution de la mise à jour des données.
	 *
	 * @param whereClause Clause Where à modifier
	 * @return Nouvelle clause Where
	 */
	@Override
	public Where queryModifyWhereClause( Where whereClause ) {
		return whereClause;
	}

	/**
	 * Modifie la clause OrderBy avant exécution de la mise à jour des données.
	 * Ordonne par défaut selon les IDs des modèles.
	 *
	 * @param orderByClause Clause OrderBy à modifier
	 * @return Nouvelle clause OrderBy
	 */
	@Override
	public OrderBy queryModifyOrderByClause( OrderBy orderByClause ) {
		return ( orderByClause != null ? orderByClause : Model.orderByIDs( getModelClass() ) );
	}

	/**
	 * Créer un nouveau sélecteur SQL associé au modèle.
	 *
	 * @return Sélecteur SQL
	 */
	@Override
	public SQLSelect<M> queryCreateSelector() {
		return new SQLSelect<>( getModelClass() );
	}

	/**
	 * Récupère le panneau principal associé.
	 *
	 * @return Panneau principal
	 */
	public ModelPanel<M> getPanel() {
		return panel;
	}

	/**
	 * Récupère le modèle de table associé.
	 *
	 * @return Modèle de table associé
	 */
	@Override
	public TableModel<M> getTableModel() {
		return tableModel;
	}

	/**
	 * Récupère la clause Where actuelle, non-modifiée.
	 *
	 * @return Clause Where actuelle
	 */
	@Override
	public Where getWhereClause() {
		return whereClause;
	}

	/**
	 * Récupère la clause OrderBy actuelle, non-modifiée.
	 *
	 * @return Clause OrderBy actuelle
	 */
	@Override
	public OrderBy getOrderByClause() {
		return orderByClause;
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche associée au modèle.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	public abstract ModelSearchDialog createSearchDialog();

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour associée au modèle.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	public abstract ModelUpdateDialog<M> createUpdateDialog( M existingModel );

	/**
	 * Créer une nouvelle boîte de dialogue de suppression associée au modèle.
	 *
	 * @return Boîte de dialogue de suppression
	 */
	public ModelDeleteDialog createDeleteDialog() {
		return ModelDeleteDialogController.createDialog( panel.getList().getSelecteds(), this );
	}

	/**
	 * Récupère et met à jour la liste affichée par rapport à la base de donnée.
	 */
	public void update() {
		panel.inUpdate();
		( new ModelQueryWorker<>( this ) ).execute();
	}

	/**
	 * Méthode réagissant au choix de l'utilisateur sur le panneau (notamment la barre d'outils).
	 *
	 * @param actionEvent Événement d'action
	 */
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

	/**
	 * Méthode régissant à la validation d'une boîte de dialogue.
	 *
	 * @param dialog Boîte de dialogue validée
	 */
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
			( new ModelUpdateWorker( dialogUpdate.buildModel(), this, dialogUpdate.isAdd() ) ).execute();
			dialogUpdate = null;
		}
	}

	/**
	 * Méthode réagissant à l'annulation d'une boîte de dialogue.
	 *
	 * @param dialog Boîte de dialogue annulée
	 */
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

	/**
	 * Actions à effectuer à la fin d'une requête de mise à jour.
	 */
	@Override
	public void queryOnFinish() {
		panel.outOfUpdate();
	}


	/**
	 * Actions à effectuer à la fin d'une requête autre que de mise à jour.
	 */
	@Override
	public void workerOnFinish() {
		queryOnFinish();
		update();
	}
}
