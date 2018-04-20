package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.SoigneInputList;

public class SoigneUpdateDialog extends ModelUpdateDialog<Soigne> {
	public SoigneUpdateDialog( Soigne model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new SoigneInputList( false );
	}

	@Override
	protected Soigne buildNewModel() {
		return new Soigne();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}
}
