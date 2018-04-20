package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.DocteurSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.DocteurUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.DocteurPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau principal de Docteur
 */
public class DocteurPanelController  extends ModelPanelController<Docteur> {
	/**
	 * Récupère la classe de Docteur
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}

	/**
	 * Construit un nouveau panneau principal de Docteur, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Docteur> buildModelPanel( TableModel<Docteur> tableModel ) {
		return new DocteurPanel( tableModel );
	}

	/**
	 * Créer un nouveau sélecteur SQL de Docteur.
	 *
	 * @return Sélecteur SQL
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Docteur> queryCreateSelector() {
		return new SQLSelect<Docteur>( new Class[]{ Docteur.class, Employe.class } );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Docteur.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return DocteurSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Docteur.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Docteur> createUpdateDialog( Docteur existingModel ) {
		return DocteurUpdateDialogController.createDialog( existingModel, this );
	}
}
