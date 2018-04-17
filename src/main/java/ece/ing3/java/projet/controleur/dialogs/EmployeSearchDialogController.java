package ece.ing3.java.projet.controleur.dialogs;

import static ece.ing3.java.projet.controleur.dialogs.ModelSearchDialogController.createDialog;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.MaladeSearchDialog;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

public class EmployeSearchDialogController extends ModelSearchDialogController {
	private EmployeSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new MaladeSearchDialog(), listener );
	}
}
