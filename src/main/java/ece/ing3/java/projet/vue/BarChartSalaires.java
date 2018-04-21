package ece.ing3.java.projet.vue;

import java.awt.Dimension;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import ece.ing3.java.projet.modele.employe.Infirmier;
/**
 * Classe fille de la classe BarChartModel 
 * Permet l'affichage du diagramme en batons des salaires des infirmiers.
 * 
 * @author Nicolas
 *
 */
public class BarChartSalaires extends BarChartModel {
	
	public BarChartSalaires( List<Infirmier> malisteinfirmier ) {
		super("Repartition des salaires mensuels chez les infirmiers", "Salaire" , "Nombre d'infirmiers");
		
		int categorie1 = 0; // 1ère categorie de salaire ( inferieur à 1300 euros )
		int categorie2 = 0; //2ème categorie de salaire ( compris entre 1301 et 1500 euros )
		int categorie3 = 0; // 3ème catégorie de salaire (compris entre 1501 et 1700 euros )
		int categorie4 = 0; //4ème catégorie de salaire ( superieur à 1701 euros )

		//on parcourt la liste d'infirmier et incrémente des compteurs.
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
		//On remplit avec nos données le dataset du diagramme
		dataset = new DefaultCategoryDataset();
		dataset.addValue( categorie1 , "", " <= 1300 euros ");
		dataset.addValue( categorie2 , "", " Entre 1300 et 1500 ");
		dataset.addValue( categorie3 , "", " Entre 1500 et 1700 ");
		dataset.addValue( categorie4 , "", " > 1700 euros ");

		//Création du diagramme en baton
		 chart = ChartFactory.createBarChart(
				this.titre, // chart title
				this.axe_x, // domain axis label
				this.axe_y, // range axis label
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
