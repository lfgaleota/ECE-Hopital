package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.controleur.panels.ServicePanelController;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.ServiceSearchDialog;

public class ServiceSearchDialogController extends ModelSearchDialogController {
	private ServiceSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new ServiceSearchDialog(), listener );
	}

}
