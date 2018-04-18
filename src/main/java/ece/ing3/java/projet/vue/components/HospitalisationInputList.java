package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.IntegerInput;
import ece.ing3.java.projet.vue.components.inputs.LongInput;
import ece.ing3.java.projet.vue.components.inputs.StringInput;

public class HospitalisationInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numeroMalade":
				return new LongInput( columnName, true );
			case "codeService":
				return new StringInput( columnName, true );
			case "numeroChambre":
				return new LongInput( columnName, true );
			case "numeroLit":
				return new IntegerInput( columnName, true ); 

		}

		return null;
	}
}
