package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.dialogs.update.ChambreUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

public class ChambreUpdateDialogController extends ModelUpdateDialogController {
	private ChambreUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	public static ChambreUpdateDialog createDialog( Chambre existingModel, DialogListener listener ) {
		return (ChambreUpdateDialog) createDialog( new ChambreUpdateDialog( existingModel ), listener );
	}
}

