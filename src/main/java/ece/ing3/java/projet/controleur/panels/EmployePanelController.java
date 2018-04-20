package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.EmployeSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.EmployePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

/**
 * Base de contrôleur de panneau principal d'Employe
 */
public class EmployePanelController  extends ModelPanelController<Employe> {
	/**
	 * Récupère la classe d'Employe
	 *
	 * @return Classe du modèle
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Employe.class;
	}

	/**
	 * Construit un nouveau panneau principal d'Employe, utilisant le modèle de table fourni
	 *
	 * @param tableModel Modèle de table à utiliser
	 * @return Panneau principal associé
	 */
	@Override
	protected ModelPanel<Employe> buildModelPanel( TableModel<Employe> tableModel ) {
		return new EmployePanel( tableModel );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Employe.
	 *
	 * @return Boîte de dialogue de recherche
	 */
	@Override
	public ModelSearchDialog createSearchDialog() {
		return EmployeSearchDialogController.createDialog( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour d'Employe.
	 *
	 * @param existingModel Instance de modèle BDD existant ou {@code null}
	 * @return Boîte de dialogue de mise à jour
	 */
	@Override
	public ModelUpdateDialog<Employe> createUpdateDialog( Employe existingModel ) {
		return null;
	}

	/**
	 * Modifie la clause Where avant exécution de la mise à jour des données.
	 * Ici, on exclue les Infirmiers et les Employes.
	 *
	 * @param whereClause Clause Where à modifier
	 * @return Nouvelle clause Where
	 */
	public Where queryModifyWhereClause( Where whereClause ) {
		if( whereClause == null ) {
			whereClause = new Where();
		}
		whereClause.and( "numero", "NOT IN", new SQLSelect<Infirmier>( Infirmier.class, "numero" ) );
		whereClause.and( "numero", "NOT IN", new SQLSelect<Employe>( Employe.class, "numero" ) );
		return whereClause;
	}
}

