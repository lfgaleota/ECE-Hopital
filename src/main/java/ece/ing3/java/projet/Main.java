package ece.ing3.java.projet;

import ece.ing3.java.projet.configuration.Configuration;
import ece.ing3.java.projet.controleur.dialogs.ConfigurationDialogController;
import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.ConfigurationException;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.Application;
import ece.ing3.java.projet.vue.dialogs.ConnectingDialog;

import javax.swing.*;

/**
 * Classe princiapale du programme
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Main {
	/**
	 * Point d'entrée du programme, initialise les différents composants.
	 *
	 * @param args Arguments
	 */
	public static void main( String[] args ) {
		try {
			UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );

			Configuration.init();
			while( true ) {
				ConnectingDialog connectingDialog = new ConnectingDialog();
				try {
					Database.init();
					connectingDialog.dispose();
					break;
				} catch( ConfigurationException e ) {
					connectingDialog.dispose();
				} catch( DatabaseException e ) {
					connectingDialog.dispose();
					e.printStackTrace();
					Utils.error( "Une erreur est survenue durant la connexion à la base de donnée : \n" + e.getLocalizedMessage() );
				}
				if( ConfigurationDialogController.createDialog().get() != JOptionPane.YES_OPTION ) {
					Utils.error( "L'application ne peut continuer sans connexion à la base de donnée.\nElle va donc se fermer." );
					System.exit( 20 );
				}
			}

			Application.get();
		} catch( Exception e ) {
			e.printStackTrace();
			Utils.error( "L'application s'est arrêtée suite à une erreur inattendue." + ( e.getLocalizedMessage() != null ? "\n" + e.getLocalizedMessage() : "" ) );
			System.exit( 1 );
		}
	}

	/**
	 * Méthode fermant les différents composants initialisés.
	 */
	public static void quit() {
		try {
			Database.close();
		} catch( DatabaseException e ) {

		}
	}
}
