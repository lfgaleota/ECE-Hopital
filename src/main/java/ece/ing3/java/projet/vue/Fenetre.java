/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 */

package ece.ing3.java.projet.vue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.panels.BasePanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Virgile
 */

/**
 * Fenetre principale de l'application
 * 
 * @author Nicolas
 *
 */
public class Fenetre extends JFrame implements ActionListener, MouseListener {
	private JTabbedPane onglet;
	/// LE JSPLIT QUIVA PERMETTRE D AVOIR DEUX PANNEAUX PAR ONGLET , UN POUR LES
	/// BOUTONS DU HAUT L AUTRE POUR L AFFICHAGE
	// JSplitPane ongletandhaut = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // ou
	/// VERTICAL_SPLIT ///METHODE AVEC ONGLET/BOUTONS INDEPENDANT
	private JSplitPane split;

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET0
	private JButton boutonstat = new JButton("Statistiques");
	private JButton boutonrechercher = new JButton("Rechercher");
	private JButton boutonMA = new JButton("MAJ");
	private JButton boutonAjouter = new JButton("Ajouter");
	private JButton boutonSup = new JButton("Supprimer");

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 1
	private JButton boutonstat1 = new JButton("Statistiques");
	private JButton boutonrechercher1 = new JButton("Rechercher");
	private JButton boutonMA1 = new JButton("MAJ");
	private JButton boutonAjouter1 = new JButton("Ajouter");
	private JButton boutonSup1 = new JButton("Supprimer");

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 2
	private JButton boutonstat2 = new JButton("Statistiques");
	private JButton boutonrechercher2 = new JButton("Rechercher");
	private JButton boutonMA2 = new JButton("MAJ");
	private JButton boutonAjouter2 = new JButton("Ajouter");
	private JButton boutonSup2 = new JButton("Supprimer");

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 3
	private JButton boutonstat3 = new JButton("Statistiques");
	private JButton boutonrechercher3 = new JButton("Rechercher");
	private JButton boutonMA3 = new JButton("MAJ");
	private JButton boutonAjouter3 = new JButton("Ajouter");
	private JButton boutonSup3 = new JButton("Supprimer");

	/// LES 5 BOUTONS DE CHAQUE ONGLET ICI ONGLET 4
	private JButton boutonstat4 = new JButton("Statistiques");
	private JButton boutonrechercher4 = new JButton("Rechercher");
	private JButton boutonMA4 = new JButton("MAJ");
	private JButton boutonAjouter4 = new JButton("Ajouter");
	private JButton boutonSup4 = new JButton("Supprimer");
        
        private JButton boutonSup5 = new JButton("ALALALALLA");


	private JButton configuration = new JButton();
	private Image config_img;
	
	private static int width = 1100;
	private static int height = 900;
        
       /// LES DIFFERENTS PANNEAUX ONGLETS SONT CREES ICI , AVEC UN CONSTRUCTEUR
        /// UTILISANT UNE COULEUR EN PARAMETRE
        private BasePanel[] tPan = { new BasePanel(Color.RED), new BasePanel(Color.GREEN), new BasePanel(Color.BLUE),
				new BasePanel(Color.YELLOW), new BasePanel(Color.BLACK), new BasePanel(Color.RED), new BasePanel(Color.GRAY) };
	
