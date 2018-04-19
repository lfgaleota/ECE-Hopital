package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.components.inputs.*;

public class InfirmierInputList extends ModelInputList {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
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
			case "rotation":
				return new RotationInput( columnName, true );
			case "salaire":
				return new FloatInput( columnName, true );
			case "codeService":
				return new StringInput( columnName, true );

		}

		return null;
	}

}
