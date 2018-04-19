package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.InfirmierSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

public class InfirmierSearchDialogController extends ModelSearchDialogController {
	private InfirmierSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new InfirmierSearchDialog(), listener );
	}
}
