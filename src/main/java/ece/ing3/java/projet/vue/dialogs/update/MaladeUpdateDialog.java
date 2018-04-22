package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.components.inputlists.MaladeInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Malade de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Malade à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 */
public class MaladeUpdateDialog extends ModelUpdateDialog<Malade> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Malade.
	 */
	public MaladeUpdateDialog( Malade model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Malade}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link MaladeInputList}
	 */
	@Override
	public ModelInputList build() {
		return new MaladeInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Malade.
	 *
	 * @return Nouvelle instance de modèle Malade
	 */
	@Override
	protected Malade buildNewModel() {
		return new Malade();
	}

	/**
	 * Récupère la classe associée au modèle Malade.
	 *
	 * @return Classe du modèle Malade
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}
}
