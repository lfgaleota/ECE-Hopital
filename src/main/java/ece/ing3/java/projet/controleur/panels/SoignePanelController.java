package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.SoigneSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.modele.tables.SoigneTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;
import ece.ing3.java.projet.vue.panels.SoignePanel;

public class SoignePanelController extends ModelPanelController<Soigne> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Soigne.class;
	}

	@Override
	protected TableModel<Soigne> buildTableModel() {
		return new SoigneTableModel();
	}

	@Override
	protected ModelPanel<Soigne> buildModelPanel( TableModel<Soigne> tableModel ) {
		return new SoignePanel( tableModel );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return SoigneSearchDialogController.createDialog( this );
	}
}

