package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.vue.dialogs.update.SoigneUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class SoigneUpdateDialogController extends ModelUpdateDialogController {
	private SoigneUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static SoigneUpdateDialog createDialog( Soigne existingModel, DialogListener listener ) {
		return (SoigneUpdateDialog) createDialog( new SoigneUpdateDialog( existingModel ), listener );
	}
}

