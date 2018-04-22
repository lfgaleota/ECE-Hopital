package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.components.FlexibleGridLayout;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Ensemble de champs de saisie pour la saisie des attributs d'un modèle BDD
 * <p>
 * Cette classe est une base pour des classes spécialisées dédiées à chaque modèle.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class ModelInputList extends JPanel {
	/**
	 * Ensemble de champs, associé à leur nom d'attribut
	 */
	protected Map<String, BaseInput> inputMap = new HashMap<>();

	/**
	 * Créer un nouveau ensemble de champs de saisie pour la saisie des attributs d'un modèle BDD.
	 *
	 * @param isSearch {@code true} si les champs seront utilisés pour de la recherche
	 * @param parent   Fenêtre parente de l'ensemble de champs
	 */
	public ModelInputList( boolean isSearch, Window parent ) {
		String[] fieldNames = Model.getFieldNames( getModelClass() );
		setLayout( new FlexibleGridLayout( fieldNames.length, 2, Constants.UI_INPUTLIST_HGAP, Constants.UI_INPUTLIST_VGAP ) );
		for( String fieldName : fieldNames ) {
			BaseInput input = getInputForField(
					fieldName,
					Model.getColumnNameFromFieldName( getModelClass(), fieldName ),
					isSearch,
					parent
			);
			inputMap.put( fieldName, input );
			createInput( fieldName, input );
		}
	}

	/**
	 * Récupère la classe du modèle BDD associé à l'ensemble.
	 *
	 * @return Classe du modèle BDD
	 */
	protected abstract Class<? extends Model> getModelClass();

	private void createInput( String fieldName, BaseInput input ) {
		add( new JLabel( Strings.getModel( fieldName ) ) );
		( (Component) input ).setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );
		add( (Component) input );
	}

	/**
	 * Construit, pour un attribut du modèle BDD, le champ de saisie implémentant {@link BaseInput} associé.
	 *
	 * @param fieldName  Nom de l'attribut
	 * @param columnName Nom de la colonne associée à l'attribut
	 * @param isSearch   {@code true} si le champ sera utilisé pour de la recherche
	 * @param parent     Fenêtre parente du champ
	 * @return Champ de saisie
	 */
	protected abstract BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent );

	/**
	 * Récupère l'ensemble des champs de saisies.
	 *
	 * @return Ensemble des champs de saisies
	 */
	public Map<String, BaseInput> getInputs() {
		return inputMap;
	}
}
