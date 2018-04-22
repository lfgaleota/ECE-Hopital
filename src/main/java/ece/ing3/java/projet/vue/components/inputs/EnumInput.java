package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.ValueChangeListener;
import ece.ing3.java.projet.utils.Constants;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Champ de saisie pour les valeurs de type Enum, avec possibilité de sélection multiple pour la recherche
 * <p>
 * Ce champ doit être dérivé par énumération désirée.
 *
 * @param <T> Type de l'énumération
 */
public abstract class EnumInput<T> extends JPanel implements BaseInput<T>, ActionListener, ListSelectionListener {
	/**
	 * Liste de sélection de valeur unique (pour l'ajout/modification)
	 */
	protected JComboBox<T> comboBox;
	/**
	 * Liste de sélection de valeurs multiples (pour la recherche)
	 */
	protected JList<T> list;
	/**
	 * Nom de la colonne associée
	 */
	protected String columnName;
	private ValueChangeListener valueChangeListener;

	/**
	 * Créer un nouveau champ de saisie de valeur énumération générique.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 */
	public EnumInput( String columnName, boolean isSearch ) {
		this.columnName = columnName;
		if( !isSearch ) {
			this.comboBox = new JComboBox<>( getEnumValues() );
			this.comboBox.addActionListener( this );
			add( this.comboBox );
		} else {
			DefaultListModel<T> listModel = new DefaultListModel<>();
			for( T val : getEnumValues() ) {
				listModel.addElement( val );
			}
			this.list = new JList<>( listModel );
			this.list.setVisibleRowCount( Math.min( Constants.UI_INPUTLIST_MAXVISIBLEENUMITEMS, listModel.size() ) );
			this.list.addListSelectionListener( this );
			JScrollPane scrollPane = new JScrollPane( this.list );
			add( scrollPane );
		}
		this.valueChangeListener = null;
	}

	/**
	 * Récupère les valeurs de l'énumération à utiliser et afficher
	 *
	 * @return Valeurs de l'énumération
	 */
	protected abstract T[] getEnumValues();

	/**
	 * Récupère la valeur de l'énumération associée à une chaîne de caractère
	 *
	 * @param value Chaîne de caractère
	 * @return Valeur de l'énumération associée
	 */
	protected abstract T getEnumValueOf( String value );

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
		return ( comboBox != null && comboBox.getSelectedItem() != null ) || ( list != null && list.getSelectedIndices().length > 0 );
	}
	/**
	 * Récupère la valeur actuelle du champ de saisie, ou la première des valeurs multiples s'il y en a.
	 *
	 * @return Valeur saisie actuelle
	 * @throws IllegalArgumentException La valeur saisie est invalide
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public T getValue() {
		if( comboBox != null ) {
			return (T) comboBox.getSelectedItem();
		}
		if( list != null ) {
			return list.getSelectedValue();
		}
		return null;
	}
	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		if( comboBox != null ) {
			comboBox.setSelectedItem( value );
		} else {
			list.setSelectedValue( value, true );
		}
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
		setValue( getEnumValueOf( String.valueOf( value ) ) );
	}

	/**
	 * Construit la clause Where de sélection liée à la/aux valeur(s) du champ
	 *
	 * @return Clause Where de sélection
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( list != null ) {
			Where whereClause = new Where();
			for( T val : list.getSelectedValuesList() ) {
				whereClause.or( getColumnName(), "=", val.toString() );
			}
			return whereClause;
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
	 * Méthode de retour appelée lors d'une action, ici lorsque l'utilisateur intéragit avec la liste de sélection unique {@link EnumInput#comboBox}.
	 *
	 * @param actionEvent Evénement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == this.comboBox ) {
			triggerValueListener();
		}
	}

	/**
	 * Méthode de retour appelée lors d'une sélection de valeur, ici lorsque l'utilisateur intéragit avec la liste de sélection multiple {@link EnumInput#list}.
	 *
	 * @param listSelectionEvent Evénement de sélection de valeur
	 */
	@Override
	public void valueChanged( ListSelectionEvent listSelectionEvent ) {
		if( listSelectionEvent.getSource() == this.list ) {
			triggerValueListener();
		}
	}

	/**
	 * Récupère l'ensemble des valeurs saisies.
	 *
	 * @return Valeurs saisies
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public T[] getValues() throws IllegalArgumentException {
		if( this.list != null ) {
			return this.list.getSelectedValuesList().toArray( getEnumValues() );
		}
		if( getValue() != null ) {
			LinkedList<T> lst = new LinkedList<T>();
			lst.add( getValue() );
			return lst.toArray( getEnumValues() );
		}
		return null;
	}

	private void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}
}
