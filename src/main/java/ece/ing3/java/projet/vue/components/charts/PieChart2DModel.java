package ece.ing3.java.projet.vue.components.charts;

import ece.ing3.java.projet.database.Database;
import ece.ing3.java.projet.exceptions.DatabaseException;
import ece.ing3.java.projet.modele.employe.Docteur;
import ece.ing3.java.projet.modele.employe.Employe;
import ece.ing3.java.projet.modele.employe.Infirmier;
import ece.ing3.java.projet.modele.hopital.Chambre;
import ece.ing3.java.projet.modele.hopital.Hospitalisation;
import ece.ing3.java.projet.modele.hopital.Malade;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ece.ing3.java.projet.enums.Rotation.JOUR;
import static ece.ing3.java.projet.enums.Rotation.NUIT;
import static ece.ing3.java.projet.enums.Specialite.*;

/**
 * Classe modèle pour les diagrammes circulaires 2D
 *
 * @author Virgile
 * @author Nicolas
 * @author Louis-Félix
 */
public class PieChart2DModel implements Chart {

	protected DefaultPieDataset dataset;
	protected JFreeChart chart;
	protected ChartPanel chartpanel;
	protected int type = 0;
	protected String titre;

	/**
	 * Constructeur par défaut: sert de modèle aux autres PieChart
	 * Initialise le conteneur de données
	 *
	 * @param titre : le titre du diagramme.
	 */
	public PieChart2DModel(String titre)
	{
		dataset = new DefaultPieDataset();
		this.titre = titre;
	}

	/**
	 * Getter
	 *
	 * @return un panel contenant le diagramme 2D
	 */
	public ChartPanel getChart() {
		return chartpanel;
	}
}
