package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.components.inputlists.ChambreInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class ChambreUpdateDialog extends ModelUpdateDialog<Chambre> {
	public ChambreUpdateDialog( Chambre model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new ChambreInputList();
	}

	@Override
	protected Chambre buildNewModel() {
		return new Chambre();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}
}
