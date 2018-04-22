package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

/**
 * Base de contrôleur de boîte de dialogue demandant d'entrer des valeurs correspondants à un modèle BDD
 */
public abstract class BaseModelInputDialogController extends BaseValidatedDialogController {
	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue de saisie définie.
	 *
	 * @param dialog   Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	protected BaseModelInputDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}
}
