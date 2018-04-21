package ece.ing3.java.projet.vue.components.inputlists;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputs.*;

import java.awt.*;

public class HospitalisationInputList extends ModelInputList {
	private final ChambreInput chambreInput;

	public HospitalisationInputList( boolean isSearch, Window parent ) {
		super( isSearch, parent );
		chambreInput = (ChambreInput) getInputs().get( "numeroChambre" );
		getInputs().get( "codeService" ).addValueChangeListener( newList -> chambreInput.setCodesService( (String[]) newList ) );
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected BaseInput getInputForField( String fieldName, String columnName, boolean isSearch, Window parent ) {
		switch( fieldName ) {
			case "numeroMalade":
				return new LongIdInput( columnName, isSearch );
			case "codeService":
				return new ServiceInput( columnName, isSearch, parent );
			case "numeroChambre":
				return new ChambreInput( columnName, isSearch, parent );
			case "numeroLit":
				return new IntegerInput( columnName, isSearch );
		}

		return null;
	}
}
