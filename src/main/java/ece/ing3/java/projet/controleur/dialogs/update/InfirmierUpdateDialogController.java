package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.dialogs.update.InfirmierUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class InfirmierUpdateDialogController extends ModelUpdateDialogController {
	private InfirmierUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static InfirmierUpdateDialog createDialog( Infirmier existingModel, DialogListener listener ) {
		return (InfirmierUpdateDialog) createDialog( new InfirmierUpdateDialog( existingModel ), listener );
	}
}

