package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.search.EmployeSearchDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

/**
 * Contrôleur de boîte de dialogue de recherche d'Employe
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class EmployeSearchDialogController extends ModelSearchDialogController {
	private EmployeSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de recherche d'Employe.
	 *
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche
	 */
	public static ModelSearchDialog createDialog( DialogListener listener ) {
		return createDialog( new EmployeSearchDialog(), listener );
	}
}
