package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.components.inputlists.InfirmierInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class InfirmierUpdateDialog extends ModelUpdateDialog<Infirmier> {
	public InfirmierUpdateDialog( Infirmier model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new InfirmierInputList( false );
	}

	@Override
	protected Infirmier buildNewModel() {
		return new Infirmier();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}
}
