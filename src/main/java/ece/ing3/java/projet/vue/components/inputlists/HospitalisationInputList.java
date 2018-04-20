package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputs.*;

public class HospitalisationInputList extends ModelInputList {
	public HospitalisationInputList( boolean isSearch ) {
		super( isSearch );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch ) {
		switch( fieldName ) {
			case "numeroMalade":
				return new LongIdInput( columnName, isSearch );
			case "codeService":
				return new StringInput( columnName, isSearch );
			case "numeroChambre":
				return new LongInput( columnName, isSearch );
			case "numeroLit":
				return new IntegerInput( columnName, isSearch );

		}

		return null;
	}
}
