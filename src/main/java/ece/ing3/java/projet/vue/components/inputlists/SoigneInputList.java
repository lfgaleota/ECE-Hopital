package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.components.inputs.LongIdInput;

import java.awt.*;

public class SoigneInputList extends ModelInputList {
	public SoigneInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "numeroDocteur":
				return new LongIdInput( columnName, isSearch );
			case "numeroMalade":
				return new LongIdInput( columnName, isSearch );
		}

		return null;
	}

}
