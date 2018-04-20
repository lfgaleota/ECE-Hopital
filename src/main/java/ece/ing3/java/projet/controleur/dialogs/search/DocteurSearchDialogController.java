package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.DocteurSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche de Docteur
 */
public class DocteurSearchDialogController extends ModelSearchDialogController {
	private DocteurSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Docteur.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new DocteurSearchDialog(), listener );
	}
}
