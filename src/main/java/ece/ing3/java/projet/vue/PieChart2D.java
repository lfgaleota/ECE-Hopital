package ece.ing3.java.projet.vue;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class PieChart2D {

	
	private static DefaultPieDataset dataset;
	private static JFreeChart chart;
	private static ChartPanel chartpanel;
 
	public PieChart2D()  { }
	
	public ChartPanel Creer_2DPie()
	{
		dataset = new DefaultPieDataset();
		dataset.setValue("Category 1", 43.2);
		dataset.setValue("Category 2", 27.9);
		dataset.setValue("Category 3", 79.5);
		
		chart = ChartFactory.createPieChart("Sample Pie Chart",dataset,true, // legend?
				true, // tooltips?
				false // URLs?
				);
		
		chartpanel = new ChartPanel(chart);
		return chartpanel;
	}
}
