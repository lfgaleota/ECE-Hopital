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
 * @author Virgile
 */

/**
 * Fenetre principale de l'application
 *
 * @author Nicolas
 */
public class Application extends JFrame implements ActionListener {
	private JTabbedPane tabs;
	/// LE JSPLIT QUIVA PERMETTRE D AVOIR DEUX PANNEAUX PAR ONGLET , UN POUR LES
	/// BOUTONS DU HAUT L AUTRE POUR L AFFICHAGE
	// JSplitPane ongletandhaut = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // ou
	/// VERTICAL_SPLIT ///METHODE AVEC ONGLET/BOUTONS INDEPENDANT
	private JSplitPane split;

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET0
	/*private JButton boutonstat = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_STATLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 6, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonrechercher = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_RECHERCHERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonMA = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_MODIFIERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonAjouter = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_AJOUTERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonSup = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SUPPRIMERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 1
	private JButton boutonstat1 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_STATLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 6, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonrechercher1 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_RECHERCHERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonMA1 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_MODIFIERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonAjouter1 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_AJOUTERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonSup1 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SUPPRIMERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 2
	private JButton boutonstat2 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_STATLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 6, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonrechercher2 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_RECHERCHERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonMA2 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_MODIFIERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonAjouter2 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_AJOUTERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonSup2 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SUPPRIMERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 3
	private JButton boutonstat3 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_STATLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 6, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonrechercher3 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_RECHERCHERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonMA3 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_MODIFIERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));;
	private JButton boutonAjouter3 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_AJOUTERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonSup3 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SUPPRIMERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 4
	private JButton boutonstat4 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_STATLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 6, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonrechercher4 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_RECHERCHERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonMA4 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_MODIFIERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME / 8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonAjouter4 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_AJOUTERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
	private JButton boutonSup4 = new JButton(new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SUPPRIMERLOGO).getImage()
			.getScaledInstance(Constants.WIDTH_FRAME /8, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));

	//private JButton boutonSup5 = new JButton("ALALALALLA");

	//private static int width = 1100;
//	private static int height = 900;

	/// LES DIFFERENTS PANNEAUX ONGLETS SONT CREES ICI , AVEC UN CONSTRUCTEUR
	/// UTILISANT UNE COULEUR EN PARAMETRE      LE BASEPANEL(1) est  onglet d accueil
	private BasePanel[] tPan = { new BasePanel(1), new BasePanel(Color.WHITE), new BasePanel(Color.WHITE),
			new BasePanel(Color.WHITE), new BasePanel(Color.WHITE), new BasePanel(Color.WHITE),
			new BasePanel(Color.GRAY) };*/

