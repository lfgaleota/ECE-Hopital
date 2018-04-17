package ece.ing3.java.projet.vue;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.hopital.Malade;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTable;

/**
 * Classe d'affichage de la fenetre de statistiques.
 * @author Nicolas
 *
 */
public class Statistiques  extends JFrame {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe
	 * Donne les principales caractéristiques de la fenêtre
	 */
	public Statistiques()
	{
		this.setTitle ("Statistiques de l'Hôpital");
		this.setSize((int)getToolkit().getScreenSize().getWidth(), ((int)getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.
	}
	
	/**
	 * Méthode qui créée des objets de JFreeChart et les ajoute dans une fenêtre
	 */
	public void creer_Statistiques()
	{
            //On crée un liste d'employe
		List<Employe> malisteemploye = new ArrayList<Employe>();
		try {
			malisteemploye = Employe.findBaseList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
                
                
            //On crée un liste de malade
		List<Malade> malistemalade = new ArrayList<Malade>();
		try {
			malistemalade = Malade.findList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
                
                
                List<Service> malisteservice = new ArrayList<Service>();
		try {
			malisteservice = Service.findList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
                

                
            
		GridLayout mygridLayout = new GridLayout(3,2); 
		this.setLayout(mygridLayout);
		mygridLayout.setHgap(10);
		mygridLayout.setVgap(10);
		  
		
		PieChart2D P= new PieChart2D(malisteemploye);
		PieChart3D P3= new PieChart3D();
		BarChart B = new BarChart();
		
		this.getContentPane().add(P.getPieChart2D());
		this.getContentPane().add(P3.getPieChart3D());
		this.getContentPane().add(B.getBarChart());
		
		this.pack();
	}
}
