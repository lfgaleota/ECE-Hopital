package ece.ing3.java.projet.vue;

import ece.ing3.java.projet.modele.employe.Employe;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Classe modèle pour les diagrammes circulaires 2D
 * 
 * @author Nicolas
 *
 */
public class PieChart2D {

	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static ChartPanel chartpanel;
        private static String tmpdebut;

	/**
	 * Constructeur par défaut -> Complete les informations du diagramme 
	 * -> Rempli et affiche le diagramme circulaire dans un panel
	 */
	public PieChart2D(List<Employe> malisteemploye) {
		dataset = new DefaultPieDataset();
                
                /*
                
                ///A SAVOIR QUE CE BLOC NE SERT UNIQUEMENT POUR DES TEST
                ///VALEUR DU NOMBRE DE PERSONNE DELA POPLUATION
                
                int populationglobale = malisteemploye.size();
                
                
                int populationvoulue=0;
                
                
                System.out.println(malisteemploye.size());
                
                for(int i=0;i<malisteemploye.size();i++)
                {
                        System.out.println(malisteemploye.get(i).getNom());
                        
                        if(malisteemploye.get(i).getNom().charAt(0)=='D')
                        {
                            populationvoulue++;
                        }
                        
                }
   
                
                System.out.println(" "+ populationvoulue);
                
                
                
                
		dataset.setValue("Category 1",(populationvoulue*100/populationglobale));
		dataset.setValue("Category 2", (1-populationvoulue/populationglobale)*100);
		//dataset.setValue("Category 3", 79.5);

                
                System.out.println("PUTAIN "+malisteemploye.get(0).getAdresse()+" "+ malisteemploye.size());
                
                */
		chart = ChartFactory.createPieChart("Sample Pie Chart", dataset, true, // legend?
				true, // tooltips?
				false // URLs?
		);

		chartpanel = new ChartPanel(chart);
	}

	/**
	 * Getter
	 * 
	 * @return un panel contenant le diagramme 2D
	 */
	public ChartPanel getPieChart2D() {
		return chartpanel;
	}
}
