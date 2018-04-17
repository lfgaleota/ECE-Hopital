package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.InfirmierSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.InfirmierTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.panels.InfirmierPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class InfirmierPanelController extends ModelPanelController<Infirmier> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Infirmier.class;
	}

	@Override
	protected TableModel<Infirmier> buildTableModel() {
		return new InfirmierTableModel();
	}

	@Override
	protected ModelPanel<Infirmier> buildModelPanel( TableModel<Infirmier> tableModel ) {
		return new InfirmierPanel( tableModel );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	protected SQLSelect<Infirmier> createSelector() {
		return new SQLSelect<Infirmier>( new Class[]{ Infirmier.class, Employe.class } );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return InfirmierSearchDialogController.createDialog( this );
	}
}
