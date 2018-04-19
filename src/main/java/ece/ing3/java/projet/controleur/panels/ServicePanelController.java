package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ServiceSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.tables.ServiceTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.vue.panels.ServicePanel;

public class ServicePanelController extends ModelPanelController<Service> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Service.class;
	}

	@Override
	protected TableModel<Service> buildTableModel() {
		return new ServiceTableModel();
	}

	@Override
	protected ModelPanel<Service> buildModelPanel( TableModel<Service> tableModel ) {
		return new ServicePanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return ServiceSearchDialogController.createDialog(this);
	}
}
