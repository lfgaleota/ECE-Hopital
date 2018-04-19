package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongInput;
import ece.ing3.java.projet.vue.components.inputs.StringIdInput;
import ece.ing3.java.projet.vue.components.inputs.StringInput;

public class ServiceInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "code":
				return new StringIdInput( columnName, true );
			case "nom":
				return new StringInput( columnName, true );
			case "batiment":
				return new StringInput( columnName, true );
			case "numeroDirecteur":
				return new LongInput( columnName, true );

		}

		return null;
	}
}
