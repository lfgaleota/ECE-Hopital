package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongIdInput;
import ece.ing3.java.projet.vue.components.inputs.StringInput;

public class MaladeInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numero":
				return new LongIdInput( columnName, true );
			case "nom":
				return new StringInput( columnName, true );
			case "prenom":
				return new StringInput( columnName, true );
			case "adresse":
				return new StringInput( columnName, true );
			case "numeroTelephone":
				return new StringInput( columnName, true );
			case "mutuelle":
				return new StringInput( columnName, true );
		}

		return null;
	}
}
