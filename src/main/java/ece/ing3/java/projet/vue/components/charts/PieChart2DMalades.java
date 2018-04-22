package ece.ing3.java.projet.vue.components.charts;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;

/**
 *  Diagramme circulaire 2D adapté pour afficher le nombre de malade par specialité
 *  Utilisation d'une jointure entre les classes Malade, Docteur et Soigne
 * @author Nicolas
 * @author Virgile
 */
public class PieChart2DMalades extends PieChart2DModel {

	public PieChart2DMalades() {

		super("Malades par spécialité");

		/// BLOC JOINTURE SOIGNE DOCTEUR MALADE

		// On récupère le nombre de malade par spécialité auprès de la BDD
		try {
			ResultSet rs = Database.execute(
					"SELECT docteur.specialite, COUNT( malade.numero ) FROM soigne JOIN docteur ON soigne.no_docteur = docteur.numero JOIN malade ON soigne.no_malade = malade.numero GROUP BY docteur.specialite;");
			while (rs.next()) {
				// On ajoute chaque association Spécialité <-> Nombre de malade au graphe
				dataset.setValue(rs.getString(1), rs.getInt(2));
			}
			rs.close();
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}

		chart = ChartFactory.createPieChart(this.titre, dataset, true, // legende
				true, // infos
				false // URLs
		);

		chartpanel = new ChartPanel(chart);

	}
}
