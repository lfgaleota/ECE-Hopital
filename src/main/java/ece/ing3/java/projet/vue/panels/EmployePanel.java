package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Employe
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class EmployePanel extends ModelPanel<Employe> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Employe.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public EmployePanel( TableModel<Employe> tableModel ) {
		super( Strings.getModel( "employe" ), tableModel );
	}
}
