package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.vue.dialogs.update.ChambreUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

/**
 * Contrôleur de boîte de mise à jour de recherche de Chambre
 */
public class ChambreUpdateDialogController extends ModelUpdateDialogController {
	private ChambreUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de mise à jour de Chambre
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ChambreUpdateDialog createDialog( Chambre existingModel, DialogListener listener ) {
		return (ChambreUpdateDialog) createDialog( new ChambreUpdateDialog( existingModel ), listener );
	}
}

