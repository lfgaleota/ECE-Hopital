package ece.ing3.java.projet.vue;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ece.ing3.java.projet.utils.Constants;

public class ModelSearchDialog extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel recherche;
	JButton submit;
	Map<String, JTextField> textFields;

	public ModelSearchDialog(JList modelList, Map<String, String> parameters ) {
		this.setTitle ("Recherche");
		this.setSize(500,300);
		this.setResizable(true);
		this.setVisible(true);
		this.toFront(); // place la fenêtre devant les autres.

		textFields = new HashMap<>();

		//on récupère la taille de la liste
		int size = modelList.getModel().getSize();
		recherche =  new JPanel();
		GridLayout g =  new GridLayout(size+1,1);
		recherche.setLayout(g);

		for( Entry<String, String> parameter : parameters.entrySet() ) {
			JTextField field = new JTextField( Constants.UI_TEXTFIELD_MAXLENGTH );
			textFields.put( parameter.getKey(), field );
			recherche.add( new JLabel( parameter.getValue() ) );
			recherche.add( field );
		}

		submit = new JButton("Valider");
		recherche.add(submit);
		this.add(recherche);
		submit.addActionListener(this);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == submit)
		{
			//test la correspondance avec la JListe
			System.out.println("Validation");
			//On ferme la fenêtre et les informations (s'il y en a )apparaissent sur la table centrale
			this.dispose();
	
		}
		
	}
}
