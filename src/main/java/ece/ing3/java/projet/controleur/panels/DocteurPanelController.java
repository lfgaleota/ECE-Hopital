package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.controleur.dialogs.search.DocteurSearchDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.database.sql.queries.SQLSelect;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.tables.TableModel;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;
import ece.ing3.java.projet.vue.panels.DocteurPanel;
import ece.ing3.java.projet.vue.panels.ModelPanel;

public class DocteurPanelController  extends ModelPanelController<Docteur> {
	@Override
	protected Class<? extends Model> getModelClass() {
		return Docteur.class;
	}

	@Override
	protected ModelPanel<Docteur> buildModelPanel( TableModel<Docteur> tableModel ) {
		return new DocteurPanel( tableModel );
	}

	@Override
	@SuppressWarnings( "unchecked" )
	public SQLSelect<Docteur> queryCreateSelector() {
		return new SQLSelect<Docteur>( new Class[]{ Docteur.class, Employe.class } );
	}

	@Override
	public ModelSearchDialog createSearchDialog() {
		return DocteurSearchDialogController.createDialog( this );
	}

	@Override
	public ModelUpdateDialog<Docteur> createUpdateDialog( Docteur existingModel ) {
		return null;
	}
}

