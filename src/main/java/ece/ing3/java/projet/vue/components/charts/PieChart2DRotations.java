package ece.ing3.java.projet.vue.components.charts;

import static ece.ing3.java.projet.enums.Rotation.JOUR;
import static ece.ing3.java.projet.enums.Rotation.NUIT;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.employe.Infirmier;


public class PieChart2DRotations  extends PieChart2DModel {

	public PieChart2DRotations(List<Infirmier> malisteinfirmier ) {

		super("Rotation infirmier");

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


		chart = ChartFactory.createPieChart( this.titre, dataset, true, // legende
				true, // infos
				false // URLs
		);

		chartpanel = new ChartPanel( chart );
	}
}
