package ece.ing3.java.projet.vue.components.inputs;

import ece.ing3.java.projet.controleur.dialogs.ModelSelectDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.interfaces.ValueChangeListener;
import ece.ing3.java.projet.vue.dialogs.ModelSelectDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Champ de saisie pour sélectionner des instances de modèle BDD, stockés en base de donnée, avec possibilité de sélection multiple pour la recherche
 * <p>
 * Ce champ doit être dérivé par modèle BDD considéré.
 *
 * @param <M> Type du modèle BDD considéré
 * @param <T> Type de retour utilisé (le type de la clé primaire du modèle)
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class SearchInput<M extends Model, T> extends JPanel implements BaseInput<T>, DialogListener {
	private String columnName;
	/**
	 * Champ de saisie textuel de la clé primaire du modèle BDD
	 */
	protected JTextField textField;
	/**
	 * Champ déclanchant la boîte de dialogue de sélection d'instances de modèle BDD
	 */
	protected JButton search;
	/**
	 * Boîte de dialogue de sélection d'instances de modèle BDD ouverte
	 */
	protected ModelSelectDialog<M> dialogSelect;
	/**
	 * Instances de modèle BDD sélectionnés dans la boîte de dialogue
	 */
	protected List<M> selectedModels;
	private ValueChangeListener valueChangeListener;

	/**
	 * Créer un nouveau champ de saisie pour sélectionner des instances de modèle BDD générique.
	 *
	 * @param columnName Nom de la colonne associée
	 * @param isSearch   {@code true} si le champ est utilisé pour de la recherche
	 * @param parent     Fenêtre parente de l'ensemble du champ, utilisé pour la boîte de dialogue de recherche
	 */
	public SearchInput( String columnName, boolean isSearch, Window parent ) {
		this.dialogSelect = null;
		this.columnName = columnName;
		this.textField = new JTextField();
		this.textField.setEnabled( false );
		this.valueChangeListener = null;
		setLayout( new BorderLayout() );
		add( this.textField, BorderLayout.CENTER );
		search = new JButton( "Rechercher" );
		add( search, BorderLayout.LINE_END );
		search.addActionListener( actionEvent -> {
			if( this.dialogSelect == null ) {
				ModelSelectDialogController<M> controller = new ModelSelectDialogController<>( parent, createController(), this );
				this.dialogSelect = controller.getDialog();
			} else {
				this.dialogSelect.toFront();
			}
		} );
	}

	/**
	 * Créer le panneau d'affichage, de recherche et sélection du modèle BDD considéré.
	 *
	 * @return Panneau d'affichage, de recherche et sélection du modèle BDD
	 */
	protected abstract ModelPanel<M> createController();

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
	public T getValue() {
		return ( this.selectedModels != null && this.selectedModels.size() > 0 ? getValueFromModel( this.selectedModels.get( 0 ) ) : null );
	}

	/**
	 * Récupère un tableau vide de type du modèle BDD considéré.
	 *
	 * @return Tableau vide de type du modèle BDD considéré
	 * @implNote Cette mléthode est utilisée pour faire de la conversion de {@link List} en tableau statique. Seul le type importe pour cette opération, et non la taille.
	 */
	protected abstract T[] getEmptyArray();

	/**
	 * Récupère la valeur du champ associée à une instance de modèle BDD.
	 *
	 * @param model Instance de modèle BDD
	 * @return Valeur du champ associée
	 */
	protected abstract T getValueFromModel( M model );

	/**
	 * Créer une instance de modèle BDD à partir de la valeur du champ fournie.
	 *
	 * @param value Valeur fournie
	 * @return Modèle BDD correspondant
	 * @throws IllegalArgumentException La valeur fgournie est invalide
	 */
	protected abstract M createInstanceFromValue( T value ) throws IllegalArgumentException;

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	public void setValue( T value ) throws IllegalArgumentException {
		M model = createInstanceFromValue( value );
		this.selectedModels = new LinkedList<>();
		this.selectedModels.add( model );
		this.textField.setText( String.valueOf( getValueFromModel( model ) ) );
		triggerValueListener();
	}

	/**
	 * Déclenche l'événement de mise à jour du champ. A appeler dès que le champ est modifié.
	 */
	protected void triggerValueListener() {
		if( this.valueChangeListener != null ) {
			this.valueChangeListener.onValueChanged( getValues() );
		}
	}

	/**
	 * Construit la clause Where de sélection liée à la/aux valeur(s) du champ
	 *
	 * @return Clause Where de sélection
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public Where getWhere() throws IllegalArgumentException {
		if( this.selectedModels.size() == 0 ) {
			throw new IllegalArgumentException( "No selected model." );
		}
		Where whereClause = new Where();
		for( M model : this.selectedModels ) {
			whereClause.or( this.columnName, "=", getValueFromModel( model ) );
		}
		return whereClause;
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
	 * Méthode régissant à la validation d'une boîte de dialogue, ici de la boîte de dialogue de sélection de modèle BDD.
	 *
	 * @param dialog Boîte de dialogue de sélection validée
	 */
	@Override
	public void onDialogSubmitted( JDialog dialog ) {
		this.selectedModels = this.dialogSelect.getPanel().getList().getSelecteds();
		this.dialogSelect = null;

		StringBuilder text = new StringBuilder();
		for( M selectedModel : this.selectedModels ) {
			text.append( getValueFromModel( selectedModel ) ).append( "," );
		}
		this.textField.setText( text.toString() );
		triggerValueListener();
	}

	/**
	 * Méthode régissant à l'annulation d'une boîte de dialogue, ici de la boîte de dialogue de sélection de modèle BDD.
	 *
	 * @param dialog Boîte de dialogue de sélection annulée
	 */
	@Override
	public void onDialogCancelled( JDialog dialog ) {
		this.dialogSelect = null;
	}

	/**
	 * Récupère l'ensemble des valeurs saisies.
	 *
	 * @return Valeurs saisies
	 * @throws IllegalArgumentException Au moins une valeure saisie est invalide
	 */
	@Override
	public T[] getValues() throws IllegalArgumentException {
		if( selectedModels != null && selectedModels.size() > 0 ) {
			LinkedList<T> lst = new LinkedList<>();

			for( M selectedModel : this.selectedModels ) {
				lst.add( getValueFromModel( selectedModel ) );
			}

			return lst.toArray( getEmptyArray() );
		}
		return null;
	}

	/**
	 * Réinitialise la valeur du champ.
	 */
	public void resetValue() {
		this.textField.setText( "" );
		this.selectedModels = null;
	}

	/**
	 * Remplace la valeur saisie par la valeur passée en paramètre, en faisant une conversion vers le type attendu.
	 *
	 * @param value Nouvelle valeur du champ
	 * @throws IllegalArgumentException La valeur fournie est invalide
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public void setRawValue( Object value ) throws IllegalArgumentException {
		try {
			setValue( (T) value );
		} catch( ClassCastException e ) {
			e.printStackTrace();
			throw new IllegalArgumentException( "Valeur invalide.", e );
		}
	}
}
