package ece.ing3.java.projet.vue;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Malade;

public class PieChart2DInfirmier extends PieChart2DModel {

	public PieChart2DInfirmier(List<Infirmier> malisteinfirmier) {

		super("Nombre d'infirmiers par service");
		int CAR = 0;
		int CHG = 0;
		int REA = 0;

		int populationglobale = malisteinfirmier.size();

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
