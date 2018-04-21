package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ServiceSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.ServiceUpdateDialogController;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.ServiceTableModel;
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
	public Class<Service> getModelClass() {
		return Service.class;
	}

	@Override
	protected TableModel<Service> buildTableModel() {
		return new ServiceTableModel();
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

	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Service> queryCreateSelector() {
		return new SQLSelect<Service> (
				new Class[]{ Service.class, Employe.class },
				new String[]{ "JOIN" },
				new String[]{ "ON service.directeur = employe.numero" },
				true,
				"code AS code", "service.nom AS nom", "batiment AS batiment", "directeur AS numeroDirecteur", "employe.nom AS directeurNom", "employe.prenom AS directeurPrenom"
		);
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
