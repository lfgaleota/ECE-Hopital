package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.components.inputlists.MaladeInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class MaladeUpdateDialog extends ModelUpdateDialog<Malade> {
	public MaladeUpdateDialog( Malade model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new MaladeInputList( false, this );
	}

	@Override
	protected Malade buildNewModel() {
		return new Malade();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}
}
