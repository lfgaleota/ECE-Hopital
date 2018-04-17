package ece.ing3.java.projet.vue.components;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongInput;
import ece.ing3.java.projet.vue.components.inputs.StringInput;

public class InfirmierInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName ) {
		switch( fieldName ) {
			case "numero":
				return new LongInput( columnName, true );
			case "nom":
				return new StringInput( columnName, true );
			case "prenom":
				return new StringInput( columnName, true );
			case "adresse":
				return new StringInput( columnName, true );
			case "numeroTelephone":
				return new StringInput( columnName, true );
			case "rotation":
				return new StringInput( columnName, true ); //de type rotation normalement
			case "salaire":
				return new StringInput( columnName, true ); // de type Float normalement
			case "codeService":
				return new StringInput( columnName, true );

		}

		return null;
	}

}