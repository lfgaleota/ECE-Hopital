package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.inputs.*;

public class ChambreInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numeroChambre":
				return new LongIdInput( columnName, true );
			case "nombreLits":
				return new IntegerInput( columnName, true );
			case "numeroSurveillant":
				return new LongInput( columnName, true );
			case "codeServiceRattache":
				return new StringIdInput( columnName, true );

		}

		return null;
	}

}
