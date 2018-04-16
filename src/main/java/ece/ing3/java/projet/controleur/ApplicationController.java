package ece.ing3.java.projet.controleur;

import ece.ing3.java.projet.Main;
import ece.ing3.java.projet.vue.Application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ApplicationController implements ActionListener, WindowListener {
	private Application win;

	public ApplicationController( Application win ) {
		this.win = win;
		this.win.addWindowListener( this );
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ) {

	}

	@Override
	public void windowOpened( WindowEvent windowEvent ) {}

	@Override
	public void windowClosing( WindowEvent windowEvent ) {}

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
