package ece.ing3.java.projet.vue.components.charts;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.hopital.Chambre;

/**
 * Diagramme circulaire 2D adapté pour afficher le nombre de lits par chambre
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class PieChart2DNbreLits extends PieChart2DModel {

	public PieChart2DNbreLits(List<Chambre> malistechambre) {

		super("Nombre de lits par chambre");
		int populationglobale = malistechambre.size();
		//création d'un tableau compteurs pour chaque lit
		int[] nbrelits = new int[100];
		//on initialise le tableau à 0.
		for (int i = 0; i < nbrelits.length; i++) {
			nbrelits[i] = 0;
		}
		// on parcourt le tableau et incrémente le compteur en fonction du nombre de lits donnés par la BDD
		for (int i = 0; i < nbrelits.length; i++) {
			for (Chambre aMalistechambre : malistechambre) {

				if (aMalistechambre.getNombreLits() == i) {
					nbrelits[i]++;
				}

			}
			//on transmet les infos au diagramme si le nombre de lits est !=0
			if (nbrelits[i] != 0) {
				dataset.setValue(i + " lit(s) ", (nbrelits[i] * 100 / populationglobale));
			}

		}

		chart = ChartFactory.createPieChart(this.titre, dataset, true, // legende
				true, // infos
				false // URLs
		);

		chartpanel = new ChartPanel(chart);
	}
}
