package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.vue.dialogs.update.MaladeUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class MaladeUpdateDialogController extends ModelUpdateDialogController {
	private MaladeUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static MaladeUpdateDialog createDialog( Malade existingModel, DialogListener listener ) {
		return (MaladeUpdateDialog) createDialog( new MaladeUpdateDialog( existingModel ), listener );
	}
}


