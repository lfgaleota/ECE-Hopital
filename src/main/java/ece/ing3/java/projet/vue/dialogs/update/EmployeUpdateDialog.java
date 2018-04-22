package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.vue.components.inputlists.EmployeInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Employe de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Employe à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class EmployeUpdateDialog extends ModelUpdateDialog<Employe> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Employe.
	 */
	public EmployeUpdateDialog( Employe model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Employe}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link EmployeInputList}
	 */
	@Override
	public ModelInputList build() {
		return new EmployeInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Employe.
	 *
	 * @return Nouvelle instance de modèle Employe
	 */
	@Override
	protected Employe buildNewModel() {
		return new Employe();
	}

	/**
	 * Récupère la classe associée au modèle Employe.
	 *
	 * @return Classe du modèle Employe
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Employe.class;
	}
}
