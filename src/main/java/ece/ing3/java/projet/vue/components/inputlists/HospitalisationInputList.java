package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputs.*;

public class HospitalisationInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numeroMalade":
				return new LongIdInput( columnName, true );
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
