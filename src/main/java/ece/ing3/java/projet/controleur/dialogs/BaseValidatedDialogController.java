package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.BaseValidatedDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Base de contrôleur de boîte de dialogue demandant d'entrer des valeurs correspondants à un modèle BDD
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public abstract class BaseValidatedDialogController implements ActionListener, WindowListener {
	/**
	 * Boîte de dialogue associée
	 */
	protected BaseValidatedDialog dialog;
	/**
	 * Objet qui écoute l'issue de la décision
	 */
	protected DialogListener listener;

	private boolean alreadyHandled;

	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue d'entrée générique et initialise cette boite de dialogue.
	 *
	 * @param dialog   Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	protected BaseValidatedDialogController( BaseValidatedDialog dialog, DialogListener listener ) {
		this.dialog = dialog;
		this.listener = listener;
		this.dialog.setValidated( false );
		this.dialog.addActionListener( this );
		this.dialog.addWindowListener( this );
		this.alreadyHandled = false;
	}


	/**
	 * Méthode d'écoute réagissant au choix de l'utilisateur, validant les entrées si nécessaire.
	 *
	 * @param actionEvent Événement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == dialog.getCancel() ) {
			dialog.dispose();
			return;
		}
		if( actionEvent.getSource() == dialog.getSubmit() ) {
			try {
				this.dialog.validateContent();
				this.dialog.setValidated( true );
				dialog.dispose();
			} catch( IllegalArgumentException e ) {
				Utils.error( Application.get(), e.getLocalizedMessage() );
			}
		}
	}

	@Override
	public void windowOpened( WindowEvent windowEvent ) {
	}

	/**
	 * Méthode réagissant au début de la fermeture de la boîte de dialogue, appelant l'objet écoutant l'issue selon la décision prise
	 *
	 * @param windowEvent Événement de fermeture
	 */
	@Override
	public void windowClosing( WindowEvent windowEvent ) {
		if( !alreadyHandled ) {
			if( this.dialog.isValidated() ) {
				listener.onDialogSubmitted( dialog );
			} else {
				listener.onDialogCancelled( dialog );
			}
		}
		alreadyHandled = true;
	}

	/**
	 * Méthode réagissant à la fermeture de la boîte de dialogue, appelant l'objet écoutant l'issue selon la décision prise
	 *
	 * @param windowEvent Événement de fermeture
	 */
	@Override
	public void windowClosed( WindowEvent windowEvent ) {
		// Pas très propre...
		// windowClosed est lancé quand on ferme par dispose(), et windowClosing() par la croix.
		// On fait donc en sorte de lancer l'action de windowClosing() quand on a windowClosed() tout en s'assurant de ne pas traiter deux fois la chose
		windowClosing( windowEvent );
	}

	@Override
	public void windowIconified( WindowEvent windowEvent ) {
	}

	@Override
	public void windowDeiconified( WindowEvent windowEvent ) {
	}

	@Override
	public void windowActivated( WindowEvent windowEvent ) {
	}

	@Override
	public void windowDeactivated( WindowEvent windowEvent ) {
	}
}
