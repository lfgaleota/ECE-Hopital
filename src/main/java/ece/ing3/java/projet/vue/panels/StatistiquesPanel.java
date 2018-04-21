package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.administration.Service;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.modele.hopital.Soigne;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.components.charts.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

/**
 * Classe d'affichage de la fenetre de statistiques.
 *
 * @author Nicolas
 *
 */
public class StatistiquesPanel extends JTabbedPane {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur par défaut de la classe
	 * Donne les principales caractéristiques du panneau
	 */
	public StatistiquesPanel() {
		this.creer_Statistiques();
	}

	/**
	 * Méthode qui créée des objets de JFreeChart et les ajoute dans 2 fenêtres
	 */
	public void creer_Statistiques() {

		// On crée un liste d'employe
		List<Employe> malisteemploye = new ArrayList<>();
		try {
			malisteemploye = Employe.findBaseList(); // récuperation des données de la bdd concernant les employes
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		// On crée un liste de malade
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
			malistehospitalisation = Hospitalisation.findList(); // récuperation des données de la bdd concernant les
																	// hospitalisations
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Chambre> malistechambre = new ArrayList<>();
		try {
			malistechambre = Chambre.findList(); // récuperation des données de la bdd concernant les chambres
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Docteur> malistedocteur = new ArrayList<>();
		try {
			malistedocteur = Docteur.findList(); // récuperation des données de la bdd concernant les docteurs
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		List<Infirmier> malisteinfirmier = new ArrayList<>();
		try {
			malisteinfirmier = Infirmier.findList(); // récuperation des données de la bdd concernant les infirmiers
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}
		List<Soigne> malistesoigne = new ArrayList<>();
		try {
			malistesoigne = Soigne.findList(); // récuperation des données de la bdd concernant les prises en charge (soigne)
		} catch (DatabaseException e1) {
			e1.printStackTrace();
		}

		JPanel panMalades = new JPanel();
		JPanel panEmployes = new JPanel();

		PieChart3DMutuelles A = new PieChart3DMutuelles( malistemalade );
		panMalades.add(A.getPieChart3D());

		PieChart2DNbreLits B = new PieChart2DNbreLits( malistechambre );
		panMalades.add(B.getPieChart2D());

		PieChart2DSpecialitesDoc C = new PieChart2DSpecialitesDoc (malistedocteur);
		panEmployes.add(C.getPieChart2D());

		PieChart2DRotations D = new PieChart2DRotations(malisteinfirmier);
		panEmployes.add(D.getPieChart2D());

		PieChart2DPatients E = new PieChart2DPatients(malistehospitalisation);
		panMalades.add(E.getPieChart2D());

		PieChart2DMalades F = new PieChart2DMalades();
		panMalades.add(F.getPieChart2D());

		//this.getContentPane().add((new PieChart2DModel(6, null, null, null, null, null, null)).getPieChart2D());

		PieChart2DInfirmier G = new PieChart2DInfirmier(malisteinfirmier);
		panEmployes.add(G.getPieChart2D());

		BarChartSalaires H = new BarChartSalaires( malisteinfirmier );
		panEmployes.add(H.getBarChart());

		GridLayout mygridLayout = new GridLayout(2, 2);
		mygridLayout.setHgap(10);
		mygridLayout.setVgap(10);
		panMalades.setLayout(mygridLayout);
		panEmployes.setLayout(mygridLayout);

		addTab( Strings.get( "reporting.malades" ), panMalades );
		addTab( Strings.get( "reporting.employes" ), panEmployes );
	}
}
