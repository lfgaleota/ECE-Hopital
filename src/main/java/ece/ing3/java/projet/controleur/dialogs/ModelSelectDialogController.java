package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.ModelSelectDialog;
import ece.ing3.java.projet.vue.panels.ModelPanel;

import java.awt.*;

/**
 * Base de contrôleur de boîte de dialogue de sélection de modèle
 *
 * @param <M> Type de modèle à considérer
 */
public class ModelSelectDialogController<M extends Model> extends BaseValidatedDialogController {
	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue de sélection de modèle et initialise cette boite de dialogue.
	 *
	 * @param parent Boîte de dialogue parente
	 * @param panel Panel à encapsuler
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	public ModelSelectDialogController( Window parent, ModelPanel<M> panel, DialogListener listener ) {
		super( new ModelSelectDialog<>( parent, panel ), listener );
	}

	/**
	 * Récupère la boîte de dialogue associée.
	 *
	 * @return Boîte de dialogue associée
	 */
	@SuppressWarnings( "unchecked" )
	public ModelSelectDialog<M> getDialog() {
		return (ModelSelectDialog<M>) dialog;
	}
}