	/// LA FENETRE CONTIENT LES PANNEAUX (CONTENU DES ONGLETS)
	public Application() {
		// this.setLocationRelativeTo(null);
		this.setTitle( "Projet Hopital" );
		this.setSize( ( int ) getToolkit().getScreenSize().getWidth(), ( ( int ) getToolkit().getScreenSize().getHeight() ) );
		this.setResizable( true );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		this.toFront(); // mettre au 1er plan

		// MMETHODE ONGLET/BOUTONS INDEPENDANT /// LA LIGNE DE SEPARATION A UNE LARGEUR
		// DE PX ET EST EN 80 ongletandhaut.setDividerSize(1);
		// ongletandhaut.setDividerLocation(100);

		//Mise en forme des boutons
		/*boutonstat.setBackground(Color.WHITE);boutonstat1.setBackground(Color.WHITE);boutonstat2.setBackground(Color.WHITE);boutonstat3.setBackground(Color.WHITE);boutonstat4.setBackground(Color.WHITE);
		boutonstat.setBorderPainted(false);boutonstat1.setBorderPainted(false);boutonstat2.setBorderPainted(false);boutonstat3.setBorderPainted(false);boutonstat4.setBorderPainted(false);

		boutonrechercher.setBackground(Color.WHITE);boutonrechercher1.setBackground(Color.WHITE);boutonrechercher2.setBackground(Color.WHITE);boutonrechercher3.setBackground(Color.WHITE);boutonrechercher4.setBackground(Color.WHITE);
		boutonrechercher.setBorderPainted(false);boutonrechercher1.setBorderPainted(false);boutonrechercher2.setBorderPainted(false);boutonrechercher3.setBorderPainted(false);boutonrechercher4.setBorderPainted(false);

		boutonMA.setBackground(Color.WHITE);boutonMA1.setBackground(Color.WHITE);boutonMA2.setBackground(Color.WHITE);boutonMA3.setBackground(Color.WHITE);boutonMA4.setBackground(Color.WHITE);
		boutonMA.setBorderPainted(false);boutonMA1.setBorderPainted(false);boutonMA2.setBorderPainted(false);boutonMA3.setBorderPainted(false);boutonMA4.setBorderPainted(false);

		boutonAjouter.setBackground(Color.WHITE);boutonAjouter1.setBackground(Color.WHITE);boutonAjouter2.setBackground(Color.WHITE);boutonAjouter3.setBackground(Color.WHITE);boutonAjouter4.setBackground(Color.WHITE);
		boutonAjouter.setBorderPainted(false);boutonAjouter1.setBorderPainted(false);boutonAjouter2.setBorderPainted(false);boutonAjouter3.setBorderPainted(false);boutonAjouter4.setBorderPainted(false);

		boutonSup.setBackground(Color.WHITE);boutonSup1.setBackground(Color.WHITE);boutonSup2.setBackground(Color.WHITE);boutonSup3.setBackground(Color.WHITE);boutonSup4.setBackground(Color.WHITE);
		boutonSup.setBorderPainted(false);boutonSup1.setBorderPainted(false);boutonSup2.setBorderPainted(false);boutonSup3.setBorderPainted(false);boutonSup4.setBorderPainted(false);

		/// CHAQUE PANEL DE BOUTONS CONTIENT LES BOUTONS ( TOUS DIFFERENTS)
		BasePanel panlogo = new BasePanel();

		BasePanel panboutons = new BasePanel();
		panboutons.add(boutonstat);
		panboutons.add(boutonrechercher);
		panboutons.add(boutonMA);
		panboutons.add(boutonAjouter);
		panboutons.add(boutonSup);

		BasePanel panboutons1 = new BasePanel();
		panboutons1.add(boutonstat1);
		panboutons1.add(boutonrechercher1);
		panboutons1.add(boutonMA1);
		panboutons1.add(boutonAjouter1);
		panboutons1.add(boutonSup1);

		BasePanel panboutons2 = new BasePanel();
		panboutons2.add(boutonstat2);
		panboutons2.add(boutonrechercher2);
		panboutons2.add(boutonMA2);
		panboutons2.add(boutonAjouter2);
		panboutons2.add(boutonSup2);

		BasePanel panboutons3 = new BasePanel();
		panboutons3.add(boutonstat3);
		panboutons3.add(boutonrechercher3);
		panboutons3.add(boutonMA3);
		panboutons3.add(boutonAjouter3);
		panboutons3.add(boutonSup3);

		BasePanel panboutons4 = new BasePanel();
		panboutons4.add(boutonstat4);
		panboutons4.add(boutonrechercher4);
		panboutons4.add(boutonMA4);
		panboutons4.add(boutonAjouter4);
		panboutons4.add(boutonSup4);

		BasePanel panboutons5 = new BasePanel();
		//panboutons5.add(boutonSup5);

		/// JSplitpane pour boutons/Logo
		// JSplitPane logoandboutons = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		/// panlogo, panboutons); // ou
		// VERTICAL_SPLIT
		/// LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
		// logoandboutons.setDividerSize(1);
		// logoandboutons.setDividerLocation(112);

		/// LE CONTENEUR D ONGLET QUE L ON MET A GAUCHE DE LA FENETRE
		tabs = new JTabbedPane(JTabbedPane.LEFT);

		/// METHODE PRISE SUR OPENCLASSROOM
		int i = 0;
		for ( BasePanel pan : tPan) {
			// Méthode d'ajout d'onglet
			/// CHAQUE VALEUR DE I CORRESPOND A UN INDICE D ONGLETS

			if (i == 0) {
				// SEPARE EN DEUX LE CONTENU DE L ONGLET AVEC LE PANBOUTONS CORRESPONDANT ET LE
				// PANNEAU
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panlogo, pan);
				// L'ONGLET CONTIENT CETTTE SEPARATION
				tabs.add(pan);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_APPLOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7, Constants.HEIGHT_FRAME / 7, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.WHITE);
			}
			if (i == 1) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons, pan);
				tabs.add(split);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_SERVICELOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7, Constants.HEIGHT_FRAME / 11, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.decode("#5DBFF4"));
				pan.add(display_Services()); // methode qui affiche la table dans le panel

			}
			if (i == 2) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons1, pan);
				tabs.add(split);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_CHAMBRELOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7, Constants.HEIGHT_FRAME / 11, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.decode("#70F96C"));
			}
			if (i == 3) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons2, pan);
				tabs.add(split);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_EMPLOYELOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7, Constants.HEIGHT_FRAME / 11, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.decode("#FEF154"));
				pan.add(display_Employes()); // methode qui affiche la table dans le panel
			}
			if (i == 4) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons3, pan);
				tabs.add(split);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_PATIENTLOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7,Constants.HEIGHT_FRAME / 11, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.decode("#A55DFF"));
			}
			if (i == 5) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons4, pan);
				tabs.add(split);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_HOSPITALISATIONLOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7,Constants.HEIGHT_FRAME / 11, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.decode("#FE3D1B"));
			}

			if (i == 6) {
				// split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons4, pan);
				tabs.add(pan);
				tabs.setIconAt(i, new ImageIcon((new ImageIcon(Constants.RESOURCE_PATH_CONFIGLOGO).getImage()
						.getScaledInstance(Constants.WIDTH_FRAME / 7, Constants.HEIGHT_FRAME / 6, Image.SCALE_DEFAULT))));
				tabs.setBackgroundAt(i, Color.WHITE);
			}

			// tabs.add("Onglet n° "+(++i), pan);

			i++;
		}
		// On passe ensuite les onglets au content pane
		// ongletandhaut.add(logoandboutons);
		// ongletandhaut.add(onglet);

		this.getContentPane().add(tabs);
		this.setVisible(true);

		boutonstat.addActionListener(this);
		boutonstat1.addActionListener(this);
		boutonstat2.addActionListener(this);
		boutonstat3.addActionListener(this);
		boutonstat4.addActionListener(this);

		/// LES BOUTONS RECHERCHER SONT TOUS SOUS ECOUTE
		boutonrechercher.addActionListener(this);
		boutonrechercher1.addActionListener(this);
		boutonrechercher2.addActionListener(this);
		boutonrechercher3.addActionListener(this);
		boutonrechercher4.addActionListener(this);

		boutonMA.addActionListener(this);
		boutonAjouter.addActionListener(this);
		boutonSup.addActionListener(this);
		*/

		setLayout( new BorderLayout() );

		TabPanel.prepare();
		tabs = new TabPanel();
		TabPanel.finish();

		for( ModelControllers modelController : ModelControllers.values() ) {
			tabs.addTab( modelController.getPrettyName(), modelController.getPanelController().getPanel() );
		}

		new ApplicationController( this );

		add( tabs, BorderLayout.CENTER );

		this.setVisible( true );
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		/*if( ( e.getSource() == boutonstat ) || ( e.getSource() == boutonstat1 ) || ( e.getSource() == boutonstat2 )
				|| ( e.getSource() == boutonstat3 ) || ( e.getSource() == boutonstat4 ) ) {
			Statistiques mesStats = new Statistiques();
			mesStats.creer_Statistiques();
		}*/
		/*
		 * ///TOUT LES BOUTONS RECHERCHER FONT LA MEME ACTION if ( (e.getSource() ==
		 * boutonrechercher) || (e.getSource() == boutonrechercher1) ||(e.getSource() ==
		 * boutonrechercher2) || (e.getSource() == boutonrechercher3) || (e.getSource()
		 * == boutonrechercher4) ) {
		 */
/*
		/// ACTION DU BOUTON RECHERCHER 1 : POUR LE MOMENT AFFICHE UNIQUEMENT LA LISTE
		/// AU MILIEU DU JPANEL CORRESPONDANT
		if ((e.getSource() == boutonrechercher)) {

			///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche
			 deja dans nos JPanel; JFrame frame = new JFrame(); frame.add(new
			 JScrollPane(maliste)); frame.setSize(300, 200); frame.setVisible(true);




		//	ModelSearchDialog marecherche = new ModelSearchDialog(maliste);

			this.repaint();
		}

		/// MEME ACTION QUE POUR BOUTON RECHERCHER 1 MAIS AFFICHE DANS UN AUTRE ONGLET
		if ((e.getSource() == boutonrechercher1)) {

			// test remplissage manuel
			Vector<String> monvecteur = new Vector<String>();
			JList<String> maliste = new JList<String>(monvecteur);

			monvecteur.add(new String("ONGLET CHAMBRE"));
			monvecteur.add(new String("Nom"));
			monvecteur.add(new String("Prenom"));
			monvecteur.add(new String("06"));
			monvecteur.add(new String("Paris"));

			tPan[2].add(maliste);


			ModelSearchDialog marecherche = new ModelSearchDialog(maliste);

			this.repaint();

		}

		/// MEME ACTION QUE POUR BOUTON RECHERCHER 2 MAIS AFFICHE DANS UN AUTRE ONGLET
		if ((e.getSource() == boutonrechercher2)) {

			// test remplissage manuel
			Vector<String> monvecteur = new Vector<String>();
			JList<String> maliste = new JList<String>(monvecteur);

			monvecteur.add(new String("ONGLET EMPLOYES"));
			monvecteur.add(new String("Nom"));
			monvecteur.add(new String("Prenom"));
			monvecteur.add(new String("06"));
			monvecteur.add(new String("Paris"));

			tPan[3].add(maliste);


			ModelSearchDialog marecherche = new ModelSearchDialog(maliste);

			this.repaint();


		}
		/// MEME ACTION QUE POUR BOUTON RECHERCHER 3 MAIS AFFICHE DANS UN AUTRE ONGLET
		if ((e.getSource() == boutonrechercher3)) {

			// test remplissage manuel
			Vector<String> monvecteur = new Vector<String>();
			JList<String> maliste = new JList<String>(monvecteur);

			monvecteur.add(new String("ONGLET PATIENT"));
			monvecteur.add(new String("Nom"));
			monvecteur.add(new String("Prenom"));
			monvecteur.add(new String("06"));
			monvecteur.add(new String("Paris"));

			tPan[4].add(maliste);


			ModelSearchDialog marecherche = new ModelSearchDialog(maliste);

			this.repaint();

			// marecherche.

		}
		/// MEME ACTION QUE POUR BOUTON RECHERCHER 4 MAIS AFFICHE DANS UN AUTRE ONGLET
		if ((e.getSource() == boutonrechercher4)) {

			// test remplissage manuel
			Vector<String> monvecteur = new Vector<String>();
			JList<String> maliste = new JList<String>(monvecteur);

			monvecteur.add(new String("ONGLET HOSPITALISATION"));
			monvecteur.add(new String("Nom"));
			monvecteur.add(new String("Prenom"));
			monvecteur.add(new String("06"));
			monvecteur.add(new String("Paris"));

			tPan[5].add(maliste);

			ModelSearchDialog marecherche = new ModelSearchDialog(maliste);

			this.repaint();
		}

		if (e.getSource() == boutonMA) {
			System.out.print("Panel à modifier avec une classe modifier");
			// ModelSearchDialog marecherche = new ModelSearchDialog( null );
		}

		if (e.getSource() == boutonAjouter) {
			System.out.print("Panel à modifier avec une classe ajouter");
			// ModelSearchDialog marecherche = new ModelSearchDialog();
		}

		if (e.getSource() == boutonSup) {
			JOptionPane.showMessageDialog(null, /// composant parent
					"Demande de suppression d'un élement de la base de données", /// message
					"Supprimer un élement de la BDD", /// titre
					JOptionPane.INFORMATION_MESSAGE /// message"
			);

		}*/

	}

