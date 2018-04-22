package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.components.inputlists.ServiceInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la mise à jour d'instance de modèle Service de la base de donnée.
 * <p>
 * Construit un ensemble d'instance de modèle Service à partir de la saisie utilisateur.
 * </p>
 *
 * @implNote Il s'agit du modèle qui fera la différence entre mise à jour et insertion dans la base de donnée.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ServiceUpdateDialog extends ModelUpdateDialog<Service> {
	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de modèle Service.
	 */
	public ServiceUpdateDialog( Service model ) {
		super( model );
	}

	/**
	 * Construit la liste des champs de saisie associée au modèle {@link Service}
	 *
	 * @return Liste des champs de saisie
	 * @implNote On utilise ici {@link ServiceInputList}
	 */
	@Override
	public ModelInputList build() {
		return new ServiceInputList( false, this );
	}

	/**
	 * Construit une nouvelle instance de modèle Service.
	 *
	 * @return Nouvelle instance de modèle Service
	 */
	@Override
	protected Service buildNewModel() {
		return new Service();
	}

	/**
	 * Récupère la classe associée au modèle Service.
	 *
	 * @return Classe du modèle Service
	 */
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}
}
