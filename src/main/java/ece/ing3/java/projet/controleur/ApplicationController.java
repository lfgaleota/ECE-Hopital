package ece.ing3.java.projet.controleur;

import ece.ing3.java.projet.Main;
import ece.ing3.java.projet.vue.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Contrôleur de fenêtre d'Application
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ApplicationController implements WindowListener {
	/**
	 * Fenêtre d'Application associée
	 */
	private Application win;

	/**
	 * Créer un nouveau contrôleur d'application pour une fenêtre donnée, et l'initialise.
	 *
	 * @param win Fenêtre associée
	 */
	public ApplicationController( Application win ) {
		this.win = win;
		this.win.addWindowListener( this );
	}

	@Override
	public void windowOpened( WindowEvent windowEvent ) {}

	@Override
	public void windowClosing( WindowEvent windowEvent ) {}

	/**
	 * Méthode réagissant à la fermeture de la fenêtre. Demande au programme de quitter.
	 *
	 * @param windowEvent Événement de fermeture
	 */
	@Override
	public void windowClosed( WindowEvent windowEvent ) {
		Main.quit();
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
