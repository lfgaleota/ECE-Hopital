package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Infirmier
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 */
public class InfirmierPanel extends ModelPanel<Infirmier> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Infirmier.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public InfirmierPanel( TableModel<Infirmier> tableModel ) {
		super( Strings.getModel( "infirmier" ), tableModel );
	}
}
