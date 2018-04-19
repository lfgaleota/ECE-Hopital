package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.BaseModelInputDialog;
import ece.ing3.java.projet.vue.dialogs.search.ModelSearchDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public abstract class BaseModelInputDialogController implements ActionListener, WindowListener {
	private BaseModelInputDialog dialog;
	private DialogListener listener;

	protected BaseModelInputDialogController( BaseModelInputDialog dialog, DialogListener listener ) {
		this.dialog = dialog;
		this.listener = listener;
		this.dialog.setValidated( false );
		this.dialog.addActionListener( this );
		this.dialog.addWindowListener( this );
	}

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
