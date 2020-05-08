package ece.ing3.java.projet.controleur.panels;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.enums.JDBCDriver;
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
		panel.getDatabaseDriver().addActionListener( this );
	}

	/**
	 * Créer un nouveau panneau de configuration
	 *
	 * @return Panneau de configuration
	 */
	public static ConfigurationPanel createPanel() {
		ConfigurationPanel panel = new ConfigurationPanel();
		ConfigurationPanelController controller = new ConfigurationPanelController( panel );
		controller.showDependingOnDriver();
		return panel;
	}

	private void save() {
		Map<String, Object> values = this.panel.getValues();

		for( Map.Entry<String, Object> entry : values.entrySet() ) {
			if( entry.getValue() instanceof String ) {
				Configuration.set( entry.getKey(), (String) entry.getValue() );
			} else {
				Configuration.set( entry.getKey(), entry.getValue().toString() );
			}
		}

		// If we use the embedded database driver
		if( values.get( "database.driver" ).equals( JDBCDriver.Embedded.getClassName() ) ) {
			// Get the database URL
			String dbUrl = Database.getEmbeddedUserDatabaseUrl();
			if( dbUrl == null ) {
				Utils.error( "Erreur de création de la base de donnée." );
				return;
			}
			// Force configuration values
			Configuration.set( "database.url", dbUrl );
			Configuration.set( "database.username", "" );
			Configuration.set( "database.password", "" );
		}

		try {
			Configuration.save();
		} catch( BackingStoreException e ) {
			e.printStackTrace();
			Utils.error( "Erreur de sauvegarde en configuration." );
		}
	}

	private void showDependingOnDriver() {
		JDBCDriver driver = (JDBCDriver) panel.getDatabaseDriver().getSelectedItem();

		if( driver == JDBCDriver.Embedded ) {
			panel.isUrlShown( false );
			panel.isUsernameShown( false );
			panel.isPasswordShown( false );
		} else {
			panel.isUrlShown( true );
			panel.isUsernameShown( true );
			panel.isPasswordShown( true );
		}
	}

	/**
	 * Méthode réagissant aux choix de l'utilisateur sur le panneau (pour la sauvegarde).
	 *
	 * @param actionEvent Événement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == panel.getSave() ) {
			save();
		}
		if( actionEvent.getSource() == panel.getDatabaseDriver() ) {
			showDependingOnDriver();
		}
	}
}
