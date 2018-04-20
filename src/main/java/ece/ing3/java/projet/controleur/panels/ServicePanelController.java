package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ServiceSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.ServiceUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.vue.panels.ServicePanel;

/**
 * Base de contrôleur de panneau principal de Service
 */
public class ServicePanelController extends ModelPanelController<Service> {
	/**
	 * Récupère la classe de Service
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}

	/**
	 * Construit un nouveau panneau principal de Service, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Service> buildModelPanel( TableModel<Service> tableModel ) {
		return new ServicePanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Service.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return ServiceSearchDialogController.createDialog(this);
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Service.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Service> createUpdateDialog( Service existingModel ) {
		return ServiceUpdateDialogController.createDialog( existingModel, this );
	}
}
