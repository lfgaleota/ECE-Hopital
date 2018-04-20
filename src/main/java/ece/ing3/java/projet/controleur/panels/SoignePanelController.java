package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.SoigneSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.vue.panels.SoignePanel;

/**
 * Base de contrôleur de panneau principal de Prise en Charge
 */
public class SoignePanelController extends ModelPanelController<Soigne> {
	/**
	 * Récupère la classe de Prise en Charge
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}

	/**
	 * Construit un nouveau panneau principal de Prise en Charge, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Soigne> buildModelPanel( TableModel<Soigne> tableModel ) {
		return new SoignePanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Prise en Charge.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return SoigneSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Prise en Charge.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Soigne> createUpdateDialog( Soigne existingModel ) {
		return null;
	}
}

