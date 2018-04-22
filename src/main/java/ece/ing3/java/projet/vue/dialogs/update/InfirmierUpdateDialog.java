package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.components.inputlists.InfirmierInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Infirmier de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Infirmier à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 */
public class InfirmierUpdateDialog extends ModelUpdateDialog<Infirmier> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Infirmier.
	 */
	public InfirmierUpdateDialog( Infirmier model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Infirmier}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link InfirmierInputList}
	 */
	@Override
	public ModelInputList build() {
		return new InfirmierInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Infirmier.
	 *
	 * @return Nouvelle instance de modèle Infirmier
	 */
	@Override
	protected Infirmier buildNewModel() {
		return new Infirmier();
	}

	/**
	 * Récupère la classe associée au modèle Infirmier.
	 *
	 * @return Classe du modèle Infirmier
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}
}
