package ece.ing3.java.projet.controleur.dialogs;

import ece.ing3.java.projet.controleur.panels.ConfigurationPanelController;
import ece.ing3.java.projet.vue.dialogs.ConfigurationDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Contrôleur de boîte de configuration
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ConfigurationDialogController implements ActionListener {
	private ConfigurationDialog dialog;

	private ConfigurationDialogController( ConfigurationDialog dialog ) {
		this.dialog = dialog;
		this.dialog.setValidated( false );
		this.dialog.addActionListener( this );
		this.dialog.setVisible( true );
	}

	/**
	 * Créer une nouvelle boîte de dialogue de configuration bloquante.
	 *
	 * @return Boîte de dialogue de configuration
	 */
	public static ConfigurationDialog createDialog() {
		ConfigurationDialog dialog = new ConfigurationDialog( ConfigurationPanelController.createPanel() );
		new ConfigurationDialogController( dialog );
		return dialog;
	}

	/**
	 * Méthode d'écoute réagissant au choix de l'utilisateur.
	 *
	 * @param actionEvent Événement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == dialog.getPanel().getSave() ) {
			dialog.setValidated( true );
			dialog.dispose();
		}
	}
}
