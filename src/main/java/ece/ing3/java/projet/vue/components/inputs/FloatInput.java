package ece.ing3.java.projet.vue.components.inputs;

/**
 * Champ de saisie pour les valeurs numériques flottantes, avec possibilité de sélection selon plusieurs types de comparateurs pour la recherche
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class FloatInput extends NumericInput<Float> {
	private String columnName;

	/**
	 * Créer un nouveau champ de saisie de valeur numérique flottante.
	 *
	 * @param isSearch {@code true} si le champ est utilisé pour de la recherche
	 */
	public FloatInput( String columnName, boolean isSearch ) {
		super( isSearch );
		this.columnName = columnName;
	}

		/**
	 * Retourne le nom de la colonne en base de donnée associée à ce champ de saisie.
	 *
	 * @return Nom de la colonne
	 */
	@Override
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Récupère la valeur actuelle du champ de saisie, ou la première des valeurs multiples s'il y en a.
	 *
	 * @return Valeur saisie actuelle
	 * @throws IllegalArgumentException La valeur saisie est invalide
	 */
	@Override
	public Float getValue() throws IllegalArgumentException {
		try {
			return Float.valueOf( getTextValue() );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}

	/**
	 * Récupère l'ensemble des valeurs saisies.
	 *
	 * @return Valeurs saisies
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public Float[] getValues() throws IllegalArgumentException {
		return new Float[]{ getValue() };
	}

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre, en faisant une conversion vers le type attendu.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setRawValue( Object value ) throws IllegalArgumentException {
		try {
			setValue( Float.valueOf( String.valueOf( value ) ) );
		} catch( NumberFormatException e ) {
			throw new IllegalArgumentException( "Valeur numérique invalide.", e );
		}
	}
}
