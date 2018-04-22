package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputlists.HospitalisationInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Hospitalisation de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Hospitalisation à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class HospitalisationUpdateDialog extends ModelUpdateDialog<Hospitalisation> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Hospitalisation.
	 */
	public HospitalisationUpdateDialog( Hospitalisation model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Hospitalisation}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link HospitalisationInputList}
	 */
	@Override
	public ModelInputList build() {
		return new HospitalisationInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Hospitalisation.
	 *
	 * @return Nouvelle instance de modèle Hospitalisation
	 */
	@Override
	protected Hospitalisation buildNewModel() {
		return new Hospitalisation();
	}

	/**
	 * Récupère la classe associée au modèle Hospitalisation.
	 *
	 * @return Classe du modèle Hospitalisation
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}
}
