package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.utils.Strings;

import javax.swing.*;
import java.awt.*;

/**
 * Boîte de dialogue de connexion.
 */
public class ConnectingDialog extends JDialog {
	public ConnectingDialog() {
		this.setTitle( Strings.get( "dialog.connecting.title" ) );
		this.setLayout( new BorderLayout() );
		this.setResizable( false );
		this.setDefaultCloseOperation( JFrame.DO_NOTHING_ON_CLOSE );
		this.setAlwaysOnTop( true );

		this.add( new JLabel( UIManager.getIcon( "OptionPane.informationIcon" ) ), BorderLayout.LINE_START );
		this.add( new JLabel( Strings.get( "dialog.connecting.text" ) ), BorderLayout.CENTER );

		this.pack();

		this.setLocationRelativeTo( null );

		this.setVisible( true );
		this.toFront();
	}
}
