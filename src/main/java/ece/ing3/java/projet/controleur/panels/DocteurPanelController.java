package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.DocteurSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.tables.DocteurTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.DocteurPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class DocteurPanelController  extends ModelPanelController<Docteur> implements DialogListener {
	private static DocteurPanelController instance;

	public static DocteurPanelController get() {
		if( instance == null ) {
			instance = new DocteurPanelController();
		}

		return instance;
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}

	@Override
	protected TableModel<Docteur> buildTableModel() {
		return new DocteurTableModel();
	}

	@Override
	protected ModelPanel<Docteur> buildModelPanel( TableModel<Docteur> tableModel ) {
		return new DocteurPanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return DocteurSearchDialogController.createDialog( this );
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

