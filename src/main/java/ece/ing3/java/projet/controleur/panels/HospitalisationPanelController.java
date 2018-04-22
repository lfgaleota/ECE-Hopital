package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.HospitalisationSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.HospitalisationUpdateDialogController;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.HospitalisationTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.HospitalisationPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau d'affichage, sélection, recherche et modification d'Hospitalisation
 */
public class HospitalisationPanelController extends ModelPanelController<Hospitalisation> {
	/**
	 * Récupère la classe d'Hospitalisation
	 *
	 * @return Classe du modèle
	 */
	@Override
	public Class<Hospitalisation> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected TableModel<Hospitalisation> buildTableModel() {
		return new HospitalisationTableModel();
	}

	/**
	 * Construit un nouveau panneau d'affichage, sélection, recherche et modification d'Hospitalisation, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau d'affichage, sélection, recherche et modification associé
	 */
	@Override
	protected ModelPanel<Hospitalisation> buildModelPanel( TableModel<Hospitalisation> tableModel ) {
		return new HospitalisationPanel( tableModel );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Hospitalisation> queryCreateSelector() {
		return new SQLSelect<Hospitalisation> (
				new Class[]{ Hospitalisation.class, Malade.class },
				new String[]{ "JOIN" },
				new String[]{ "ON hospitalisation.no_malade = malade.numero" },
				true,
				"no_malade AS numeroMalade", "code_service AS codeService", "no_chambre AS numeroChambre", "lit AS numeroLit", "malade.nom AS maladeNom", "malade.prenom AS maladePrenom"
		);
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
		return HospitalisationUpdateDialogController.createDialog( existingModel, this );
	}
}
