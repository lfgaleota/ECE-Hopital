package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.components.inputlists.HospitalisationInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class HospitalisationUpdateDialog extends ModelUpdateDialog<Hospitalisation> {
	public HospitalisationUpdateDialog( Hospitalisation model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new HospitalisationInputList( false, this );
	}

	@Override
	protected Hospitalisation buildNewModel() {
		return new Hospitalisation();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}
}
