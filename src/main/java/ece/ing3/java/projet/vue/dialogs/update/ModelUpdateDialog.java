package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

import javax.swing.*;
import java.util.Map;

public abstract class ModelUpdateDialog<M extends Model> extends BaseModelInputDialog {
	private M model;

	public ModelUpdateDialog( M model ) {
		this.model = model;
		setTitle( getTitle() );
		getSubmit().setText( getSubmitLabel() );
	}

	@Override
	public String getTitle() {
		return ( model != null ? "Modification" : "Ajout" );
	}

	@Override
	protected String getSubmitLabel() {
		return ( model != null ? "Mettre à jour" : "Ajouter" );
	}

	public abstract void fillFromModel( M model );
	protected abstract M buildNewModel();
	protected abstract Class<? extends Model> getModelClass();
	protected abstract void fillModel( M model, String fieldName, BaseInput input );

	public M buildModel() throws IllegalArgumentException {
		M model = buildNewModel();
		int fieldsTotal = Model.getFieldNames( getModelClass() ).length, fieldsValid = 0;

		for( Map.Entry<String, BaseInput> input : inputList.getInputs().entrySet() ) {
			fillModel( model, input.getKey(), input.getValue() );
			fieldsValid++;
		}

		if( fieldsTotal != fieldsValid ) {
			throw new IllegalArgumentException( fieldsTotal + " fields filled expected for model " + getModelClass() + ", got " + fieldsValid );
		}

		return model;
	}

	@Override
	public void validateInput() throws IllegalArgumentException {
		buildModel();
	}
}
