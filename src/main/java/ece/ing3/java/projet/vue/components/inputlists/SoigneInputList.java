package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongIdInput;

public class SoigneInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numeroDocteur":
				return new LongIdInput( columnName, true );
			case "numeroMalade":
				return new LongIdInput( columnName, true );
		}

		return null;
	}

}
