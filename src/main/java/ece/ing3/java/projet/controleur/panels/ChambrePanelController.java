package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.ChambreSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.ChambreUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.ChambrePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class ChambrePanelController extends ModelPanelController<Chambre> {
	private static ChambrePanelController instance;

	@Override
	protected Class<? extends Model> getModelClass() {
		return Chambre.class;
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
	public ModelUpdateDialog<Chambre> createUpdateDialog( Chambre existingModel ) {
		return ChambreUpdateDialogController.createDialog( existingModel, this );
	}
}
