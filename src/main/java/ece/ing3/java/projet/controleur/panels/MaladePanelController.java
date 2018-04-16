package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.MaladeSearchDialogController;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.MaladeTableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.MaladePanel;

public class MaladePanelController extends ModelPanelController<Malade> implements DialogListener {
	private static MaladePanelController instance;

	private MaladePanelController() {
		super( Malade.class );
		MaladeTableModel tableModel = new MaladeTableModel();
		init( new MaladePanel( tableModel ), tableModel );
	}

	public static MaladePanelController get() {
		if( instance == null ) {
			instance = new MaladePanelController();
		}

		return instance;
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return MaladeSearchDialogController.createDialog( this );
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
