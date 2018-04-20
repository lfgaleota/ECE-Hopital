package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.vue.dialogs.update.DocteurUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class DocteurUpdateDialogController extends ModelUpdateDialogController {
	private DocteurUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static DocteurUpdateDialog createDialog( Docteur existingModel, DialogListener listener ) {
		return (DocteurUpdateDialog) createDialog( new DocteurUpdateDialog( existingModel ), listener );
	}
}

