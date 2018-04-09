package ece.ing3.java.projet.vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Recherche extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel recherche;
	JButton valider;
	JTextField jnumero;
	JTextField jnom;
	JTextField jprenom;
	JTextField jtel;
	JTextField jadresse;
	
	public Recherche()
	{
		this.setTitle ("Recherche");
		this.setSize(500,300);
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.
	}
	
	public void creer_recherche(JList maListe)
	{	
		//on récupère la taille de la liste
		int size = maListe.getModel().getSize();
		recherche =  new JPanel();
		GridLayout g =  new GridLayout(size+1,1);
		recherche.setLayout(g);
	
		JLabel numero =  new JLabel("Numéro: ");
		JTextField jnumero= new JTextField(10);
		JLabel nom =  new JLabel("Nom: ");
		JTextField jnom= new JTextField(10);
		JLabel prenom =  new JLabel("Prénom: ");
		JTextField jprenom= new JTextField(10);
		JLabel tel =  new JLabel("Télephone: ");
		JTextField jtel= new JTextField(10);
		JLabel adresse =  new JLabel("Adresse: ");
		JTextField jadresse= new JTextField(10);
		valider = new JButton("Valider");
		
		recherche.add(numero);
		recherche.add(jnumero);
		recherche.add(nom);
		recherche.add(jnom);
		recherche.add(prenom);
		recherche.add(jprenom);
		recherche.add(tel);
		recherche.add(jtel);
		recherche.add(adresse);
		recherche.add(jadresse);
		recherche.add(valider);
		this.add(recherche);
		
		valider.addActionListener(this);
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == valider)
		{
			//test la correspondance avec la JListe
			System.out.println("Validation");
			//On ferme la fenêtre et les informations (s'il y en a )apparaissent sur la table centrale
			this.dispose();
	
		}
		
	}
}
