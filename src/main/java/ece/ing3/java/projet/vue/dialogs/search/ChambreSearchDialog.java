package ece.ing3.java.projet.vue.dialogs.search;

import ece.ing3.java.projet.vue.components.inputlists.ChambreInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

/**
 * Boîte de dialogue pour la recherche et filtrage d'instance de modèle {@link ece.ing3.java.projet.modele.hopital.Chambre} de la base de donnée, avec la saisie des attributs à filtrer.
 * <p>
 * Génère une clause {@link ece.ing3.java.projet.database.sql.clauses.Where} pour le filtrage des résultats dans une requête.
 */
public class ChambreSearchDialog extends ModelSearchDialog {
	/**
	 * Construit la liste des champs de saisie pour le filtrage associé à {@link ece.ing3.java.projet.modele.hopital.Chambre}
	 *
	 * @return Liste des champs de saisie pour le filtrage
	 * @implNote On utilise ici {@link ChambreInputList}
	 */
	@Override
	public ModelInputList build() {
		return new ChambreInputList( true, this );
	}

}
