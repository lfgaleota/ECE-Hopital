/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece.ing3.java.projet.vue;

/**
 *
 * @author Virgile
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel{
    

  private Color color = Color.white;
  private static int COUNT = 0;
  private String message = "";
  private Image logo;
  private int type=0;
   
 ///CONSTRUCTEUR D UN PANNEAU (CONTENU D UN ONGLET)
/// CONSTRUCTEUR PAR DEFAUT
  public Panneau(){}
  ///CONSTRUCTEUR VIA INT
    public Panneau(int initype){
        this.type=initype;
    }
    ///CONSTRUCTEUR VIA COLOR
  public Panneau(Color color){
    this.color = color;
    this.message = "Contenu du panneau N°" + (++COUNT);
  }
  
  //LA METHODE ETANT APPELEE POUR L AFFICHAGE GRAPHIQUE A CHAUQE MODIFICATION VISUELLE DES PANNEAUX
  public void paintComponent(Graphics g){
    g.setColor(this.color);
    g.fillRect(0, 0, this.getWidth(), this.getHeight());
    g.setColor(Color.white);
    g.setFont(new Font("Arial", Font.BOLD, 15));
    g.drawString(this.message, 10, 20);
    
    ///BLOC IMAGE DU LOGO SI LE TYPE DU PANNEAU EST 1
    if(type==1){
       
               try {
                  logo = ImageIO.read(new File("logo.jpg"));
                                 
                  g.drawImage(logo,0,0,115,100, this);
			
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
        
    }

    
    

  }
}