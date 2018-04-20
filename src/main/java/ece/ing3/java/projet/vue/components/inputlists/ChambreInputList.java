package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.inputs.*;

public class ChambreInputList extends ModelInputList {
	public ChambreInputList( boolean isSearch ) {
		super( isSearch );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch ) {
		switch( fieldName ) {
			case "numeroChambre":
				return new LongIdInput( columnName, isSearch );
			case "nombreLits":
				return new IntegerInput( columnName, isSearch );
			case "numeroSurveillant":
				return new LongInput( columnName, isSearch );
			case "codeServiceRattache":
				return new StringIdInput( columnName, isSearch );

		}

		return null;
	}

}
