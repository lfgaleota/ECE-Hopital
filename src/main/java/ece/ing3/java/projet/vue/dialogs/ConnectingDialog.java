package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.components.LoadingThrobber;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

		( (JPanel) getContentPane() ).setBorder( new EmptyBorder( Constants.UI_DIALOG_MARGIN ) );

		this.add( new LoadingThrobber(), BorderLayout.LINE_START );
		this.add( new JLabel( Strings.get( "dialog.connecting.text" ) ), BorderLayout.CENTER );

		this.pack();

		this.setLocationRelativeTo( null );

		this.setVisible( true );
		this.toFront();
	}
}
