package ece.ing3.java.projet.vue;
import static ece.ing3.java.projet.enums.Rotation.JOUR;
import static ece.ing3.java.projet.enums.Rotation.NUIT;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Soigne;


/**
 * Classe modèle pour les diagrammes en batons.
 * 
 * @author Nicolas
 *
 */
public class BarChart
{
	public static DefaultCategoryDataset dataset;
	public static ChartPanel chartPanel;
	public static JFreeChart chart;
	private static int type = 0;
	
	/**
	 * Constructeur par défaut -> Complete les informations du diagramme 
	 * -> Rempli et affiche le diagramme en batons dans un panel
	 */
	public BarChart( int type, List<Infirmier> malisteinfirmier , List<Soigne> malistesoigne) {
		this.type = type;
		
		if (type == 4)
		{
			int categorie1 = 0; // 1ère categorie de salaire ( inferieur à 1300 euros )
			int categorie2 = 0; //2ème categorie de salaire ( compris entre 1301 et 1500 euros )
			int categorie3 = 0; // 3ème catégorie de salaire (compris entre 1501 et 1700 euros )
			int categorie4 = 0; //4ème catégorie de salaire ( superieur à 1701 euros )
	
			for( Infirmier eleminfirmier : malisteinfirmier ) {
				if( eleminfirmier.getSalaire() <= 1300 ) {
					categorie1++;
				}
	
				if( (eleminfirmier.getSalaire() > 1300 ) && ( eleminfirmier.getSalaire() <= 1500 ) ){
					categorie2++;
				}
				
				if( (eleminfirmier.getSalaire() > 1500 ) && ( eleminfirmier.getSalaire() <= 1700 ) ){
					categorie3++;
				}
				
				if( eleminfirmier.getSalaire() > 1700 ) {
					categorie4++;
				}
			}
			
			dataset = new DefaultCategoryDataset();
			dataset.addValue( categorie1 , "", " <= 1300 euros ");
			dataset.addValue( categorie2 , "", " Entre 1300 et 1500 ");
			dataset.addValue( categorie3 , "", " Entre 1500 et 1700 ");
			dataset.addValue( categorie4 , "", " > 1700 euros ");
	
			
			 chart = ChartFactory.createBarChart(
					"Repartition des salaires mensuels chez les infirmiers", // chart title
					"Salaire", // domain axis label
					"Nombre d'employes", // range axis label
					dataset, // data
					PlotOrientation.VERTICAL, // orientation
					true, // include legend
					true, // tooltips?
					false // URLs?
					);
			
			chartPanel = new ChartPanel(chart, false);
			chartPanel.setPreferredSize(new Dimension(400, 200));
		}
		
		
	if(type == 5)
	{
		dataset = new DefaultCategoryDataset();
		List<Long> maliste = new ArrayList<Long>();
		//https://www.developpez.net/forums/d78004/java/general-java/apis/java-util/collections-supprimer-doublons-arraylist/ 
		for( Soigne elemsoigne : malistesoigne ) {
			maliste.add( elemsoigne.getNumeroDocteur());
		}
		Set monset = new HashSet();
		monset.add(maliste);
		ArrayList<Long> newListe = new ArrayList<Long>(monset);
		
		for( Soigne elemsoigne : malistesoigne ) {
			for(int i =0; i< newListe.size() ; i++ )
			{
				if( elemsoigne.getNumeroDocteur() == newListe.get(i) )
				{
					
				}
			}

		}
		
//		dataset.addValue( categorie1 , "", " <= 1300 euros ");
//		dataset.addValue( categorie2 , "", " Entre 1300 et 1500 ");
//		dataset.addValue( categorie3 , "", " Entre 1500 et 1700 ");
//		dataset.addValue( categorie4 , "", " > 1700 euros ");

		
		 chart = ChartFactory.createBarChart(
				"Numero du Docteur en fonction des malades ", // chart title
				"Docteurs n°", // domain axis label
				"Malades associés", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		
		chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(400, 200));
	}

	}
	/**
	 * Getter
	 * 
	 * @return un panel contenant le diagramme en baton
	 */
	public ChartPanel getBarChart()
	{
		return chartPanel;
	}
	
}
