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

public abstract class ModelInputList extends JPanel {
	protected Map<String, BaseInput> inputMap = new HashMap<>();

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

	protected abstract Class<? extends Model> getModelClass();

	private void createInput( String fieldName, BaseInput input ) {
		add( new JLabel( Strings.getModel( fieldName ) ) );
		( (Component) input ).setPreferredSize( Constants.UI_DIALOGCOMPONENT_PREFERREDSIZE );
		add( (Component) input );
	}

	protected abstract BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent );

	public Map<String, BaseInput> getInputs() {
		return inputMap;
	}
}
