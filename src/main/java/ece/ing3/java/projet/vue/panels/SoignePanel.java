package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Soigne
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class SoignePanel extends ModelPanel<Soigne> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Soigne.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public SoignePanel( TableModel<Soigne> tableModel ) {
		super( Strings.getModel( "soigne" ), tableModel );
	}
}
