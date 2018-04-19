package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ServiceSearchDialog;

public class ServiceSearchDialogController extends ModelSearchDialogController {
	private ServiceSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new ServiceSearchDialog(), listener );
	}

}
