package ece.ing3.java.projet.interfaces;

/**
 * Objet écoutant les changements de valeur d'un {@link ece.ing3.java.projet.vue.components.inputs.BaseInput}
 */
public interface ValueChangeListener {
	/**
	 * Méthode appelée lorsque la valeur est changée.
	 *
	 * @param newValue Nouvelle valeur
	 */
	void onValueChanged( Object newValue );
}
