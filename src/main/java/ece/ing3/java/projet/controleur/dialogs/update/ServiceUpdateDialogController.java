package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.vue.dialogs.update.ServiceUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class ServiceUpdateDialogController extends ModelUpdateDialogController {
	private ServiceUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ServiceUpdateDialog createDialog( Service existingModel, DialogListener listener ) {
		return (ServiceUpdateDialog) createDialog( new ServiceUpdateDialog( existingModel ), listener );
	}
}

