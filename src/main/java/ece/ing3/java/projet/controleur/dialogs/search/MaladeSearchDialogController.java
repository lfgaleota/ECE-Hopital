package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.MaladeSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

public class MaladeSearchDialogController extends ModelSearchDialogController {
	private MaladeSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new MaladeSearchDialog(), listener );
	}
}
