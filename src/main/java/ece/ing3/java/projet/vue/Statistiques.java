package ece.ing3.java.projet.vue;

import java.awt.GridLayout;

import javax.swing.JFrame;

public class Statistiques  extends JFrame {
	private static final long serialVersionUID = 1L;

	public Statistiques()
	{
		this.setTitle ("Statistiques de l'Hôpital");
		this.setSize((int)getToolkit().getScreenSize().getWidth(), ((int)getToolkit().getScreenSize().getHeight()));
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.
	}
	
	/**
	 * Méthode qui créée des objets de JFreeChart et les ajoute dans une fenêtre
	 */
	public void creer_Statistiques()
	{
		GridLayout mygridLayout = new GridLayout(3,2); 
		this.setLayout(mygridLayout);
		mygridLayout.setHgap(10);
		mygridLayout.setVgap(10);
		  
		
		PieChart2D P= new PieChart2D();
		PieChart3D P3= new PieChart3D();
		BarChart B = new BarChart();
		
		this.getContentPane().add(P.getPieChart2D());
		this.getContentPane().add(P3.getPieChart3D());
		this.getContentPane().add(B.getBarChart());
		
		this.pack();
	}
}
