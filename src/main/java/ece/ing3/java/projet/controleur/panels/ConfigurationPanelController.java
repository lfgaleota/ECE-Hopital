package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.panels.ConfigurationPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.prefs.BackingStoreException;

/**
 * Contrôleur de panneau de configuration
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class ConfigurationPanelController implements ActionListener {
	private ConfigurationPanel panel;

	private ConfigurationPanelController( ConfigurationPanel panel ) {
		this.panel = panel;
		panel.addActionListener( this );
	}

	/**
	 * Créer un nouveau panneau de configuration
	 *
	 * @return Panneau de configuration
	 */
	public static ConfigurationPanel createPanel() {
		ConfigurationPanel panel = new ConfigurationPanel();
		new ConfigurationPanelController( panel );
		return panel;
	}

	private void save() {
		for( Map.Entry<String, Object> entry : this.panel.getValues().entrySet() ) {
			if( entry.getValue() instanceof String ) {
				Configuration.set( entry.getKey(), (String) entry.getValue() );
			} else {
				Configuration.set( entry.getKey(), entry.getValue().toString() );
			}
		}

		try {
			Configuration.save();
		} catch( BackingStoreException e ) {
			e.printStackTrace();
			Utils.error( "Erreur de sauvegarde en configuration." );
		}
	}


	/**
	 * Méthode réagissant au choix de l'utilisateur sur le panneau (pour la sauvegarde).
	 *
	 * @param actionEvent Événement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == panel.getSave() ) {
			save();
		}
	}
}
