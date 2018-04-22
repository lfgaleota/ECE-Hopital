package ece.ing3.java.projet.vue.components.inputs;

/**
 * Champ de saisie pour les valeurs numériques entières longues, avec possibilité de sélection selon plusieurs types de comparateurs pour la recherche, en tant que clé primaire
 */
public class LongIdInput extends LongInput {
	/**
	 * Créer un nouveau champ de saisie de valeur numérique entière longue, en tant que clé primaire.
	 *
	 * @param isSearch {@code true} si le champ est utilisé pour de la recherche
	 */
	public LongIdInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre. Bloque la modification du champ de saisie.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( Long value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( selector != null ) {
			selector.setEnabled( false );
		}
	}
}
