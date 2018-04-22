package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.inputlists.ChambreInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Chambre de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Chambre à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 */
public class ChambreUpdateDialog extends ModelUpdateDialog<Chambre> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Chambre.
	 */
	public ChambreUpdateDialog( Chambre model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Chambre}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link ChambreInputList}
	 */
	@Override
	public ModelInputList build() {
		return new ChambreInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Chambre.
	 *
	 * @return Nouvelle instance de modèle Chambre
	 */
	@Override
	protected Chambre buildNewModel() {
		return new Chambre();
	}

	/**
	 * Récupère la classe associée au modèle Chambre.
	 *
	 * @return Classe du modèle Chambre
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}
}
