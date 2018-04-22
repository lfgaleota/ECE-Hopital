/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */

package ece.ing3.java.projet.vue;

import javax.swing.*;

import java.awt.*;

import ece.ing3.java.projet.controleur.ApplicationController;
import ece.ing3.java.projet.controleur.panels.ConfigurationPanelController;
import ece.ing3.java.projet.enums.ModelControllers;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.panels.StatistiquesPanel;
import ece.ing3.java.projet.vue.panels.TabPanel;

/**
 * Fenetre principale de l'application
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class Application extends JFrame {
	private static Application instance;

	private JTabbedPane tabs;

	private Application() {
		//propriétés de la fenêtre
		this.setTitle( "Projet Hopital" );
		this.setSize( ( int ) getToolkit().getScreenSize().getWidth(), ( ( int ) getToolkit().getScreenSize().getHeight() ) );
		this.setResizable( true );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.toFront(); // mettre au 1er plan

		setLayout( new BorderLayout() );
		//création du tableau de panels
		tabs = TabPanel.create();

		try {
			for( ModelControllers modelController : ModelControllers.values() ) {
				tabs.addTab( modelController.getPrettyName(), modelController.getPanelController().getPanel() );
			}
		} catch( ExceptionInInitializerError e ) {
			this.dispose();
			throw new RuntimeException( ( e.getCause() != null ? e.getCause().getLocalizedMessage() : e.getLocalizedMessage() ), e );
		}

		tabs.addTab( Strings.get( "reporting.title" ), new StatistiquesPanel() );
		tabs.addTab( Strings.get( "configuration.title" ), ConfigurationPanelController.createPanel() );

		new ApplicationController( this );

		add( tabs, BorderLayout.CENTER );

		this.setVisible( true );
	}

	public static Application get() {
		if( instance == null ) {
			instance = new Application();
		}

		return instance;
	}
	/**
	 * Getter
	 * @return l'ensemble des panels
	 */
	public JTabbedPane getTabs() {
		return tabs;
	}
}
