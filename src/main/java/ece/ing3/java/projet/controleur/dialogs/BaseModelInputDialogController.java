package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Base de contrôleur de boîte de dialogue demandant d'entrer des valeurs correspondants à un modèle BDD
 */
public abstract class BaseModelInputDialogController implements ActionListener, WindowListener {
	/**
	 * Boîte de dialogue associée
	 */
	protected BaseModelInputDialog dialog;
	/**
	 * Objet qui écoute l'issue de la décision
	 */
	protected DialogListener listener;

	/**
	 * Créer un nouveau contrôleur pour une boîte de dialogue d'entrée générique et initialise cette boite de dialogue.
	 *
	 * @param dialog Boîte de dialogue associée
	 * @param listener Objet qui écoute l'issue de la décision
	 */
	protected BaseModelInputDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		this.dialog = dialog;
		this.listener = listener;
		this.dialog.setValidated( false );
		this.dialog.addActionListener( this );
		this.dialog.addWindowListener( this );
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
				this.dialog.validateInput();
				this.dialog.setValidated( true );
				dialog.dispose();
			} catch( IllegalArgumentException e ) {
				Utils.error( Application.get(), e.getLocalizedMessage() );
			}
		}
	}

	@Override
	public void windowOpened( WindowEvent windowEvent ) {}

	@Override
	public void windowClosing( WindowEvent windowEvent ) {}

	/**
	 * Méthode réagissant à la fermeture de la boîte de dialogue, appelant l'objet écoutant l'issue selon la décision prise
	 *
	 * @param windowEvent Événement de fermeture
	 */
	@Override
	public void windowClosed( WindowEvent windowEvent ) {
		if( this.dialog.isValidated() ) {
			listener.onDialogSubmitted( dialog );
		} else {
			listener.onDialogCancelled( dialog );
		}
	}

	@Override
	public void windowIconified( WindowEvent windowEvent ) {}

	@Override
	public void windowDeiconified( WindowEvent windowEvent ) {}

	@Override
	public void windowActivated( WindowEvent windowEvent ) {}

	@Override
	public void windowDeactivated( WindowEvent windowEvent ) {}
}
