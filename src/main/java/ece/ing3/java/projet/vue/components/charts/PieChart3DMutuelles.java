package ece.ing3.java.projet.vue.components.charts;

import ece.ing3.java.projet.modele.hopital.Malade;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Classe modèle pour les diagrammes circulaires 3D
 *
 * @author Nicolas
 *
 */
public class PieChart3DMutuelles extends JPanel implements Chart {

	private static final long serialVersionUID = 5686202670543030890L;
	private DefaultPieDataset dataset;
	private JFreeChart chart;
	private ChartPanel chartpanel;

	/**
	 * Constructeur -> Complete les informations du diagramme -> Rempli
	 * et affiche le diagramme circulaire dans un panel
	 */
	public PieChart3DMutuelles(List<Malade> malistemalade) {

		dataset = new DefaultPieDataset();
		int populationglobale = malistemalade.size();

		Map<String, Integer> mutuelles = new HashMap<>();

		// On parcours l'ensemble des mutuelles
		for (Malade aMalistemalade : malistemalade) {
			String mutuelle = aMalistemalade.getMutuelle();
			// Si c'est une mutuelle qu'on a pas vu, on l'ajoute à notre map de mutuelles et
			// on l'initialise
			if (!mutuelles.containsKey(mutuelle)) {
				mutuelles.put(mutuelle, 0);
			}
			// On incrémente de 1 l'entrée de cette mutuelle
			mutuelles.put(mutuelle, mutuelles.get(mutuelle) + 1);
		}

		// On parcours l'ensemble des mutuelles créées
		for (Map.Entry<String, Integer> mutuelle : mutuelles.entrySet()) {
			// Pour chaque mutuelle, on l'ajoute au graphique
			dataset.setValue(mutuelle.getKey(), mutuelle.getValue() * 100 / populationglobale);
		}

		chart = ChartFactory.createPieChart3D("Mutuelle des patients", // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();		// plot.setStartAngle( 270 );
		plot.setForegroundAlpha(0.60f);

		this.chartpanel = new ChartPanel(chart);
		this.add(chartpanel);

	}

	/**
	 * Getter
	 *
	 * @return un panel contenant le diagramme 3D
	 */
	public ChartPanel getChart() {
		return chartpanel;
	}

}
