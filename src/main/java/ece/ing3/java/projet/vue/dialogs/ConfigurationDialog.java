package ece.ing3.java.projet.vue.dialogs;

import ece.ing3.java.projet.utils.Constants;
import ece.ing3.java.projet.utils.Strings;
import ece.ing3.java.projet.vue.panels.ConfigurationPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Boîte de dialogue de configuration.
 * Cette boîte de dialogue est bloquante et n'est pas visible par défaut.
 */
public class ConfigurationDialog extends JDialog {
	private ConfigurationPanel panel;

	private boolean validated;

	public ConfigurationDialog( ConfigurationPanel panel ) {
		this.panel = panel;

		this.setTitle( Strings.get( "configuration.title" ) );
		this.setLayout( new BorderLayout() );
		this.setResizable( true );
		this.setModal( true );
		this.setAlwaysOnTop( true );

		this.setLocationRelativeTo( null );

		( (JPanel) getContentPane() ).setBorder( new EmptyBorder( Constants.UI_DIALOG_MARGIN ) );

		this.add( this.panel, BorderLayout.CENTER );

		this.pack();

		this.setLocationRelativeTo( null );
	}

	public void addActionListener( ActionListener actionListener ) {
		this.panel.addActionListener( actionListener );
	}

	public ConfigurationPanel getPanel() {
		return panel;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated( boolean validated ) {
		this.validated = validated;
	}

	/**
	 * Retourne un entier, correspodant à une valeur d'une constante de {@link javax.swing.JOptionPane}, indiquant si le dialogue a été validé ou non.
	 *
	 * @return {@link javax.swing.JOptionPane#YES_OPTION} si validé
	 */
	public int get() {
		return ( isValidated() ? JOptionPane.YES_OPTION : JOptionPane.NO_OPTION );
	}
}
