package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.MaladeSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.MaladeUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.MaladePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau principal de Malade
 */
public class MaladePanelController extends ModelPanelController<Malade> {
	/**
	 * Récupère la classe de Malade
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}

	/**
	 * Construit un nouveau panneau principal de Malade, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Malade> buildModelPanel( TableModel<Malade> tableModel ) {
		return new MaladePanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Malade.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return MaladeSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Malade.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Malade> createUpdateDialog( Malade existingModel ) {
		return MaladeUpdateDialogController.createDialog( existingModel, this );
	}
}
