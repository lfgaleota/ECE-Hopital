/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 */

package ece.ing3.java.projet.vue;

import javax.swing.*;

import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import ece.ing3.java.projet.controleur.ApplicationController;
import ece.ing3.java.projet.enums.ModelControllers;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.vue.panels.TabPanel;

/**
 * Fenetre principale de l'application
 *
 * @author Nicolas , Virgile
 */
public class Application extends JFrame implements ActionListener {
	private static Application instance;

	//tableau accueillant les tables
	private JTabbedPane tabs;
	//Objets de la classe Statistiques
	private Statistiques mesStats = new Statistiques();

	/**
	 * Constructeur par défaut de la classe Application
	 * Permet de régler les fonctionnalités de la fenêtre
	 */
	private Application() {
		this.setTitle("Projet Hopital");
		this.setSize((int) getToolkit().getScreenSize().getWidth(), ((int) getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.toFront(); // forcer la fenêtre au 1er plan

		//création de la fenêtre de statistiques
		mesStats.creer_Statistiques();

		setLayout(new BorderLayout());
		//preparation du panel
		TabPanel.prepare();
		tabs = new TabPanel();
		TabPanel.finish();
		
		//ajout des tables
		for (ModelControllers modelController : ModelControllers.values()) {
			tabs.addTab(modelController.getPrettyName(), modelController.getPanelController().getPanel());
		}

		new ApplicationController(this);

		add(tabs, BorderLayout.CENTER);

		this.setVisible(true);
	}
	/**
	 * Méthode appelée dans le main. Crée un objet de la classe.
	 * @return une instance d'Application
	 */
	public static Application get() {
		if (instance == null) {
			instance = new Application();
		}

		return instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

	/**
	 * Getter 
	 * @return tabs , notre JTabbedPane principal
	 */
	public JTabbedPane getTabs() {
		return tabs;
	}

}
