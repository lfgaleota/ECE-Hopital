package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.HospitalisationSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.HospitalisationPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau principal d'Hospitalisation
 */
public class HospitalisationPanelController extends ModelPanelController<Hospitalisation> {
	/**
	 * Récupère la classe d'Hospitalisation
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	/**
	 * Construit un nouveau panneau principal d'Hospitalisation, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Hospitalisation> buildModelPanel( TableModel<Hospitalisation> tableModel ) {
		return new HospitalisationPanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Hospitalisation.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return HospitalisationSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour d'Hospitalisation.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Hospitalisation> createUpdateDialog( Hospitalisation existingModel ) {
		return null;
	}
}
