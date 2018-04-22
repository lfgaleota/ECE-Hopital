package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Chambre
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ChambrePanel extends ModelPanel<Chambre> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Chambre.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public ChambrePanel( TableModel<Chambre> tableModel ) {
		super( Strings.getModel( "chambre" ), tableModel );
	}
}
