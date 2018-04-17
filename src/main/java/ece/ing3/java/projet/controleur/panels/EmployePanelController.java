package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.EmployeSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.EmployeTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.EmployePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class EmployePanelController  extends ModelPanelController<Employe> implements DialogListener {
	private static MaladePanelController instance;

	public static MaladePanelController get() {
		if( instance == null ) {
			instance = new MaladePanelController();
		}

		return instance;
	}

	@Override
	protected Class<? extends Model> getModelClass() {
		return Employe.class;
	}

	@Override
	protected TableModel<Employe> buildTableModel() {
		return new EmployeTableModel();
	}

	@Override
	protected ModelPanel<Employe> buildModelPanel( TableModel<Employe> tableModel ) {
		return new EmployePanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return EmployeSearchDialogController.createDialog( this );
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

