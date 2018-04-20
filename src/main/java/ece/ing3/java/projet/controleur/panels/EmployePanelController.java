package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.EmployeSearchDialogController;
import ece.ing3.java.projet.controleur.dialogs.update.EmployeUpdateDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.tables.EmployeTableModel;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.EmployePanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class EmployePanelController  extends ModelPanelController<Employe> {
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
	public ModelUpdateDialog<Employe> createUpdateDialog( Employe existingModel ) {
		return EmployeUpdateDialogController.createDialog( existingModel, this );
	}

	public Where queryModifyWhereClause( Where whereClause ) {
		if( whereClause == null ) {
			whereClause = new Where();
		}
		whereClause.and( "numero", "NOT IN", new SQLSelect<Infirmier>( Infirmier.class, "numero" ) );
		whereClause.and( "numero", "NOT IN", new SQLSelect<Docteur>( Docteur.class, "numero" ) );
		return whereClause;
	}
}

