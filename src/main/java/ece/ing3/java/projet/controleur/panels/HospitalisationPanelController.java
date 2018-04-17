package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.HospitalisationSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.tables.HospitalisationTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.HospitalisationPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class HospitalisationPanelController extends ModelPanelController<Hospitalisation> implements DialogListener {
	private static HospitalisationPanelController instance;

	public static HospitalisationPanelController get() {
		if( instance == null ) {
			instance = new HospitalisationPanelController();
		}

		return instance;
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Hospitalisation.class;
	}

	@Override
	protected TableModel<Hospitalisation> buildTableModel() {
		return new HospitalisationTableModel();
	}

	@Override
	protected ModelPanel<Hospitalisation> buildModelPanel( TableModel<Hospitalisation> tableModel ) {
		return new HospitalisationPanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return HospitalisationSearchDialogController.createDialog( this );
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
