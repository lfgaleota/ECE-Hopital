package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * Champ de saisie de valeur textuelle, avec possibilité de sélection en exact ou approximatif pour la recherche
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class StringInput extends JPanel implements BaseInput<String>, DocumentListener {
	private String columnName;
	/**
	 * Champ de saisie textuel
	 */
	protected JTextField textField;
	/**
	 * Sélecteur de comparateur à utiliser pour {@link StringInput#getWhere()}
	 */
	protected JComboBox<String> selector;
	private ValueChangeListener valueChangeListener;

	/**
	 * Créer un nouveau champ de saisie textuel.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 */
	public StringInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		this.textField = new JTextField();
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		if( isSearch ) {
			this.selector = new JComboBox<>( new String[]{ "=", "~=" } );
			add( this.selector, BorderLayout.EAST );
		}
		this.valueChangeListener = null;
		this.textField.getDocument().addDocumentListener( this );
	}

	/**
	 * Modifie la taille souhaitée du champ, de manière à ne modifier que la largeur et à conserver la hauteur à sa valeur d'avant, ce pour garantir que le champ ne soit pas trop petit en hauteur.
	 *
	 * @param size Dimensions souhaitées (seul la largeur sera considéré)
	 */
	@Override
	public void setPreferredSize( Dimension size ) {
		super.setPreferredSize( new Dimension( size.width, getPreferredSize().height ) );
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
	 * Retourne si le champ est actuellement rempli.
	 *
	 * @return {@code true} champ rempli
	 */
	@Override
	public boolean isFilled() {
		return textField.getText().length() > 0;
	}
	/**
	 * Récupère la valeur actuelle du champ de saisie, ou la première des valeurs multiples s'il y en a.
	 *
	 * @return Valeur saisie actuelle
	 * @throws IllegalArgumentException La valeur saisie est invalide
	 */
	@Override
	public String getValue() {
		return textField.getText();
	}

	/**
	 * Récupère l'ensemble des valeurs saisies.
	 *
	 * @return Valeurs saisies
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public String[] getValues() throws IllegalArgumentException {
		return new String[]{ getValue() };
	}
	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( String value ) throws IllegalArgumentException {
		textField.setText( value );
		triggerValueListener();
	}

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre, en faisant une conversion vers le type attendu.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setRawValue( Object value ) throws IllegalArgumentException {
		setValue( String.valueOf( value ) );
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
		if( selector.equals( "~=" ) ) {
			return new Where( getColumnName(), "LIKE", "%" + getValue() + "%" );
		}
		return new Where( getColumnName(), "=", getValue() );
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
