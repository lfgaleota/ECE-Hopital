package ece.ing3.java.projet.vue;

import static ece.ing3.java.projet.enums.Specialite.Anesthesiste;
import static ece.ing3.java.projet.enums.Specialite.Cardiologue;
import static ece.ing3.java.projet.enums.Specialite.Orthopediste;
import static ece.ing3.java.projet.enums.Specialite.Pneumologue;
import static ece.ing3.java.projet.enums.Specialite.Radiologue;
import static ece.ing3.java.projet.enums.Specialite.Traumatologue;

import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.employe.Docteur;

public class PieChart2DSpecialitesDoc extends PieChart2DModel {

	public PieChart2DSpecialitesDoc(List<Docteur> malistedocteur ) {

		super("Parts des spécialités des docteurs");
		
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

		chart = ChartFactory.createPieChart( this.titre , dataset, true, // legende
				true, // infos
				false // URLs
		);

		chartpanel = new ChartPanel( chart );
	}
}