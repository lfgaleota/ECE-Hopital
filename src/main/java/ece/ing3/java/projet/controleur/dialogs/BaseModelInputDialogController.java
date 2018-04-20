package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

/**
 * Base de contrôleur de boîte de dialogue demandant d'entrer des valeurs correspondants à un modèle BDD
 */
public abstract class BaseModelInputDialogController extends BaseValidatedDialogController {
	protected BaseModelInputDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}
}
