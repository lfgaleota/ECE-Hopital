package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.ChambreSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.ChambreTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.ChambrePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class ChambrePanelController extends ModelPanelController<Chambre> implements DialogListener {
	private static ChambrePanelController instance;

	public static ChambrePanelController get() {
		if( instance == null ) {
			instance = new ChambrePanelController();
		}

		return instance;
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
	}

	@Override
	protected TableModel<Chambre> buildTableModel() {
		return new ChambreTableModel();
	}

	@Override
	protected ModelPanel<Chambre> buildModelPanel( TableModel<Chambre> tableModel ) {
		return new ChambrePanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return ChambreSearchDialogController.createDialog( this );
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
