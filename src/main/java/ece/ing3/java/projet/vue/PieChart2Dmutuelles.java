package ece.ing3.java.projet.vue;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;

import ece.ing3.java.projet.modele.hopital.Malade;

public class PieChart2Dmutuelles extends PieChart2DModel {

	public PieChart2Dmutuelles(List<Malade> malistemalade ) {

		super("Mutuelle des malades");

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

		chart = ChartFactory.createPieChart(this.titre, dataset, true,true, false );

		chartpanel = new ChartPanel(chart);
	}
}
