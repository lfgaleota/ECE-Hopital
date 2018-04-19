package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.ChambreInputList;
import ece.ing3.java.projet.vue.components.ModelInputList;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

import java.util.Map;

public class ChambreUpdateDialog extends ModelUpdateDialog<Chambre> {
	public ChambreUpdateDialog( Chambre model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new ChambreInputList();
	}

	@Override
	public void fillFromModel( Chambre model ) {

	}

	@Override
	protected Chambre buildNewModel() {
		return new Chambre();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	protected void fillModel( Chambre model, String fieldName, BaseInput input ) {
		switch( fieldName ) {
			case "numeroChambre":
				model.setNumeroChambre( (Long) input.getValue() );
				break;
			case "nombreLits":
				model.setNombreLits( (Integer) input.getValue() );
				break;
			case "numeroSurveillant":
				model.setNumeroSurveillant( (Long) input.getValue() );
				break;
			case "codeServiceRattache":
				model.setCodeServiceRattache( (String) input.getValue() );
				break;
		}
	}
}
