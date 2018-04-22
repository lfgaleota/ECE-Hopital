package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.Strings;

/**
 * Panneau d'affichage, sélection, recherche et modification de modèle Service
 * <p>
 * C'est le panneau principal utilisé dans les onglets de l'application.
 */
public class ServicePanel extends ModelPanel<Service> {
	/**
	 * Créer un nouveau panneau d'affichage, sélection, recherche et modification de modèle Service.
	 *
	 * @param tableModel Modèle de table {@link TableModel} à utiliser pour l'affichage des instances
	 */
	public ServicePanel( TableModel<Service> tableModel ) {
		super( Strings.getModel( "service" ), tableModel );
	}
}
