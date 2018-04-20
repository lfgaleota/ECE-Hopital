package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.vue.dialogs.update.EmployeUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class EmployeUpdateDialogController extends ModelUpdateDialogController {
	private EmployeUpdateDialogController( ModelUpdateDialog<?> dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static EmployeUpdateDialog createDialog( Employe existingModel, DialogListener listener ) {
		return (EmployeUpdateDialog) createDialog( new EmployeUpdateDialog( existingModel ), listener );
	}
}

