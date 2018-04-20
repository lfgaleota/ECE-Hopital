package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

public class DocteurInputList extends ModelInputList {
	public DocteurInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
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
			case "specialite":
				return new SpecialiteInput( columnName, isSearch );

		}

		return null;
	}

}
