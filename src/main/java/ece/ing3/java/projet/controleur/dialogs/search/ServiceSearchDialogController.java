package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ServiceSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche de Service
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ServiceSearchDialogController extends ModelSearchDialogController {
	private ServiceSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche de Service.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new ServiceSearchDialog(), listener );
	}
}
