package ece.ing3.java.projet.vue.panels;

import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.utils.Utils;
import ece.ing3.java.projet.vue.components.charts.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'affichage de la fenetre de statistiques.
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class StatistiquesPanel extends JTabbedPane implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel panMalades;
	private JPanel panEmployes;
	private LoadingPanel panChargement;
	private JButton boutonRafraichir;
	private PieChart3DMutuelles chart3DMutuelles;
	private PieChart2DNbreLits chart2DNbreLits;
	private PieChart2DSpecialitesDoc chart2DSpecialitesDoc;
	private PieChart2DRotations chart2DRotations;
	private PieChart2DPatients chart2DPatients;
	private PieChart2DMalades chart2DMalades;
	private PieChart2DInfirmier chart2DInfirmier;
	private BarChartSalaires chartSalaires;

	/**
	 * Constructeur par défaut de la classe
	 * Donne les principales caractéristiques du panneau
	 */
	public StatistiquesPanel() {
		panChargement = new LoadingPanel();
		try {
			boutonRafraichir = new JButton( new ImageIcon( Utils.getImageResource( Constants.RESOURCE_PATH_ICON_REFRESH ) ) );
			boutonRafraichir.setBorder( BorderFactory.createEmptyBorder() );
			boutonRafraichir.addActionListener( this );
		} catch( IOException e ) {
			throw new RuntimeException( "Fichier non trouvé.\n" + e.getLocalizedMessage() );
		}

		mettreAJour_statistiques();
	}

	private void creerPanneaux() {
		GridLayout mygridLayout = new GridLayout( 2, 2 );
		mygridLayout.setHgap( 10 );
		mygridLayout.setVgap( 10 );
		panMalades = new JPanel( mygridLayout );
		mygridLayout = new GridLayout( 2, 2 );
		mygridLayout.setHgap( 10 );
		mygridLayout.setVgap( 10 );
		panEmployes = new JPanel( mygridLayout );
		addTab( Strings.get( "reporting.malades" ), panMalades );
		addTab( Strings.get( "reporting.employes" ), panEmployes );
		addTab( "", null );
		setTabComponentAt( 2, boutonRafraichir );
		setEnabledAt( 2, false );
	}

	/**
	 * Met à jour les statistiques du panneau
	 */
	public void mettreAJour_statistiques() {
		while( getTabCount() > 0 ) {
			removeTabAt( 0 );
		}
		addTab( "...", panChargement );
		panChargement.start();
		( new SwingWorker<List<Chart>, Object>() {
			@Override
			protected List<Chart> doInBackground() throws Exception {
				// On crée un liste de malade
				List<Malade> malistemalade = new ArrayList<>();
				try {
					malistemalade = Malade.findList(); // récuperation des données de la bdd concernant les malade
				} catch( DatabaseException e1 ) {
					e1.printStackTrace();
				}

				List<Hospitalisation> malistehospitalisation = new ArrayList<>();
				try {
					malistehospitalisation = Hospitalisation.findList(); // récuperation des données de la bdd concernant les
					// hospitalisations
				} catch( DatabaseException e1 ) {
					e1.printStackTrace();
				}

				List<Chambre> malistechambre = new ArrayList<>();
				try {
					malistechambre = Chambre.findList(); // récuperation des données de la bdd concernant les chambres
				} catch( DatabaseException e1 ) {
					e1.printStackTrace();
				}

				List<Docteur> malistedocteur = new ArrayList<>();
				try {
					malistedocteur = Docteur.findList(); // récuperation des données de la bdd concernant les docteurs
				} catch( DatabaseException e1 ) {
					e1.printStackTrace();
				}

				List<Infirmier> malisteinfirmier = new ArrayList<>();
				try {
					malisteinfirmier = Infirmier.findList(); // récuperation des données de la bdd concernant les infirmiers
				} catch( DatabaseException e1 ) {
					e1.printStackTrace();
				}

				chart3DMutuelles = new PieChart3DMutuelles( malistemalade );

				chart2DNbreLits = new PieChart2DNbreLits( malistechambre );

				chart2DSpecialitesDoc = new PieChart2DSpecialitesDoc( malistedocteur );

				chart2DRotations = new PieChart2DRotations( malisteinfirmier );

				chart2DPatients = new PieChart2DPatients( malistehospitalisation );

				chart2DMalades = new PieChart2DMalades();

				chart2DInfirmier = new PieChart2DInfirmier( malisteinfirmier );

				chartSalaires = new BarChartSalaires( malisteinfirmier );

				return null;
			}

			@Override
			protected void done() {
				panChargement.stop();
				removeTabAt( 0 );
				creerPanneaux();

				panEmployes.add( chart2DSpecialitesDoc.getChart() );
				panEmployes.add( chart2DRotations.getChart() );
				panEmployes.add( chart2DInfirmier.getChart() );
				panEmployes.add( chartSalaires.getChart() );
				panMalades.add( chart3DMutuelles.getChart() );
				panMalades.add( chart2DNbreLits.getChart() );
				panMalades.add( chart2DPatients.getChart() );
				panMalades.add( chart2DMalades.getChart() );

				revalidate();
				repaint();
			}
		} ).execute();
	}

	/**
	 * Méthode de retour appelée lors d'une action, ici lorsque l'utilisateur intéragit avec le bouton de rafraichiseement.
	 *
	 * @param actionEvent Evénement d'action
	 */
	@Override
	public void actionPerformed( ActionEvent actionEvent ) {
		if( actionEvent.getSource() == boutonRafraichir ) {
			mettreAJour_statistiques();
		}
	}
}
