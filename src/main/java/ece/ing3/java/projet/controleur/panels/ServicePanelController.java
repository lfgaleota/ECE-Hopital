package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.ServiceSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.tables.ServiceTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.vue.panels.ServicePanel;

public class ServicePanelController extends ModelPanelController<Service> implements DialogListener {
	private static ServicePanelController instance;

	public static ServicePanelController get() {
		if( instance == null ) {
			instance = new ServicePanelController();
		}

		return instance;
	}

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

	@Override
	public void onDialogSubmitted( ModelSearchDialog dialog ) {
		if( dialog == dialogSearch ) {
			whereClause = dialogSearch.getWhereClause();
			System.out.println( "Where clause : " + whereClause );
			dialogSearch = null;
			update();
		}
	}

	@Override
	public void onDialogCancelled( ModelSearchDialog dialog ) {
		if( dialog == dialogSearch ) {
			dialogSearch = null;
		}
	}

}
