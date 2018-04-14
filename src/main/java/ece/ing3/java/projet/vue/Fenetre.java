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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import ece.ing3.java.projet.vue.panels.BasePanel;
import ece.ing3.java.projet.vue.panels.LogoPanel;

/**
 *
 * @author Virgile
 */

/**
 * Fenetre principale de l'application
 * @author Nicolas
 *
 */
public class Fenetre extends JFrame implements ActionListener, MouseListener {
	private JTabbedPane onglet;
	/// LE JSPLIT QUIVA PERMETTRE D AVOIR DEUX PANNEAUX PAR ONGLET , UN POUR LES
	/// BOUTONS DU HAUT L AUTRE POUR L AFFICHAGE
	//JSplitPane ongletandhaut = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // ou VERTICAL_SPLIT ///METHODE AVEC ONGLET/BOUTONS INDEPENDANT
        private JSplitPane split ;

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
    
	

	/// LA FENETRE CONTIENT LES PANNEAUX (CONTENU DES ONGLETS)
	public Fenetre() {
		this.setLocationRelativeTo(null);
		this.setTitle("Projet Hopital");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1100, 900);
                
                /* MMETHODE ONGLET/BOUTONS INDEPENDANT
		/// LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
		ongletandhaut.setDividerSize(1);
		ongletandhaut.setDividerLocation(100);
                */
                
            
                ///CHAQUE PANEL DE BOUTONS CONTIENT LES BOUTONS ( TOUS DIFFERENTS
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

		//BasePanel panlogo = new LogoPanel();

		/// JSplitpane pour boutons/Logo
		//JSplitPane logoandboutons = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panlogo, panboutons); // ou
																										// VERTICAL_SPLIT
		/// LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
		//logoandboutons.setDividerSize(1);
		//logoandboutons.setDividerLocation(112);

		/// LES DIFFERENTS PANNEAUX ONGLETS SONT CREER ICI , AVEC UN CONSTRUCTEUR
		/// UTILISANT UNE COULEUR EN PARAMETRE
		BasePanel[] tPan = { new BasePanel(Color.RED), new BasePanel(Color.GREEN), new BasePanel(Color.BLUE),
				new BasePanel(Color.YELLOW), new BasePanel(Color.BLACK), new BasePanel(Color.RED) };

		/// LE CONTENEUR D ONGLET QUE L ON MET A GAUCHE DE LA FENETRE
		onglet = new JTabbedPane(JTabbedPane.LEFT);

		/// METHODE PRISE SUR OPENCLASSROOM
		int i = 0;
		for (BasePanel pan : tPan) {
			// Méthode d'ajout d'onglet
			/// CHAQUE VALEUR DE I CORRESPOND A UN INDICE D ONGLET
			
			if (i == 0) {
                                
                                //SEPARE EN DEUX LE CONTENU DE L ONGLET AVEC LE PANBOUTONS CORRESPONDANT ET LE PANNEAU 
                                split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons, pan);
                                //L'ONGLET CONTIENT CETTTE SEPARATION
				onglet.add("Services", split);
				/*
				 * POUR AVOIR UNE IMAGE DANS L ONGLET
				 * onglet.add (pan); onglet.setIconAt((i), new ImageIcon("logo.jpg"));
				 * 
				 * 
				 * //contenu.add(tPan[i]);
				 */

			}
			if (i == 1) {
                                split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons1, pan);
				onglet.add("Chambres", split);

			}
			if (i == 2) {
                                split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons2, pan);
				onglet.add("Employés", split);

			}
			if (i == 3) {
                                split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons3, pan);
				onglet.add("Patients", split);
			}
			if (i == 4) {
                                split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons4, pan);
				onglet.add("Hospitalisations",split);

			}

			// onglet.add("Onglet n° "+(++i), pan);

			i++;
		}
		// On passe ensuite les onglets au content pane
		//ongletandhaut.add(logoandboutons);
		//ongletandhaut.add(onglet);

		this.getContentPane().add(onglet);
		this.setVisible(true);
		
		boutonstat.addActionListener(this);
		boutonrechercher.addActionListener(this);
		boutonMA.addActionListener(this);
		boutonAjouter.addActionListener(this);
		boutonSup.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		  if(e.getSource() == boutonstat)
	        {
	        	Statistiques mesStats = new Statistiques();
	        	mesStats.creer_Statistiques();
	        }
	        
	        if(e.getSource() == boutonrechercher)
	        {
	        	Vector<String> monvecteur =new Vector<String>();
	        	monvecteur.add(new String("23")); /// Numéro
	        	monvecteur.add(new String("Nom"));  /// Nom
	        	monvecteur.add(new String("Prenom")); ///Prenom
	        	monvecteur.add(new String("06")); ///Tel
	        	monvecteur.add(new String("Paris")); ///Adresse
	            JList<String> maliste = new JList<String>(monvecteur);
	        	//ModelSearchDialog marecherche = new ModelSearchDialog();
	        	
	        	
	        }
	        
	        if(e.getSource() == boutonMA)
	        {
	        	System.out.print("Panel à modifier avec une classe modifier");
	        	//ModelSearchDialog marecherche = new ModelSearchDialog( null );
	        }
	        
	        if(e.getSource() == boutonAjouter)
	        {
	        	System.out.print("Panel à modifier avec une classe ajouter");
	        	//ModelSearchDialog marecherche = new ModelSearchDialog();
	        }
	         
	        
	         
	        
	        if(e.getSource() == boutonSup)
	        {
	        	JOptionPane.showMessageDialog(null, /// composant parent
	        	"Demande de suppression d'un élement de la base de données", /// message
	        		"Supprimer un élement de la BDD", /// titre
	        		 JOptionPane.INFORMATION_MESSAGE ///message"
	        		 );
	        
	        }
	        

	}

}