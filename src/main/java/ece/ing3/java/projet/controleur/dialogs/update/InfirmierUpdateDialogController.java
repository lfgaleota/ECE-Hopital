package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.vue.dialogs.update.InfirmierUpdateDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

/**
 * Contrôleur de boîte de dialogue de mise à jour de modèle Infirmier
 */
public class InfirmierUpdateDialogController extends ModelUpdateDialogController {
	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue de mise à jour de modèle Infirmier et initialise cette boite de dialogue.
	 *
	 * @param dialog Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	private InfirmierUpdateDialogController( ModelUpdateDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Instancie une nouvelle boîte de dialogue de mise à jour et l'initialise auprès d'un nouveau contrôleur.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de mise à jour initialisée
	 */
	public static InfirmierUpdateDialog createDialog( Infirmier existingModel, DialogListener listener ) {
		return (InfirmierUpdateDialog) createDialog( new InfirmierUpdateDialog( existingModel ), listener );
	}
}

