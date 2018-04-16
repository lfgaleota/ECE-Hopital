/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ece.ing3.java.projet.vue.panels;

/**
 *
 * @author Virgile
 */
import static ece.ing3.java.projet.utils.Constants.RESOURCE_PATH_HOMELOGO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BasePanel extends JPanel {

	private Color color = Color.white;
	private static int COUNT = 0;
	private String message = "";
        private int type;


	/// CONSTRUCTEUR D UN PANNEAU (CONTENU D UN ONGLET)
	/// CONSTRUCTEUR PAR DEFAUT
	public BasePanel() {
	}

        ///CONSTRUCTEUR PANNEAU ACCUEIL (type=1)
        public BasePanel(int type) {
            this();
            this.color = Color.white;
            this.message ="ACCUEIL";
            this.type=type;
            
	}
	/// CONSTRUCTEUR VIA COLOR
	public BasePanel(Color color) {
		this();
		this.color = color;
		this.message = "Contenu du panneau N°" + (++COUNT);
	}

	// LA METHODE ETANT APPELEE POUR L AFFICHAGE GRAPHIQUE A CHAUQE MODIFICATION
	// VISUELLE DES PANNEAUX
	public void paintComponent(Graphics g) {
		g.setColor(this.color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.drawString(this.message, 10, 20);
                
                if(this.type==1)
                {
                        try {
                        Image img = ImageIO.read(new File(RESOURCE_PATH_HOMELOGO));
                         g.drawImage(img, (this.getWidth()/2)-img.getWidth(this)/2, 0, this);
                        //Pour une image de fond
                        //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                        } catch (IOException e) {
                         e.printStackTrace();
                        }  
                }
                
	}
}