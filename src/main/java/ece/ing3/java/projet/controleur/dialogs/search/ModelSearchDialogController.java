package ece.ing3.java.projet.controleur.dialogs.search;

import ece.ing3.java.projet.controleur.dialogs.BaseModelInputDialogController;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

import java.awt.event.ActionEvent;

/**
 * Contrôleur de boîte de dialogue de recherche de modèle générique
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ModelSearchDialogController extends BaseModelInputDialogController {
	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue de recherche et initialise cette boite de dialogue.
	 *
	 * @param dialog Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	protected ModelSearchDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		super( dialog, listener );
	}

	/**
	 * Initialise une nouvelle boîte de dialogue de recherche auprès du contrôleur.
	 *
	 * @param dialog Boîte de dialogue à initialiser
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue de recherche initialisée
	 */
	public static ModelSearchDialog createDialog( ModelSearchDialog dialog, DialogListener listener ) {
		new ModelSearchDialogController( dialog, listener );
		return dialog;
	}

	/**
	 * Méthode réagissant au choix de l'utilisateur.
	 *
	 * @param actionEvent Événement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == ( (ModelSearchDialog) dialog ).getReset() ) {
			this.dialog.setValidated( true );
			( (ModelSearchDialog) this.dialog ).setResetFilters( true );
			dialog.dispose();
		}

		super.actionPerformed( actionEvent );
	}
}
