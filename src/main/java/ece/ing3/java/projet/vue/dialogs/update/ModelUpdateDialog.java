package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public abstract class ModelUpdateDialog<M extends Model> extends BaseModelInputDialog {
	private M model;

	public ModelUpdateDialog( M model ) {
		this.model = model;
		setTitle( getTitle() );
		getSubmit().setText( getSubmitLabel() );
		try {
			if( model != null ) {
				fillFromModel( model );
			}
		} catch( IllegalArgumentException e ) {
			dispose();
			e.printStackTrace();
			Utils.error( "Impossible de modifier cette entrée." );
		}
	}

	public boolean isAdd() {
		return model == null;
	}

	@Override
	public String getTitle() {
		return ( isAdd() ? "Ajout" : "Modification" );
	}

	@Override
	protected String getSubmitLabel() {
		return ( isAdd() ? "Ajouter" : "Mettre à jour" );
	}

	protected abstract M buildNewModel();
	protected abstract Class<? extends Model> getModelClass();

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

	public void fillFromModel( M model ) throws IllegalArgumentException {
		for( Map.Entry<String, BaseInput> entry : inputList.getInputs().entrySet() ) {
			try {
				entry.getValue().setValue( PropertyUtils.getSimpleProperty( model, entry.getKey() ) );
			} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
				throw new IllegalArgumentException( e );
			}
		}
	}

	protected void fillModel( M model, String fieldName, BaseInput input ) throws IllegalArgumentException {
		try {
			PropertyUtils.setSimpleProperty( model, fieldName, input.getValue() );
		} catch( IllegalAccessException | InvocationTargetException | NoSuchMethodException e ) {
			throw new IllegalArgumentException( e );
		}
	}
}
