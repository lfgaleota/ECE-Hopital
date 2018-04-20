package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

public class ChambreInputList extends ModelInputList {
	public ChambreInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "numeroChambre":
				return new LongIdInput( columnName, isSearch );
			case "nombreLits":
				return new IntegerInput( columnName, isSearch );
			case "numeroSurveillant":
				return new LongInput( columnName, isSearch );
			case "codeServiceRattache":
				return new ServiceIdInput( columnName, isSearch, parent );

		}

		return null;
	}

}
