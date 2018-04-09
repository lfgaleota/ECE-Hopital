/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.


 */

package ece.ing3.java.projet.vue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author Virgile
 */


public class Fenetre extends JFrame {
  private JTabbedPane onglet;
  ///LE JSPLIT QUIVA PERMETTRE D AVOIR DEUX PANNEAUX PAR ONGLET , UN POUR LES BOUTONS DU HAUT L AUTRE POUR L AFFICHAGE
 JSplitPane ongletandhaut = new JSplitPane(JSplitPane.VERTICAL_SPLIT); // ou VERTICAL_SPLIT
 

 
 ///LES 5 BOUTONS DU HAUT
 private JButton boutonstat = new JButton("Statistiques");
  private JButton boutonrechercher = new JButton("Rechercher");
   private JButton boutonMA = new JButton("MAJ");
    private JButton boutonAjouter = new JButton("Ajouter");
     private JButton boutonSup = new JButton("Supprimer");
 
  ///LA FENETRE CONTIENT LES PANNEAUX (CONTENU DES ONGLETS)
  public Fenetre(){
    this.setLocationRelativeTo(null);
    this.setTitle("Projet Hopital");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1100, 900);
    ///LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
    ongletandhaut.setDividerSize(1);
    ongletandhaut.setDividerLocation(100);
      
    Panneau panboutons = new Panneau();
    panboutons.setBackground(Color.blue);
    panboutons.add(boutonstat);
    panboutons.add(boutonrechercher);
    panboutons.add(boutonMA);
    panboutons.add(boutonAjouter);
    panboutons.add(boutonSup);
    
    Panneau panlogo = new Panneau(1);
    
     ///JSplitpane pour boutons/Logo
    JSplitPane logoandboutons = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT ,panlogo,panboutons); // ou VERTICAL_SPLIT
    ///LA LIGNE DE SEPARATION A UNE LARGEUR DE PX ET EST EN 80
    logoandboutons.setDividerSize(1);
    logoandboutons.setDividerLocation(112);

  
    ///LES DIFFERENTS PANNEAUX ONGLETS SONT CREER ICI , AVEC UN CONSTRUCTEUR UTILISANT UNE COULEUR EN PARAMETRE
    Panneau[] tPan = {   new Panneau(Color.RED), new Panneau(Color.GREEN), new Panneau(Color.BLUE),new Panneau(Color.YELLOW),new Panneau(Color.BLACK),new Panneau(Color.RED)};
      
    ///LE CONTENEUR D ONGLET QUE L ON MET A GAUCHE DE LA FENETRE
    onglet = new JTabbedPane(JTabbedPane.LEFT);
    
    ///METHODE PRISE SUR OPENCLASSROOM
    int i = 0;
    for(Panneau pan : tPan){
      //Méthode d'ajout d'onglet
      ///CHAQUE VALEUR DE I CORRESPOND A UN INDICE D ONGLET
      /// L ONGLET 0 CORRESPOND A L ACCEUIL QUAND ON CLIQUE SUR LE LOGO
      if(i==0)
      {
          
           onglet.add("Services", pan);
          /*

               onglet.add (pan);
               onglet.setIconAt((i), new ImageIcon("logo.jpg"));
                   
              
               //contenu.add(tPan[i]);
              */
               
      }
      if(i==1)
      {
           //JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT, panboutons1, pan);
               onglet.add("Chambres", pan);
          
      }
      if(i==2)
      {
                 onglet.add("Employés", pan);
            
            
      }
      if(i==3)
      {
     onglet.add("Patients", pan);
      }
      if(i==4)
      {
          onglet.add("Hospitalisations", pan);
             
      }

     // onglet.add("Onglet n° "+(++i), pan);


i++;
    }
    //On passe ensuite les onglets au content pane
    ongletandhaut.add(logoandboutons);
    ongletandhaut.add(onglet);

    this.getContentPane().add(ongletandhaut);
    this.setVisible(true);
  }


}