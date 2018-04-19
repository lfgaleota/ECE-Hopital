package ece.ing3.java.projet.controleur.dialogs.delete;

import ece.ing3.java.projet.database.sql.Model;
import ece.ing3.java.projet.interfaces.DialogListener;
import ece.ing3.java.projet.vue.dialogs.delete.ModelDeleteDialog;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ModelDeleteDialogController implements PropertyChangeListener, WindowListener {
	private ModelDeleteDialog dialog;
	private DialogListener listener;

	private ModelDeleteDialogController( List<? extends Model> selectedModels, DialogListener listener ) {
		this.dialog = new ModelDeleteDialog( selectedModels );
		this.listener = listener;
		this.dialog.addWindowListener( this );
		this.dialog.getOptionPane().addPropertyChangeListener( this );
	}

	public static ModelDeleteDialog createDialog( List<? extends Model> selectedModels, DialogListener listener ) {
		ModelDeleteDialogController controller = new ModelDeleteDialogController( selectedModels, listener );
		return controller.dialog;
	}

	@Override
	public void windowOpened( WindowEvent windowEvent ) {
	}

	@Override
	public void windowClosing( WindowEvent windowEvent ) {
	}

	@Override
	public void windowClosed( WindowEvent windowEvent ) {
		if( ( (Integer) this.dialog.getOptionPane().getValue() ) == JOptionPane.YES_OPTION ) {
			listener.onDialogSubmitted( dialog );
		} else {
			listener.onDialogCancelled( dialog );
		}
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

	@Override
	public void propertyChange( PropertyChangeEvent propertyChangeEvent ) {
		String prop = propertyChangeEvent.getPropertyName();

		if( dialog.isVisible() && ( propertyChangeEvent.getSource() == dialog.getOptionPane() ) && ( prop.equals( JOptionPane.VALUE_PROPERTY ) ) ) {
			dialog.dispose();
		}
	}
}
