package ece.ing3.java.projet.vue.components.inputs;

import java.awt.*;

/**
 * Champ de saisie pour sélectionner des instances de modèle {@link ece.ing3.java.projet.modele.administration.Service}, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche, en tant que clé primaire
 */
public class ServiceIdInput extends ServiceInput {
	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle {@link ece.ing3.java.projet.modele.administration.Service}, en tant que clé primaire.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public ServiceIdInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	/**
	 * Remplace la valeur de la sélection par la valeur passée en paramètre. Bloque la modification du champ.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( String value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( search != null ) {
			search.setEnabled( false );
		}
	}
}
