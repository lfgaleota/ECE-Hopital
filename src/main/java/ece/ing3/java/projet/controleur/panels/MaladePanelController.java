package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.MaladeSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.tables.MaladeTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.MaladePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class MaladePanelController extends ModelPanelController<Malade> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Malade.class;
	}

	@Override
	protected TableModel<Malade> buildTableModel() {
		return new MaladeTableModel();
	}

	@Override
	protected ModelPanel<Malade> buildModelPanel( TableModel<Malade> tableModel ) {
		return new MaladePanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return MaladeSearchDialogController.createDialog( this );
	}
}
