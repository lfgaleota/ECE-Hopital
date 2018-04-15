package ece.ing3.java.projet.vue;

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
public class PieChart3D extends JPanel {

	private static final long serialVersionUID = 5686202670543030890L;
	private DefaultPieDataset dataset = new DefaultPieDataset();
	private JFreeChart chart;
	private ChartPanel chartpanel;

	/**
	 * Constructeur par défaut -> Complete les informations du diagramme 
	 * -> Rempli et affiche le diagramme circulaire dans un panel
	 */
	public PieChart3D() {

		dataset.setValue("IPhone 5s", new Double(20));
		dataset.setValue("SamSung Grand", new Double(20));
		dataset.setValue("MotoG", new Double(40));
		dataset.setValue("Nokia Lumia", new Double(10));

		chart = ChartFactory.createPieChart3D("Mobile Sales", // chart title
				dataset, // data
				true, // include legend
				true, false);

		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		// plot.setStartAngle( 270 );
		plot.setForegroundAlpha(0.60f);
		// plot.setInteriorGap( 0.02 );

		/** Sauvegarde comme une image (.jpeg) **/
		/*
		 * int width = 640; /* Width of the image int height = 480; /* Height of the
		 * image File pieChart3D = new File( "pie_Chart3D.jpeg" );
		 * ChartUtilities.saveChartAsJPEG( pieChart3D , chart , width , height );
		 */
		this.chartpanel = new ChartPanel(chart);
		this.add(chartpanel);

	}

	/**
	 * Getter
	 * 
	 * @return un panel contenant le diagramme 3D
	 */
	public ChartPanel getPieChart3D() {
		return chartpanel;
	}

}
