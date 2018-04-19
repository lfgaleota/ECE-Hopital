package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.SoigneSearchDialog;

public class SoigneSearchDialogController extends ModelSearchDialogController {
	private SoigneSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new SoigneSearchDialog(), listener );
	}
}