	public JTabbedPane getTabs() {
		return tabs;
	}

	/**
	 * Methode pour recupérer la liste des services de la bdd et afficher la table des employes
	 * @return un jscrollbar contenant la table des services
	 */
	public JScrollPane display_Services()
	{	//On crée un liste vide
		List<Service> maliste = new ArrayList<Service>();
		try {
			maliste = Service.findList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		//création d'un tableau d'objet et tranfert des info de la liste vers le tableau Object.
		Object[][] donnees = new Object[ maliste.size()][5];
		 int i = 0;
	        while (i < maliste.size()){
	        	donnees[i][0] = maliste.get(i).getCode();
	        	donnees[i][1] = maliste.get(i).getNom();
	        	donnees[i][2] = maliste.get(i).getBatiment();
	        	donnees[i][3] = maliste.get(i).getNumeroDirecteur();
	            i++;
	        }
		//test affichage console
		for(Service elem : maliste)
		{
			System.out.println(
					elem.getCode() + "|" +
					elem.getNom() + "|" +
					elem.getBatiment() + "|" +
					elem.getNumeroDirecteur() + "\n");
		}

	    // Nom des colonnes.
		String[] colonnes = { "Code" , "Nom" , "Batiment" , "Numéro du directeur" };
		// Création de la table.
		JTable data_employes =  new JTable( donnees , colonnes );

		//Gestion de la taille des cellules de la table.
		data_employes.getColumnModel().getColumn(0).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(1).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(2).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(3).setPreferredWidth(300);
		data_employes.setRowHeight(20);

		data_employes.setRowHeight(20);

		//Création du scrollpane et insertion de la table dedans.
		JScrollPane monscrollPane = new JScrollPane();
		monscrollPane.getViewport().add(data_employes);
		monscrollPane.setPreferredSize( new Dimension(1100,570));

		//on retourne le scrollpane.
		return monscrollPane;
	 }



	/**
	 * Methode pour recupérer la liste des employés de la bdd et afficher la table des employes
	 * @return un jscrollbar contenant la table des employes
	 */
	public JScrollPane display_Employes()
	{	//On crée un liste vide
		List<Employe> maliste = new ArrayList<Employe>();
		try {
			maliste = Employe.findBaseList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		//création d'un tableau d'objet et tranfert des info de la liste vers le tableau Object.
		Object[][] donnees = new Object[ maliste.size()][5];
		 int i = 0;
	        while (i < maliste.size()){
	        	donnees[i][0] = maliste.get(i).getNumero();
	        	donnees[i][1] = maliste.get(i).getNom();
	        	donnees[i][2] = maliste.get(i).getPrenom();
	        	donnees[i][3] = maliste.get(i).getAdresse();
	        	donnees[i][4] = maliste.get(i).getNumeroTelephone();
	            i++;
	        }
		//test affichage console
		/*for(Employe elem : maliste)
		{
			System.out.println( 	elem.getNumero() + "|" +
					elem.getNom() + "|" +
					elem.getPrenom() + "|" +
					elem.getAdresse() + "|" +
					elem.getNumeroTelephone() + "\n");
		}*/

	    // Nom des colonnes.
		String[] colonnes = { "Numéro" , "Nom" , "Prénom" , "Adresse" , "Numéro de Téléphone" };
		// Création de la table.
		JTable data_employes =  new JTable( donnees , colonnes );

		//Gestion de la taille des cellules de la table.
		data_employes.getColumnModel().getColumn(0).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(1).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(2).setPreferredWidth(100);
		data_employes.getColumnModel().getColumn(3).setPreferredWidth(300);
		data_employes.getColumnModel().getColumn(4).setPreferredWidth(200);
		data_employes.setRowHeight(20);

		//Création du scrollpane et insertion de la table dedans.
		JScrollPane monscrollPane = new JScrollPane();
		monscrollPane.getViewport().add(data_employes);
		monscrollPane.setPreferredSize( new Dimension(1100,570));

		//on retourne le scrollpane.
		return monscrollPane;
	 }
}
