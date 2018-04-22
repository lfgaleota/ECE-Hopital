package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.InfirmierSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.InfirmierUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.InfirmierPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau d'affichage, sélection, recherche et modification d'Infirmier
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class InfirmierPanelController  extends ModelPanelController<Infirmier> {
	/**
	 * Récupère la classe d'Infirmier
	 *
	 * @return Classe du modèle
	 */
	@Override
	public Class<Infirmier> getModelClass() {
		return Infirmier.class;
	}

	/**
	 * Construit un nouveau panneau d'affichage, sélection, recherche et modification d'Infirmier, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau d'affichage, sélection, recherche et modification associé
	 */
	@Override
	protected ModelPanel<Infirmier> buildModelPanel( TableModel<Infirmier> tableModel ) {
		return new InfirmierPanel( tableModel );
	}

	/**
	 * Créer un nouveau sélecteur SQL d'Infirmier.
	 *
	 * @return Sélecteur SQL
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Infirmier> queryCreateSelector() {
		return new SQLSelect<Infirmier>( new Class[]{ Infirmier.class, Employe.class } );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Infirmier.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return InfirmierSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour d'Infirmier.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Infirmier> createUpdateDialog( Infirmier existingModel ) {
		return InfirmierUpdateDialogController.createDialog( existingModel, this );
	}
}
