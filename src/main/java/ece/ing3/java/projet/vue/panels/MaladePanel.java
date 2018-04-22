package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Malade
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class MaladePanel extends ModelPanel<Malade> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Malade.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public MaladePanel( TableModel<Malade> tableModel ) {
		super( Strings.getModel( "malade" ), tableModel );
	}
}
