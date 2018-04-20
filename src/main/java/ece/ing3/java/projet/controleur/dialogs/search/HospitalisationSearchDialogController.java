package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.HospitalisationSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche d'Hospitalisation
 */
public class HospitalisationSearchDialogController extends ModelSearchDialogController {
	private HospitalisationSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Hospitalisation.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new HospitalisationSearchDialog(), listener );
	}
}
