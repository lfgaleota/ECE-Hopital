package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongIdInput;
import ece.ing3.java.projet.vue.components.inputs.StringInput;

public class MaladeInputList extends ModelInputList {
	public MaladeInputList( boolean isSearch ) {
		super( isSearch );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch ) {
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
			case "mutuelle":
				return new StringInput( columnName, isSearch );
		}

		return null;
	}
}
