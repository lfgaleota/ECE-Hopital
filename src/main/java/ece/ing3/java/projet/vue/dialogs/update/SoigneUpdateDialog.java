package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.components.inputlists.SoigneInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Soigne de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Soigne à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class SoigneUpdateDialog extends ModelUpdateDialog<Soigne> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Soigne.
	 */
	public SoigneUpdateDialog( Soigne model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Soigne}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link SoigneInputList}
	 */
	@Override
	public ModelInputList build() {
		return new SoigneInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Soigne.
	 *
	 * @return Nouvelle instance de modèle Soigne
	 */
	@Override
	protected Soigne buildNewModel() {
		return new Soigne();
	}

	/**
	 * Récupère la classe associée au modèle Soigne.
	 *
	 * @return Classe du modèle Soigne
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}
}
