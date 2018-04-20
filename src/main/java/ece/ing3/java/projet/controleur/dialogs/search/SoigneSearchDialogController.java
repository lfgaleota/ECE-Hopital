package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.SoigneSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche de Prise en Charge
 */
public class SoigneSearchDialogController extends ModelSearchDialogController {
	private SoigneSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Prise en Charge.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new SoigneSearchDialog(), listener );
	}
}

