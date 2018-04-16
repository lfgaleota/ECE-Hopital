package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.database.sql.clauses.Where;
import ece.ing3.java.projet.utils.DialogListener;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.components.inputs.BaseInput;
import ece.ing3.java.projet.vue.dialogs.ModelSearchDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ModelSearchDialogController implements ActionListener, WindowListener {
	private ModelSearchDialog dialog;
	private DialogListener listener;

	protected ModelSearchDialogController( ModelSearchDialog dialog, DialogListener listener ) {
		this.dialog = dialog;
		this.listener = listener;
		this.dialog.setValidated( false );
		this.dialog.addActionListener( this );
		this.dialog.addWindowListener( this );
	}

	public static ModelSearchDialog createDialog( ModelSearchDialog dialog, DialogListener listener ) {
		new ModelSearchDialogController( dialog, listener );
		return dialog;
	}

	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == dialog.getCancel() ) {
			dialog.dispose();
			return;
		}
		if( actionEvent.getSource() == dialog.getSubmit() ) {
			try {
				this.dialog.getWhereClause();
				this.dialog.setValidated( true );
				dialog.dispose();
			} catch( IllegalArgumentException e ) {
				Utils.error( this.dialog, e.getLocalizedMessage() );
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
