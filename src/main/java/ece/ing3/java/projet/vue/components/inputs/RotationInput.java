package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.enums.Rotation;

/**
 * Champ de saisie pour les valeurs de type {@link Rotation}, avec possibilité de sélection multiple pour la recherche
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class RotationInput extends EnumInput<Rotation> {
	/**
	 * Créer un nouveau champ de saisie de valeur énumération {@link Rotation}.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 */
	public RotationInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	/**
	 * Récupère les valeurs de l'énumération {@link Rotation} à utiliser et afficher
	 *
	 * @return Valeurs de l'énumération
	 */
	@Override
	protected Rotation[] getEnumValues() {
		return Rotation.values();
	}

	/**
	 * Récupère la valeur de l'énumération {@link Rotation} associée à une chaîne de caractère
	 *
	 * @param value Chaîne de caractère
	 * @return Valeur de l'énumération associée
	 */
	@Override
	protected Rotation getEnumValueOf( String value ) {
		return Rotation.valueOf( value );
	}
}
