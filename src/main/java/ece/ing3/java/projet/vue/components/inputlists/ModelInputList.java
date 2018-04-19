package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class ModelInputList extends JPanel {
	protected Map<String, BaseInput> inputMap = new HashMap<>();

	public ModelInputList() {
		String[] fieldNames = Model.getFieldNames( getModelClass() );
		setLayout( new GridLayout( fieldNames.length, 2 ) );
		for( String fieldName : fieldNames ) {
			BaseInput input = getInputForField( fieldName, Model.getColumnNameFromFieldName( getModelClass(), fieldName ) );
			inputMap.put( fieldName, input );
			createInput( fieldName, input );
		}
	}

	protected abstract Class<? extends Model> getModelClass();

	private void createInput( String fieldName, BaseInput input ) {
		add( new JLabel( Strings.getModel( fieldName ) ) );
		add( (Component) input );
	}

	protected abstract BaseInput getInputForField( String fieldName, String columnName );

	public Map<String, BaseInput> getInputs() {
		return inputMap;
	}
}
