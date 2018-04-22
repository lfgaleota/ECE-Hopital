package ece.ing3.java.projet.vue.components.charts;
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
 */
public class BarChartModel implements Chart
{
	protected DefaultCategoryDataset dataset;
	protected ChartPanel chartPanel;
	protected JFreeChart chart;
	protected String titre;
	protected String axe_x;
	protected String axe_y;

	/**
	 * Constructeur -> Complete les informations de base d'un diagramme en baton
	 *
	 */
	public BarChartModel(String titre, String titrex, String titrey)
	{
		dataset = new DefaultCategoryDataset();
		this.titre = titre;
		this.axe_x = titrex;
		this.axe_y = titrey;

	}


	/**
	 * Getter
	 *
	 * @return un panel contenant le diagramme en baton
	 */
	public ChartPanel getChart()
	{
		return chartPanel;
	}

}