	/// LA FENETRE CONTIENT LES PANNEAUX (CONTENU DES ONGLETS)
	public Fenetre() {
		//this.setLocationRelativeTo(null);
		this.setTitle( "Projet Hopital" );
		this.setSize((int)getToolkit().getScreenSize().getWidth(), ((int)getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		//this.setSize( width, height );

		/*
		 * MMETHODE ONGLET/BOUTONS INDEPENDANT /// LA LIGNE DE SEPARATION A UNE LARGEUR
		 * DE PX ET EST EN 80 ongletandhaut.setDividerSize(1);
		 * ongletandhaut.setDividerLocation(100);
		 */

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
                panboutons5.add(boutonSup5);

		/// JSplitpane pour boutons/Logo
		// JSplitPane logoandboutons = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		/// panlogo, panboutons); // ou
		// VERTICAL_SPLIT
		/// LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
		// logoandboutons.setDividerSize(1);
		// logoandboutons.setDividerLocation(112);


	

		/// LE CONTENEUR D ONGLET QUE L ON MET A GAUCHE DE LA FENETRE
		onglet = new JTabbedPane(JTabbedPane.LEFT);


		/// METHODE PRISE SUR OPENCLASSROOM
		int i = 0;
		for (BasePanel pan : tPan) {
			// Méthode d'ajout d'onglet
			/// CHAQUE VALEUR DE I CORRESPOND A UN INDICE D ONGLETS

                    
			if (i == 0) {
				// SEPARE EN DEUX LE CONTENU DE L ONGLET AVEC LE PANBOUTONS CORRESPONDANT ET LE
				// PANNEAU
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panlogo, pan);
				// L'ONGLET CONTIENT CETTTE SEPARATION
				onglet.add(pan);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_APPLOGO).getImage().getScaledInstance(this.width / 7 , this.height / 7, Image.SCALE_DEFAULT))));
					/* Constants.RESOURCE_PATH_APPLOGO */

			}
			if (i == 1) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons, pan);
				onglet.add(split);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_SERVICELOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));

			}
			if (i == 2) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons1, pan);
				onglet.add(split);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_CHAMBRELOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));
			}
			if (i == 3) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons2, pan);
				onglet.add(split);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_EMPLOYELOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));

			}
			if (i == 4) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons3, pan);
				onglet.add(split);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_PATIENTLOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));
			}
			if (i == 5) {
				split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons4, pan);
				onglet.add(split);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_HOSPITALISATIONLOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));
			}
                        
                        if (i == 6) {
				//split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons4, pan);
				onglet.add(pan);
				onglet.setIconAt(i, new ImageIcon(
						(new ImageIcon(Constants.RESOURCE_PATH_CONFIGLOGO).getImage().getScaledInstance(this.width / 7, this.height / 11, Image.SCALE_DEFAULT))));
			}


			// onglet.add("Onglet n° "+(++i), pan);

			i++;
		}
		// On passe ensuite les onglets au content pane
		// ongletandhaut.add(logoandboutons);
		// ongletandhaut.add(onglet);

		this.getContentPane().add(onglet);
		this.setVisible(true);

		boutonstat.addActionListener(this);
		boutonstat1.addActionListener(this);
		boutonstat2.addActionListener(this);
		boutonstat3.addActionListener(this);
		boutonstat4.addActionListener(this);
                
                ///LES BOUTONS RECHERCHER SONT TOUS SOUS ECOUTE
                        boutonrechercher.addActionListener(this);
                	boutonrechercher1.addActionListener(this);
                        boutonrechercher2.addActionListener(this);
                        boutonrechercher3.addActionListener(this);
                        boutonrechercher4.addActionListener(this);
                        
		boutonMA.addActionListener(this);
		boutonAjouter.addActionListener(this);
		boutonSup.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ((e.getSource() == boutonstat) || (e.getSource() == boutonstat1) || (e.getSource() == boutonstat2)
				|| (e.getSource() == boutonstat3) || (e.getSource() == boutonstat4)) {
			Statistiques mesStats = new Statistiques();
			mesStats.creer_Statistiques();
		}
