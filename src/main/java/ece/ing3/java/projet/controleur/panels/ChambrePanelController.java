package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ChambreSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.ChambreUpdateDialogController;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.ChambreTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ChambrePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau d'affichage, sélection, recherche et modification de Chambre
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
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

	@Override
	protected TableModel<Chambre> buildTableModel() {
		return new ChambreTableModel();
	}

	/**
	 * Construit un nouveau panneau d'affichage, sélection, recherche et modification de Chambre, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau d'affichage, sélection, recherche et modification associé
	 */
	@Override
	protected ModelPanel<Chambre> buildModelPanel( TableModel<Chambre> tableModel ) {
		return new ChambrePanel( tableModel );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Chambre> queryCreateSelector() {
		return new SQLSelect<Chambre> (
				new Class[]{ Chambre.class, Employe.class },
				new String[]{ "JOIN" },
				new String[]{ "ON chambre.surveillant = employe.numero" },
				true,
				"code_service AS codeServiceRattache", "no_chambre AS numeroChambre", "surveillant AS numeroSurveillant", "nb_lits AS nombreLits", "employe.nom AS surveillantNom", "employe.prenom AS surveillantPrenom"
		);
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
