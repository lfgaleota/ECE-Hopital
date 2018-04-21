package ece.ing3.java.projet.vue.components.charts;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.hopital.Chambre;

/**
 * Classe
 * @author Nicolas
 *
 */
public class PieChart2DNbreLits extends PieChart2DModel {

	public PieChart2DNbreLits(List<Chambre> malistechambre) {

		super("Nombre de lits par chambre");

		int populationglobale = malistechambre.size();
		int[] nbrelits = new int[100];
		for (int i = 0; i < nbrelits.length; i++) {
			nbrelits[i] = 0;
		}

		for (int i = 0; i < nbrelits.length; i++) {
			for (Chambre aMalistechambre : malistechambre) {

				if (aMalistechambre.getNombreLits() == i) {
					nbrelits[i]++;
				}

			}
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
