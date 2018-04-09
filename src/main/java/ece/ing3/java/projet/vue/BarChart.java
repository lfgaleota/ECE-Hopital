package ece.ing3.java.projet.vue;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChart
{
	public static DefaultCategoryDataset dataset;
	public static ChartPanel chartPanel;
	public static JFreeChart chart;
	
	public BarChart() { }
	
	
	public ChartPanel Creer_BarChart()
	{
		dataset = new DefaultCategoryDataset();
		dataset.addValue(1.0, "Row 1", "Column 1");
		dataset.addValue(5.0, "Row 1", "Column 2");
		dataset.addValue(3.0, "Row 1", "Column 3");
		dataset.addValue(2.0, "Row 2", "Column 1");
		dataset.addValue(3.0, "Row 2", "Column 2");
		dataset.addValue(2.0, "Row 2", "Column 3");
		
		 chart = ChartFactory.createBarChart(
				"Bar Chart Demo", // chart title
				"Category", // domain axis label
				"Value", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
				);
		
		chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new Dimension(400, 200));
		
		return chartPanel;
	}
	
}
