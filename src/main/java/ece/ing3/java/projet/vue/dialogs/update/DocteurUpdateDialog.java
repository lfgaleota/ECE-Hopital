package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.components.inputlists.DocteurInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Docteur de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Docteur à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class DocteurUpdateDialog extends ModelUpdateDialog<Docteur> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Docteur.
	 */
	public DocteurUpdateDialog( Docteur model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Docteur}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link DocteurInputList}
	 */
	@Override
	public ModelInputList build() {
		return new DocteurInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Docteur.
	 *
	 * @return Nouvelle instance de modèle Docteur
	 */
	@Override
	protected Docteur buildNewModel() {
		return new Docteur();
	}

	/**
	 * Récupère la classe associée au modèle Docteur.
	 *
	 * @return Classe du modèle Docteur
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}
}
