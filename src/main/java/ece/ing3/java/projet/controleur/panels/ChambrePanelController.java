package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ChambreSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.ChambreUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ChambrePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau principal de Chambre
 */
public class ChambrePanelController extends ModelPanelController<Chambre> {
	/**
	 * Récupère la classe de Chambre
	 *
	 * @return Classe du modèle
	 */
	@Override
	public Class<Chambre> getModelClass() {
		return Chambre.class;
	}

	/**
	 * Construit un nouveau panneau principal de Chambre, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Chambre> buildModelPanel( TableModel<Chambre> tableModel ) {
		return new ChambrePanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Chambre.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return ChambreSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Chambre.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Chambre> createUpdateDialog( Chambre existingModel ) {
		return ChambreUpdateDialogController.createDialog( existingModel, this );
	}
}
