package ece.ing3.java.projet.vue;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

/**
 * Classe d'affichage de la fenetre de statistiques.
 * @author Nicolas
 *
 */
public class Statistiques  extends JFrame {
	private static final long serialVersionUID = 1L;
        private int  type=0;

	/**
	 * Constructeur par défaut de la classe
	 * Donne les principales caractéristiques de la fenêtre
	 */
	public Statistiques(int type)
	{
            this.type=type;
            if(this.type==1)
                {
                  this.setTitle ("Statistiques de l'Hôpital");  
                }
            if(this.type==2)
                {
                  this.setTitle ("Statistiques de l'Hôpital (suite)");  
                }
            
		
		this.setSize((int)getToolkit().getScreenSize().getWidth(), ((int)getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.
		this.creer_Statistiques();
                
                
	}

	/**
	 * Méthode qui créée des objets de JFreeChart et les ajoute dans une fenêtre
	 */
	public void creer_Statistiques()
	{
            //On crée un liste d'employe
		List<Employe> malisteemploye = new ArrayList<>();
		try {
			malisteemploye = Employe.findBaseList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}


            //On crée un liste de malade
		List<Malade> malistemalade = new ArrayList<>();
		try {
			malistemalade = Malade.findList(); // récuperation des données de la bdd concernant les malade
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}


                List<Service> malisteservice = new ArrayList<>();
		try {
			malisteservice = Service.findList(); // récuperation des données de la bdd concernant les service
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

                List<Hospitalisation> malistehospitalisation = new ArrayList<>();
		try {
			malistehospitalisation = Hospitalisation.findList(); // récuperation des données de la bdd concernant les hospitalisations
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}


                List<Chambre> malistechambre = new ArrayList<>();
		try {
			malistechambre = Chambre.findList(); // récuperation des données de la bdd concernant les chambre
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

                List<Docteur> malistedocteur = new ArrayList<>();
		try {
			malistedocteur = Docteur.findList(); // récuperation des données de la bdd concernant les chambre
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

                List<Infirmier> malisteinfirmier = new ArrayList<>();
		try {
			malisteinfirmier = Infirmier.findList(); // récuperation des données de la bdd concernant les chambre
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

                if(this.type==1)
                {
                GridLayout mygridLayout = new GridLayout(3,2);
		this.setLayout(mygridLayout);
		mygridLayout.setHgap(10);
		mygridLayout.setVgap(10);

                PieChart3D P3= new PieChart3D(malistemalade);
                this.getContentPane().add(P3.getPieChart3D());
                /* MEME QUE CELUI EN 3D MAIS EN 2D
		PieChart2D P= new PieChart2D(1,malisteemploye,malistemalade,malistechambre,malistedocteur,malisteinfirmier,malistehospitalisation);
                this.getContentPane().add(P.getPieChart2D());
                */
                PieChart2D D= new PieChart2D(2,malisteemploye,malistemalade,malistechambre,malistedocteur,malisteinfirmier,malistehospitalisation);
                this.getContentPane().add(D.getPieChart2D());
                PieChart2D F= new PieChart2D(3,malisteemploye,malistemalade,malistechambre,malistedocteur,malisteinfirmier,malistehospitalisation);
                this.getContentPane().add(F.getPieChart2D());
                PieChart2D G= new PieChart2D(4,malisteemploye,malistemalade,malistechambre,malistedocteur,malisteinfirmier,malistehospitalisation);
                this.getContentPane().add(G.getPieChart2D());
                PieChart2D H = new PieChart2D(5,malisteemploye,malistemalade,malistechambre,malistedocteur,malisteinfirmier,malistehospitalisation);
                this.getContentPane().add(H.getPieChart2D());
                this.getContentPane().add( ( new PieChart2D( 6, null, null, null, null, null, null ) ).getPieChart2D() );
                //PieChart2D D= new PieChart2D(malisteemploye,malistemalade);
                
		//BarChart B = new BarChart();

		//this.getContentPane().add(B.getBarChart());
                //this.getContentPane().add(D.getPieChart2D());

		this.pack();   
                }
                
                if(this.type==2)
                {
                    
                ///CHANGER LE GRID LAYOUT SELON LE NOMBRE DE DIAGRAMME A AFFICHER
                GridLayout mygridLayout = new GridLayout(3,2);
		this.setLayout(mygridLayout);
		mygridLayout.setHgap(10);
		mygridLayout.setVgap(10); 
                
                ///FAIRE EXACTEMENT COMME DANS TYPE=1 POUR RAJOUTER LES DIGRAMMES
                
   
                
		this.pack();   
                
                }
	
	}
}
