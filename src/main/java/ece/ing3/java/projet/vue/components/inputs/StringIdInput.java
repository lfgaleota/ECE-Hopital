package ece.ing3.java.projet.vue.components.inputs;

/**
 * Champ de saisie de valeur textuelle, avec possibilité de sélection en exact ou approximatif pour la recherche, en tant que clé primaire
 * <p>
 * Le champ sera bloqué en modification si la valeur est modifiée programmatiquement avec {@link StringIdInput#setValue(String)}
 */
public class StringIdInput extends StringInput {
	/**
	 * Créer un nouveau champ de saisie textuel, en tant que clé primaire.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 */
	public StringIdInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre. Bloque la modification du champ de saisie.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( String value ) throws IllegalArgumentException {
		super.setValue( value );
		textField.setEnabled( false );
		if( selector != null ) {
			selector.setEnabled( false );
		}
	}
}
