package ece.ing3.java.projet.vue;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ece.ing3.java.projet.enums.Rotation.JOUR;
import static ece.ing3.java.projet.enums.Rotation.NUIT;
import static ece.ing3.java.projet.enums.Specialite.*;

/**
 * Classe modèle pour les diagrammes circulaires 2D
 *
 * @author Nicolas
 */
public class PieChart2D {

	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static ChartPanel chartpanel;
	//VALEUR SEVANT AFFICHER LE PIECHART VOULU , IMPOSSIBLDE DE REFAIRE DES CONTRUCTEUR SPECIFIQUE
	private static int type = 0;

	/**
	 * Constructeur par défaut -> Complete les informations du diagramme
	 * -> Rempli et affiche le diagramme circulaire dans un panel
	 */
	public PieChart2D( int type, List<Employe> malisteemploye, List<Malade> malistemalade, List<Chambre> malistechambre, List<Docteur> malistedocteur, List<Infirmier> malisteinfirmier, List<Hospitalisation> malistehospitalisation ) {
		dataset = new DefaultPieDataset();
		this.type = type;
                /*

                ///A SAVOIR QUE CE BLOC NE SERT UNIQUEMENT POUR DES TEST
                ///VALEUR DU NOMBRE DE PERSONNE DELA POPLUATION

                int populationglobale = malisteemploye.size();


                int populationvoulue=0;


                System.out.println(malisteemploye.size());

                for(int i=0;i<malisteemploye.size();i++)
                {
                        System.out.println(malisteemploye.get(i).getNom());

                        if(malisteemploye.get(i).getNom().charAt(0)=='D')
                        {
                            populationvoulue++;
                        }

                }


                System.out.println(" "+ populationvoulue);




		dataset.setValue("Category 1",(populationvoulue*100/populationglobale));
		dataset.setValue("Category 2", (1-populationvoulue/populationglobale)*100);
		//dataset.setValue("Category 3", 79.5);


                System.out.println("PUTAIN "+malisteemploye.get(0).getAdresse()+" "+ malisteemploye.size());

                */

		///BLOC DE MUTUELLE MALADE
		if( type == 1 ) {
			int populationglobale = malistemalade.size();

			Map<String, Integer> mutuelles = new HashMap<>();

			// On parcours l'ensemble des mutuelles
			for( Malade aMalistemalade : malistemalade ) {
				String mutuelle = aMalistemalade.getMutuelle();
				// Si c'est une mutuelle qu'on a pas vu, on l'ajoute à notre map de mutuelles et on l'initialise
				if( !mutuelles.containsKey( mutuelle ) ) {
					mutuelles.put( mutuelle, 0 );
				}
				// On incrémente de 1 l'entrée de cette mutuelle
				mutuelles.put( mutuelle, mutuelles.get( mutuelle ) + 1 );
			}

			// On parcours l'ensemble des mutuelles créées
			for( Map.Entry<String, Integer> mutuelle : mutuelles.entrySet() ) {
				// Pour chaque mutuelle, on l'ajoute au graphique
				dataset.setValue( mutuelle.getKey(), mutuelle.getValue() * 100 / populationglobale );
			}

			chart = ChartFactory.createPieChart( "Mutuelle des malades", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}

		if( type == 2 ) {
			int populationglobale = malistechambre.size();

			int lit1 = 0;
			int lit2 = 0;
			int lit3 = 0;
			int lit4 = 0;


			for( Chambre aMalistechambre : malistechambre ) {


				if( aMalistechambre.getNombreLits() == 1 ) {
					lit1++;
				}
				if( aMalistechambre.getNombreLits() == 2 ) {
					lit2++;
				}
				if( aMalistechambre.getNombreLits() == 3 ) {
					lit3++;
				}
				if( aMalistechambre.getNombreLits() == 4 ) {
					lit4++;
				}


			}


			dataset.setValue( "Un lit ", ( lit1 * 100 / populationglobale ) );
			dataset.setValue( "Deux lit ", ( lit2 * 100 / populationglobale ) );
			dataset.setValue( "Trois lit ", ( lit3 * 100 / populationglobale ) );
			dataset.setValue( "Quatre lit ", ( lit4 * 100 / populationglobale ) );


			chart = ChartFactory.createPieChart( "Nombre de lit par chambre", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}

		if( type == 3 ) {
			int radio = 0;
			int cardio = 0;
			int pneumo = 0;
			int anes = 0;
			int ortho = 0;
			int traumato = 0;

			int populationglobale = malistedocteur.size();

			for( Docteur aMalistedocteur : malistedocteur ) {
				if( aMalistedocteur.getSpecialite() == Radiologue ) {
					radio++;
				}
				if( aMalistedocteur.getSpecialite() == Cardiologue ) {
					cardio++;
				}
				if( aMalistedocteur.getSpecialite() == Pneumologue ) {
					pneumo++;
				}
				if( aMalistedocteur.getSpecialite() == Anesthesiste ) {
					anes++;
				}
				if( aMalistedocteur.getSpecialite() == Orthopediste ) {
					ortho++;
				}
				if( aMalistedocteur.getSpecialite() == Traumatologue ) {
					traumato++;
				}
			}

			dataset.setValue( "Radiologue ", ( radio * 100 / populationglobale ) );
			dataset.setValue( "Cardiologue ", ( cardio * 100 / populationglobale ) );
			dataset.setValue( "Pneumologue ", ( pneumo * 100 / populationglobale ) );
			dataset.setValue( "Anestesiste ", ( anes * 100 / populationglobale ) );
			dataset.setValue( "Orthopediste", ( ortho * 100 / populationglobale ) );
			dataset.setValue( "Traumatologiste ", ( traumato * 100 / populationglobale ) );

			chart = ChartFactory.createPieChart( "Type de docteur spécialiste", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}

		if( type == 4 ) {

			int jour = 0;
			int nuit = 0;

			int populationglobale = malisteinfirmier.size();

			for( Infirmier aMalisteinfirmier : malisteinfirmier ) {
				if( aMalisteinfirmier.getRotation() == JOUR ) {
					jour++;
				}

				if( aMalisteinfirmier.getRotation() == NUIT ) {
					nuit++;
				}
			}
			dataset.setValue( "Jour ", ( jour * 100 / populationglobale ) );
			dataset.setValue( "Nuit ", ( nuit * 100 / populationglobale ) );


			chart = ChartFactory.createPieChart( "Rotation infirmier", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}

		if( type == 5 ) {
			int CAR = 0;
			int CHG = 0;
			int REA = 0;


			int populationglobale = malistehospitalisation.size();

			for( Hospitalisation aMalisteHospitalisation : malistehospitalisation ) {

				if( "REA".equals( aMalisteHospitalisation.getCodeService() ) ) {
					REA++;
				}

				if( "CHG".equals( aMalisteHospitalisation.getCodeService() ) ) {
					CHG++;
				}

				if( "CAR".equals( aMalisteHospitalisation.getCodeService() ) ) {
					CAR++;
				}


			}

			dataset.setValue( "Ranimation ", ( REA * 100 / populationglobale ) );
			dataset.setValue( "Chirurugie générale ", ( CHG * 100 / populationglobale ) );
			dataset.setValue( "Cardiologue ", ( CAR * 100 / populationglobale ) );


			chart = ChartFactory.createPieChart( "Patient par service", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}
		///BLOC JOINTURE SOIGNE DOCTEUR MALADE
		if( type == 6 ) {
			// On récupère le nombre de malade par spécialité auprès de la BDD
			try {
				ResultSet rs = Database.execute( "SELECT docteur.specialite, COUNT( malade.numero ) FROM soigne JOIN docteur ON soigne.no_docteur = docteur.numero JOIN malade ON soigne.no_malade = malade.numero GROUP BY docteur.specialite;" );
				while( rs.next() ) {
					// On ajoute chaque association Spécialité <-> Nombre de malade au graphe
					dataset.setValue( rs.getString( 1 ), rs.getInt( 2 ) );
				}
				rs.close();
			} catch( DatabaseException | SQLException e ) {
				e.printStackTrace();
			}

			chart = ChartFactory.createPieChart( "Malade par spécialité", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
		}
                
                if( type == 7) {
                    
                  	int CAR = 0;
			int CHG = 0;
			int REA = 0;

			int populationglobale = malisteinfirmier.size();

			for( Infirmier aMalisteinfirmier : malisteinfirmier ) {
				if( "REA".equals(aMalisteinfirmier.getCodeService()) ) {
					REA++;
				}

				if( "CHG".equals(aMalisteinfirmier.getCodeService())) {
					CHG++;
				}
                                if( "CAR".equals(aMalisteinfirmier.getCodeService())) {
					CAR++;
				}
			}
                        
                        dataset.setValue( "Ranimation ", ( REA * 100 / populationglobale ) );
			dataset.setValue( "Chirurugie générale ", ( CHG * 100 / populationglobale ) );
			dataset.setValue( "Cardiologue ", ( CAR * 100 / populationglobale ) );


			chart = ChartFactory.createPieChart( "Infirmier par service", dataset, true, // legend?
					true, // tooltips?
					false // URLs?
			);

			chartpanel = new ChartPanel( chart );
                    
                }
	}

	/**
	 * Getter
	 *
	 * @return un panel contenant le diagramme 2D
	 */
	public ChartPanel getPieChart2D() {
		return chartpanel;
	}
}
