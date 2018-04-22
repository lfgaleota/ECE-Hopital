package ece.ing3.java.projet.vue.components.charts;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.hopital.Hospitalisation;

/**
 * Diagramme circulaire 2D adapté pour afficher la part de patients par service
 */
public class PieChart2DPatients extends PieChart2DModel {

	public PieChart2DPatients(List<Hospitalisation> malistehospitalisation) {

		super("Patients par service");

		int CAR = 0;
		int CHG = 0;
		int REA = 0;

		int populationglobale = malistehospitalisation.size();
		//on parcourt la liste Hospitalisation pour incrémenter les compteurs
		for (Hospitalisation aMalisteHospitalisation : malistehospitalisation) {

			if ("REA".equals(aMalisteHospitalisation.getCodeService())) {
				REA++;
			}

			if ("CHG".equals(aMalisteHospitalisation.getCodeService())) {
				CHG++;
			}

			if ("CAR".equals(aMalisteHospitalisation.getCodeService())) {
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
