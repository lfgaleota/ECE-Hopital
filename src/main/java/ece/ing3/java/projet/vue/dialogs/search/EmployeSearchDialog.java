package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.EmployeInputList;

/**
 * Boîte de dialogue pour la recherche et filtrage d'instance de modèle {@link ece.ing3.java.projet.modele.employe.Employe} de la base de donnée, avec la saisie des attributs à filtrer.
 * <p>
 * Génère une clause {@link ece.ing3.java.projet.database.sql.clauses.Where} pour le filtrage des résultats dans une requête.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class EmployeSearchDialog extends ModelSearchDialog {
	/**
	 * Construit la liste des champs de saisie pour le filtrage associé à {@link ece.ing3.java.projet.modele.employe.Employe}
	 *
	 * @return Liste des champs de saisie pour le filtrage
	 * @implNote On utilise ici {@link EmployeInputList}
	 */
	@Override
	public ModelInputList build() {
		return new EmployeInputList( true, this );
	}
}
