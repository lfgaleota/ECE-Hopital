package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.components.inputlists.DocteurInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class DocteurUpdateDialog extends ModelUpdateDialog<Docteur> {
	public DocteurUpdateDialog( Docteur model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new DocteurInputList( false, this );
	}

	@Override
	protected Docteur buildNewModel() {
		return new Docteur();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}
}
