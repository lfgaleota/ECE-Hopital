package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Docteur
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 */
public class DocteurPanel extends ModelPanel<Docteur> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Docteur.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public DocteurPanel( TableModel<Docteur> tableModel ) {
		super( Strings.getModel( "docteur" ), tableModel );
	}
}
