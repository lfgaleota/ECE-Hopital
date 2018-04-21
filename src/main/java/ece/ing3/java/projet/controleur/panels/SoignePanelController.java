package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.SoigneSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.SoigneUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.modele.tables.SoigneTableModel;
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
	public Class<Soigne> getModelClass() {
		return Soigne.class;
	}

	@Override
	protected TableModel<Soigne> buildTableModel() {
		return new SoigneTableModel();
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

	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Soigne> queryCreateSelector() {
		return new SQLSelect<Soigne>(
				new Class[]{ Soigne.class, Malade.class, Docteur.class, Employe.class },
				new String[]{ "JOIN", "JOIN", "JOIN" },
				new String[]{ "ON soigne.no_malade = malade.numero", "ON soigne.no_docteur = docteur.numero", "ON docteur.numero = employe.numero" },
				true,
				"no_malade AS numeroMalade", "no_docteur AS numeroDocteur", "malade.nom AS maladeNom", "malade.prenom AS maladePrenom", "employe.nom AS docteurNom", "employe.prenom AS docteurPrenom", "docteur.specialite AS docteurSpecialite"
		);
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
		return SoigneUpdateDialogController.createDialog( existingModel, this );
	}
}

