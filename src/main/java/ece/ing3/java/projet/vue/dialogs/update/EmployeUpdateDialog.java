package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.vue.components.inputlists.EmployeInputList;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;

public class EmployeUpdateDialog  extends ModelUpdateDialog<Employe> {
	public EmployeUpdateDialog( Employe model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new EmployeInputList( false );
	}

	@Override
	protected Employe buildNewModel() {
		return new Employe();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Employe.class;
	}
}
