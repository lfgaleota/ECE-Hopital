package ece.ing3.java.projet.vue.components.inputs;

import java.awt.*;

/**
 * Champ de saisie pour sélectionner des instances de modèle {@link ece.ing3.java.projet.modele.hopital.Chambre}, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche, en tant que clé primaire
 */
public class ChambreIdInput extends ChambreInput {
	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle {@link ece.ing3.java.projet.modele.hopital.Chambre}, en tant que clé primaire.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public ChambreIdInput( String columnName, boolean isSearch, Window parent ) {
		super( columnName, isSearch, parent );
	}

	/**
	 * Remplace la valeur de la sélection par la valeur passée en paramètre. Bloque la modification du champ.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( Long value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( search != null ) {
			search.setEnabled( false );
		}
	}
}
