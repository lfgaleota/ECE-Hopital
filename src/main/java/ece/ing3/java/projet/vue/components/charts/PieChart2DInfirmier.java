package ece.ing3.java.projet.vue.components.charts;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.employe.Infirmier;

/**
 * Diagramme circulaire 2D adapté pour afficher le nombre d'infirmier par service
 */
public class PieChart2DInfirmier extends PieChart2DModel {

	public PieChart2DInfirmier(List<Infirmier> malisteinfirmier) {

		super("Nombre d'infirmiers par service");
		int CAR = 0;
		int CHG = 0;
		int REA = 0;

		int populationglobale = malisteinfirmier.size();
		//on parcourt la liste et incrémente les compteurs associés aux caractères voulus
		for (Infirmier aMalisteinfirmier : malisteinfirmier) {
			if ("REA".equals(aMalisteinfirmier.getCodeService())) {
				REA++;
			}

			if ("CHG".equals(aMalisteinfirmier.getCodeService())) {
				CHG++;
			}
			if ("CAR".equals(aMalisteinfirmier.getCodeService())) {
				CAR++;
			}
		}
		//on ajoute ces valeurs au diagramme
		dataset.setValue("Ranimation ", (REA * 100 / populationglobale));
		dataset.setValue("Chirurugie générale ", (CHG * 100 / populationglobale));
		dataset.setValue("Cardiologue ", (CAR * 100 / populationglobale));

		chart = ChartFactory.createPieChart(this.titre, dataset, true, // legende
				true, // infos
				false // URLs
		);

		chartpanel = new ChartPanel(chart);

	}
}