/*
                ///TOUT LES BOUTONS RECHERCHER FONT LA MEME ACTION
		if ( (e.getSource() == boutonrechercher) || (e.getSource() == boutonrechercher1)  ||(e.getSource() == boutonrechercher2) 
				|| (e.getSource() == boutonrechercher3)  || (e.getSource() == boutonrechercher4) )
		{
                */

                ///ACTION DU BOUTON RECHERCHER 1 : POUR LE MOMENT AFFICHE UNIQUEMENT LA LISTE AU MILIEU DU JPANEL CORRESPONDANT
	if ( (e.getSource() == boutonrechercher) )
		{
			
			//test remplissage manuel
			 Vector<String> monvecteur = new Vector<String>();
			 JList<String> maliste = new JList<String>(monvecteur);
                         
                         monvecteur.add(new String("ONGLET SERVICE"));
			 monvecteur.add(new String("Nom"));
			 monvecteur.add(new String("Prenom"));
			 monvecteur.add(new String("06"));
			 monvecteur.add(new String("Paris"));
                         
                         /**
                         ///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche deja dans nos JPanel;
                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(maliste));
                        frame.setSize(300, 200);
                        frame.setVisible(true);
                        */
                        
                        tPan[1].add(maliste);
                        
			 
			 Map<String, String> mamap = new HashMap<>();
			 mamap.put("a", "1");
			 mamap.put("b", "2");
			 mamap.put("c", "3");
			 mamap.put("d", "4");
			 mamap.put("e", "5");
			 
                         ModelSearchDialog marecherche = new ModelSearchDialog( maliste, mamap );
                         
                         this.repaint();
                         
                       //  marecherche.

		}
        
                ///MEME ACTION QUE POUR BOUTON RECHERCHER 1 MAIS AFFICHE DANS UN AUTRE ONGLET
        	if ( (e.getSource() == boutonrechercher1) )
		{
			
			//test remplissage manuel
			 Vector<String> monvecteur = new Vector<String>();
			 JList<String> maliste = new JList<String>(monvecteur);
                         
                         monvecteur.add(new String("ONGLET CHAMBRE"));
			 monvecteur.add(new String("Nom"));
			 monvecteur.add(new String("Prenom"));
			 monvecteur.add(new String("06"));
			 monvecteur.add(new String("Paris"));
                         
                         /**
                         ///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche deja dans nos JPanel;
                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(maliste));
                        frame.setSize(300, 200);
                        frame.setVisible(true);
                        */
                        
                        tPan[2].add(maliste);
                        
			 
			 Map<String, String> mamap = new HashMap<>();
			 mamap.put("a", "1");
			 mamap.put("b", "2");
			 mamap.put("c", "3");
			 mamap.put("d", "4");
			 mamap.put("e", "5");
			 
                         ModelSearchDialog marecherche = new ModelSearchDialog( maliste, mamap );
                         
                            this.repaint();
                         
                       //  marecherche.

		}
                
                     ///MEME ACTION QUE POUR BOUTON RECHERCHER 1 MAIS AFFICHE DANS UN AUTRE ONGLET
                if ( (e.getSource() == boutonrechercher2) )
		{
			
			//test remplissage manuel
			 Vector<String> monvecteur = new Vector<String>();
			 JList<String> maliste = new JList<String>(monvecteur);
                         
                         monvecteur.add(new String("ONGLET EMPLOYES"));
			 monvecteur.add(new String("Nom"));
			 monvecteur.add(new String("Prenom"));
			 monvecteur.add(new String("06"));
			 monvecteur.add(new String("Paris"));
                         
                         /**
                         ///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche deja dans nos JPanel;
                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(maliste));
                        frame.setSize(300, 200);
                        frame.setVisible(true);
                        */
                        
                        tPan[3].add(maliste);
                        
			 
			 Map<String, String> mamap = new HashMap<>();
			 mamap.put("a", "1");
			 mamap.put("b", "2");
			 mamap.put("c", "3");
			 mamap.put("d", "4");
			 mamap.put("e", "5");
			 
                         ModelSearchDialog marecherche = new ModelSearchDialog( maliste, mamap );
                         
                            this.repaint();
                         
                       //  marecherche.

		}
                     ///MEME ACTION QUE POUR BOUTON RECHERCHER 1 MAIS AFFICHE DANS UN AUTRE ONGLET
                if ( (e.getSource() == boutonrechercher3) )
		{
			
			//test remplissage manuel
			 Vector<String> monvecteur = new Vector<String>();
			 JList<String> maliste = new JList<String>(monvecteur);
                         
                         monvecteur.add(new String("ONGLET PATIENT"));
			 monvecteur.add(new String("Nom"));
			 monvecteur.add(new String("Prenom"));
			 monvecteur.add(new String("06"));
			 monvecteur.add(new String("Paris"));
                         
                         /**
                         ///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche deja dans nos JPanel;
                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(maliste));
                        frame.setSize(300, 200);
                        frame.setVisible(true);
                        */
                        
                        tPan[4].add(maliste);
                        
			 
			 Map<String, String> mamap = new HashMap<>();
			 mamap.put("a", "1");
			 mamap.put("b", "2");
			 mamap.put("c", "3");
			 mamap.put("d", "4");
			 mamap.put("e", "5");
			 
                         ModelSearchDialog marecherche = new ModelSearchDialog( maliste, mamap );
                         
                            this.repaint();
                         
                       //  marecherche.

		}
                     ///MEME ACTION QUE POUR BOUTON RECHERCHER 1 MAIS AFFICHE DANS UN AUTRE ONGLET
                if ( (e.getSource() == boutonrechercher4) )
		{
			
			//test remplissage manuel
			 Vector<String> monvecteur = new Vector<String>();
			 JList<String> maliste = new JList<String>(monvecteur);
                         
                         monvecteur.add(new String("ONGLET HOSPITALISATION"));
			 monvecteur.add(new String("Nom"));
			 monvecteur.add(new String("Prenom"));
			 monvecteur.add(new String("06"));
			 monvecteur.add(new String("Paris"));
                         
                         /**
                         ///Fenetre d affichage du contenu de la liste (ne marche pas si on affiche deja dans nos JPanel;
                        JFrame frame = new JFrame();
                        frame.add(new JScrollPane(maliste));
                        frame.setSize(300, 200);
                        frame.setVisible(true);
                        */
                        
                        tPan[5].add(maliste);
                        
			 
			 Map<String, String> mamap = new HashMap<>();
			 mamap.put("a", "1");
			 mamap.put("b", "2");
			 mamap.put("c", "3");
			 mamap.put("d", "4");
			 mamap.put("e", "5");
			 
                         ModelSearchDialog marecherche = new ModelSearchDialog( maliste, mamap );
                         
                            this.repaint();
                         
                       //  marecherche.

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

		}

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}