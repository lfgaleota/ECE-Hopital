package ece.ing3.java.projet.vue.panels;

import javax.swing.*;
import java.awt.*;

public class LoadingPanel extends JPanel {
	public LoadingPanel() {
		setLayout( new BorderLayout() );
		setBackground( Color.WHITE );
		add( new JLabel( "..." ), BorderLayout.CENTER );
	}
}
