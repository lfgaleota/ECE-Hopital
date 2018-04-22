package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

/**
 * Interface de champ de saisie abstrait, permettant de concevoir des champs de saiesies plus complexes qu'un simple champ de saisie de texte tout en masquant cette complexité aux couches supérieures.
 *
 * @param <T> Type de retour
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public interface BaseInput<T> {
	/**
	 * Retourne le nom de la colonne en base de donnée associée à ce champ de saisie.
	 *
	 * @return Nom de la colonne
	 */
	String getColumnName();

	/**
	 * Récupère la valeur actuelle du champ de saisie, ou la première des valeurs multiples s'il y en a.
	 *
	 * @return Valeur saisie actuelle
	 * @throws IllegalArgumentException La valeur saisie est invalide
	 */
	T getValue() throws IllegalArgumentException;

	/**
	 * Récupère l'ensemble des valeurs saisies.
	 *
	 * @return Valeurs saisies
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	T[] getValues() throws IllegalArgumentException;

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	void setValue( T value ) throws IllegalArgumentException;

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre, en faisant une conversion vers le type attendu.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	void setRawValue( Object value ) throws IllegalArgumentException;

	/**
	 * Retourne si le champ est actuellement rempli.
	 *
	 * @return {@code true} champ rempli
	 */
	boolean isFilled();

	/**
	 * Construit la clause Where de sélection liée à la/aux valeur(s) du champ
	 *
	 * @return Clause Where de sélection
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	default Where getWhere() throws IllegalArgumentException {
		return new Where( getColumnName(), "=", getValue() );
	}

	/**
	 * Modifie l'objet qui écoute les changements de valeurs du champ
	 *
	 * @param valueChangeListener Objet qui écoute les changements de valeurs
	 */
	void addValueChangeListener( ValueChangeListener valueChangeListener );
}
