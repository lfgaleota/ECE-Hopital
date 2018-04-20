package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

public class InfirmierInputList extends ModelInputList {
	public InfirmierInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "numero":
				return new LongIdInput( columnName, isSearch );
			case "nom":
				return new StringInput( columnName, isSearch );
			case "prenom":
				return new StringInput( columnName, isSearch );
			case "adresse":
				return new StringInput( columnName, isSearch );
			case "numeroTelephone":
				return new StringInput( columnName, isSearch );
			case "rotation":
				return new RotationInput( columnName, isSearch );
			case "salaire":
				return new FloatInput( columnName, isSearch );
			case "codeService":
				return new ServiceInput( columnName, isSearch, parent );

		}

		return null;
	}

}
