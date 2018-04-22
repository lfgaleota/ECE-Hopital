package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.enums.Specialite;

/**
 * Champ de saisie pour les valeurs de type {@link Specialite}, avec possibilité de sélection multiple pour la recherche
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class SpecialiteInput extends EnumInput<Specialite> {
	/**
	 * Créer un nouveau champ de saisie de valeur énumération {@link Specialite}.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 */
	public SpecialiteInput( String columnName, boolean isSearch ) {
		super( columnName, isSearch );
	}

	/**
	 * Récupère les valeurs de l'énumération {@link Specialite} à utiliser et afficher
	 *
	 * @return Valeurs de l'énumération
	 */
	@Override
	protected Specialite[] getEnumValues() {
		return Specialite.values();
	}

	/**
	 * Récupère la valeur de l'énumération {@link Specialite} associée à une chaîne de caractère
	 *
	 * @param value Chaîne de caractère
	 * @return Valeur de l'énumération associée
	 */
	@Override
	protected Specialite getEnumValueOf( String value ) {
		return Specialite.valueOf( value );
	}
}
