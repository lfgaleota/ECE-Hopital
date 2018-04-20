package ece.ing3.java.projet.controleur.dialogs.update;

import ece.ing3.java.projet.controleur.dialogs.BaseModelInputDialogController;
import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import ece.ing3.java.projet.vue.dialogs.update.ModelUpdateDialog;

/**
 * Contrôleur de boîte de dialogue de mise à jour de modèle générique
 */
public class ModelUpdateDialogController extends BaseModelInputDialogController {
	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue de mise à jour et initialise cette boite de dialogue.
	 *
	 * @param dialog Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	protected ModelUpdateDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Initialise une nouvelle boîte de dialogue de mise à jour auprès du contrôleur.
	 *
	 * @param dialog Boîte de dialogue à initialiser
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de mise à jour initialisée
	 */
	public static ModelUpdateDialog<? extends Model> createDialog( ModelUpdateDialog<? extends Model> dialog, DialogListener listener ) {
		new ModelUpdateDialogController( dialog, listener );
		return dialog;
	}
}
