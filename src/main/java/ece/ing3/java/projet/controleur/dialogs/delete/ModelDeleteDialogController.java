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

/**
 * Contrôleur de boîte de dialogue demandant à l'utilisateur s'il veut supprimer certaines instances de modèles BDD
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ModelDeleteDialogController implements PropertyChangeListener, WindowListener {
	/**
	 * Boîte de dialogue associée
	 */
	private ModelDeleteDialog dialog;
	/**
	 * Objet qui écoute l'issue de la décision
	 */
	private DialogListener listener;

	private ModelDeleteDialogController( List<? extends Model> selectedModels, DialogListener listener ) {
		this.dialog = new ModelDeleteDialog( selectedModels );
		this.listener = listener;
		this.dialog.addWindowListener( this );
		this.dialog.getOptionPane().addPropertyChangeListener( this );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de suppression d'un ensemble de modèles donnée.
	 *
	 * @param selectedModels Modèles sélectionnés
	 * @param listener Objet qui écoute l'issue de la décision
	 * @return Boîte de dialogue
	 */
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

	/**
	 * Méthode réagissant à la fermeture de la boîte de dialogue, appelant l'objet écoutant l'issue selon la décision prise
	 *
	 * @param windowEvent Événement de fermeture
	 */
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

	/**
	 * Méthode d'écoute réagissant au choix de l'utilisateur
	 *
	 * @param propertyChangeEvent Événement de changement
	 */
	@Override
	public void propertyChange( PropertyChangeEvent propertyChangeEvent ) {
		String prop = propertyChangeEvent.getPropertyName();

		if( dialog.isVisible() && ( propertyChangeEvent.getSource() == dialog.getOptionPane() ) && ( prop.equals( JOptionPane.VALUE_PROPERTY ) ) ) {
			dialog.dispose();
		}
	}
}
