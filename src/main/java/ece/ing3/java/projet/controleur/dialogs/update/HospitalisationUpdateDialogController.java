package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.vue.dialogs.update.HospitalisationUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class HospitalisationUpdateDialogController extends ModelUpdateDialogController {
	private HospitalisationUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static HospitalisationUpdateDialog createDialog( Hospitalisation existingModel, DialogListener listener ) {
		return (HospitalisationUpdateDialog) createDialog( new HospitalisationUpdateDialog( existingModel ), listener );
	}
}

