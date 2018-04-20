package ece.ing3.java.projet.vue.dialogs.update;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.components.inputlists.ModelInputList;
import ece.ing3.java.projet.vue.components.inputlists.ServiceInputList;

public class ServiceUpdateDialog extends ModelUpdateDialog<Service> {
	public ServiceUpdateDialog( Service model ) {
		super( model );
	}

	@Override
	public ModelInputList build() {
		return new ServiceInputList( false, this );
	}

	@Override
	protected Service buildNewModel() {
		return new Service();
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}
}
