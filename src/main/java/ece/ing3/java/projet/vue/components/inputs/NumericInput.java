package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Champ de saisie pour les valeurs de type numérique, avec possibilité de sélection selon plusieurs types de comparateurs pour la recherche
 * <p>
 * Ce champ doit être dérivé par type numérique désiré.
 *
 * @param <T> Type numérique utilisé
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class NumericInput<T> extends JPanel implements BaseInput<T>, DocumentListener {
	/**
	 * Champ de saisie textuel
	 */
	protected JTextField textField;
	/**
	 * Sélecteur de comparateur à utiliser pour {@link NumericInput#getWhere()}
	 */
	protected JComboBox<String> selector;
	private ValueChangeListener valueChangeListener;

	/**
	 * Créer un nouveau champ de saisie de valeur numérique générique.
	 *
	 * @implNote Il n'y a ici pas de columnName, suite à un oubli de restructuration avant le freeze du code source.
	 *
	 * @param isSearch {@code true} si le champ est utilisé pour de la recherche
	 */
	public NumericInput( boolean isSearch ) {
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "<", ">", "<=", ">=" } );
			add( this.selector, BorderLayout.EAST );
		}
		this.valueChangeListener = null;
		this.textField.getDocument().addDocumentListener( this );
	}

	/**
	 * Retourne si le champ est actuellement rempli.
	 *
	 * @return {@code true} champ rempli
	 */
	@Override
	public boolean isFilled() {
		return textField.getText().length() > 0;
	}

	/**
	 * Récupère la valeur textuelle saisie.
	 *
	 * @return Valeur textuelle saisie
	 */
	public String getTextValue() {
		return textField.getText();
	}
	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		textField.setText( String.valueOf( value ) );
		triggerValueListener();
	}

	/**
	 * Construit la clause Where de sélection liée à la/aux valeur(s) du champ
	 *
	 * @return Clause Where de sélection
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public Where getWhere() throws IllegalArgumentException {
		String selector = ( this.selector.getSelectedItem() != null ? (String) this.selector.getSelectedItem() : "=" );
		return new Where( getColumnName(), selector, getValue() );
	}

	@Override
	public void setPreferredSize( Dimension size ) {
		super.setPreferredSize( new Dimension( size.width, getPreferredSize().height ) );
	}

	/**
	 * Modifie l'objet qui écoute les changements de valeurs du champ
	 *
	 * @param valueChangeListener Objet qui écoute les changements de valeurs
	 */
	@Override
	public void addValueChangeListener( ValueChangeListener valueChangeListener ) {
		this.valueChangeListener = valueChangeListener;
	}

	/**
	 * Méthode de retour appelée lors d'une insertion de valeur dans un {@link javax.swing.text.Document}, ici lorsque l'utilisateur insert une valeur dans le champ texte de saisie {@link NumericInput#textField}.
	 *
	 * @param documentEvent Evénement de modification de document
	 */
	@Override
	public void insertUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	/**
	 * Méthode de retour appelée lors d'une suppression de valeur dans un {@link javax.swing.text.Document}, ici lorsque l'utilisateur supprime la valeur dans le champ texte de saisie {@link NumericInput#textField}.
	 *
	 * @param documentEvent Evénement de modification de document
	 */
	@Override
	public void removeUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	/**
	 * Méthode de retour appelée lors d'un changement de valeur dans un {@link javax.swing.text.Document}, ici lorsque l'utilisateur change la valeur dans le champ texte de saisie {@link NumericInput#textField}.
	 *
	 * @param documentEvent Evénement de modification de document
	 */
	@Override
	public void changedUpdate( DocumentEvent documentEvent ) {
		triggerValueListener();
	}

	private void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}
}
