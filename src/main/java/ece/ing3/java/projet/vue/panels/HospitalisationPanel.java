package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Hospitalisation
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class HospitalisationPanel extends ModelPanel<Hospitalisation> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Hospitalisation.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public HospitalisationPanel( TableModel<Hospitalisation> tableModel ) {
		super( Strings.getModel( "hospitalisation" ), tableModel );
	}
}
