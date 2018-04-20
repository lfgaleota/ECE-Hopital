package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.InfirmierSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche d'Infirmier
 */
public class InfirmierSearchDialogController extends ModelSearchDialogController {
	private InfirmierSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Infirmier.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new InfirmierSearchDialog(), listener );
	}
}
