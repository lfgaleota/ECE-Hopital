package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.HospitalisationSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

public class HospitalisationSearchDialogController extends ModelSearchDialogController {
	private HospitalisationSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new HospitalisationSearchDialog(), listener );
	}
}
