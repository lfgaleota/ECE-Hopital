package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

public class ServiceInputList extends ModelInputList {
	public ServiceInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "code":
				return new StringIdInput( columnName, isSearch );
			case "nom":
				return new StringInput( columnName, isSearch );
			case "batiment":
				return new StringInput( columnName, isSearch );
			case "numeroDirecteur":
				return new DocteurInput( columnName, isSearch, parent );
		}

		return null;
	}
}
